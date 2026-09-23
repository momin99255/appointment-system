package com.momin.appointmentsystem.repository;
import com.momin.appointmentsystem.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAllByOrderByNameAsc();
}
