package com.momin.appointmentsystem.controller;
import com.momin.appointmentsystem.dto.DoctorDTO; import com.momin.appointmentsystem.model.Doctor; import com.momin.appointmentsystem.service.DoctorService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import org.springframework.security.core.Authentication; import java.util.*;
@RestController @RequestMapping("/api/doctors") @RequiredArgsConstructor public class DoctorController {
 private final DoctorService service;
 @GetMapping public List<Doctor> all(){return service.findAll();}
 @GetMapping("/{id}") public Doctor one(@PathVariable String id){return service.findById(id);}
 @GetMapping("/me") public Doctor me(Authentication a){return service.findByEmail(a.getName());}
 @PostMapping public Doctor create(@Valid @RequestBody DoctorDTO d){return service.create(d);}
 @PutMapping("/{id}") public Doctor update(@PathVariable String id,@Valid @RequestBody DoctorDTO d){return service.update(id,d);}
 @DeleteMapping("/{id}") public void delete(@PathVariable String id){service.delete(id);}
}
