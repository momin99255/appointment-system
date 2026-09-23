package com.momin.appointmentsystem.repository;
import com.momin.appointmentsystem.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface DoctorRepository extends MongoRepository<Doctor,String> {
    Optional<Doctor> findByEmail(String email);
}
