package com.momin.appointmentsystem.controller;
import com.momin.appointmentsystem.dto.DoctorDTO; import com.momin.appointmentsystem.enums.Role; import com.momin.appointmentsystem.model.*; import com.momin.appointmentsystem.service.*; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/admin") @RequiredArgsConstructor public class AdminController {
 private final DoctorService doctors; private final UserService users; private final AppointmentService appointments;
 @GetMapping("/users") public List<User> users(){return users.findAll().stream().peek(u->u.setPassword(null)).toList();}
 @DeleteMapping("/users/{id}") public void deleteUser(@PathVariable String id){users.delete(id);}
 @GetMapping("/doctors") public List<Doctor> doctorList(){return doctors.findAll();}
 @PostMapping("/doctors") public Doctor create(@Valid @RequestBody DoctorDTO d){return doctors.create(d);}
 @PutMapping("/doctors/{id}") public Doctor update(@PathVariable String id,@Valid @RequestBody DoctorDTO d){return doctors.update(id,d);}
 @DeleteMapping("/doctors/{id}") public void delete(@PathVariable String id){doctors.delete(id);}
 @GetMapping("/appointments") public List<Appointment> appointmentList(){return appointments.all();}
}
