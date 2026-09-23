package com.momin.appointmentsystem.service;
import com.momin.appointmentsystem.model.Doctor; import com.momin.appointmentsystem.repository.DoctorRepository; import com.momin.appointmentsystem.dto.DoctorDTO; import com.momin.appointmentsystem.exception.ResourceNotFoundException; import lombok.RequiredArgsConstructor; import org.springframework.cache.annotation.*; import org.springframework.stereotype.Service; import java.util.*;
@Service @RequiredArgsConstructor public class DoctorService {
 private final DoctorRepository repo;
 @Cacheable("doctors") public List<Doctor> findAll(){return repo.findAll();}
 @Cacheable(value="doctorById",key="#id") public Doctor findById(String id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Doctor not found"));}
 public Doctor findByEmail(String email){return repo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Doctor profile not found"));}
 @CacheEvict(value={"doctors","doctorById"},allEntries=true) public Doctor create(DoctorDTO d){return repo.save(Doctor.builder().name(d.getName()).specialization(d.getSpecialization()).email(d.getEmail()).phone(d.getPhone()).available(d.isAvailable()).build());}
 @CacheEvict(value={"doctors","doctorById"},allEntries=true) public Doctor update(String id,DoctorDTO d){Doctor x=findById(id); x.setName(d.getName());x.setSpecialization(d.getSpecialization());x.setEmail(d.getEmail());x.setPhone(d.getPhone());x.setAvailable(d.isAvailable());return repo.save(x);}
 @CacheEvict(value={"doctors","doctorById"},allEntries=true) public void delete(String id){repo.deleteById(id);}
}
