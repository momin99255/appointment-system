package com.momin.appointmentsystem.repository;
import com.momin.appointmentsystem.enums.AppointmentStatus;
import com.momin.appointmentsystem.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.*; import java.util.*;
public interface AppointmentRepository extends MongoRepository<Appointment,String> {
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(String doctorId, LocalDate date, LocalTime time, AppointmentStatus status);
    List<Appointment> findByDoctorIdOrderByAppointmentDateAscAppointmentTimeAsc(String doctorId);
    List<Appointment> findByPatientIdOrderByAppointmentDateAscAppointmentTimeAsc(String patientId);
    List<Appointment> findAllByOrderByAppointmentDateAscAppointmentTimeAsc();
}
