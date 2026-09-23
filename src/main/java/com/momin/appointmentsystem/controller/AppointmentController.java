package com.momin.appointmentsystem.controller;
import com.momin.appointmentsystem.dto.AppointmentDTO; import com.momin.appointmentsystem.enums.AppointmentStatus; import com.momin.appointmentsystem.model.Appointment; import com.momin.appointmentsystem.service.AppointmentService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/appointments") @RequiredArgsConstructor public class AppointmentController {
 private final AppointmentService service;
 @PostMapping public Appointment book(Authentication a,@Valid @RequestBody AppointmentDTO d){return service.book(a.getName(),d);}
 @GetMapping("/my") public List<Appointment> mine(Authentication a){return service.myAppointments(a.getName());}
 @GetMapping("/doctor/{doctorId}") public List<Appointment> doctor(@PathVariable String doctorId){return service.doctorAppointments(doctorId);}
 @PutMapping("/{id}/status") public Appointment status(@PathVariable String id,@RequestParam AppointmentStatus status){return service.updateStatus(id,status);}
 @DeleteMapping("/{id}") public void cancel(@PathVariable String id){service.cancel(id);}
}
