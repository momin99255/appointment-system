package com.momin.appointmentsystem.service;
import com.momin.appointmentsystem.dto.AppointmentDTO; import com.momin.appointmentsystem.enums.AppointmentStatus; import com.momin.appointmentsystem.exception.ResourceNotFoundException; import com.momin.appointmentsystem.model.*; import com.momin.appointmentsystem.repository.*; import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service; import java.util.*;
@Service @RequiredArgsConstructor public class AppointmentService {
 private final AppointmentRepository repo; private final DoctorRepository doctors; private final UserRepository users;
 public Appointment book(String email,AppointmentDTO d){
  User p=users.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Patient not found"));
  if(p.getRole()!=com.momin.appointmentsystem.enums.Role.PATIENT) throw new IllegalArgumentException("Only patients can book appointments");
  Doctor doc=doctors.findById(d.getDoctorId()).orElseThrow(()->new ResourceNotFoundException("Doctor not found"));
  if(!doc.isAvailable()) throw new IllegalArgumentException("Doctor is not available");
  if(d.getAppointmentDate().isBefore(java.time.LocalDate.now())) throw new IllegalArgumentException("Appointment date cannot be in the past");
  if(repo.existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(doc.getId(),d.getAppointmentDate(),d.getAppointmentTime(),AppointmentStatus.CANCELLED)) throw new IllegalArgumentException("This time slot is already booked");
  return repo.save(Appointment.builder().patientId(p.getId()).patientName(p.getName()).doctorId(doc.getId()).doctorName(doc.getName()).appointmentDate(d.getAppointmentDate()).appointmentTime(d.getAppointmentTime()).reason(d.getReason()).status(AppointmentStatus.BOOKED).build());
 }
 public List<Appointment> myAppointments(String email){User p=users.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));return repo.findByPatientIdOrderByAppointmentDateAscAppointmentTimeAsc(p.getId());}
 public List<Appointment> doctorAppointments(String id){return repo.findByDoctorIdOrderByAppointmentDateAscAppointmentTimeAsc(id);}
 public List<Appointment> all(){return repo.findAllByOrderByAppointmentDateAscAppointmentTimeAsc();}
 public Appointment updateStatus(String id,AppointmentStatus status){Appointment a=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Appointment not found"));a.setStatus(status);return repo.save(a);}
 public void cancel(String id){updateStatus(id,AppointmentStatus.CANCELLED);}
}
