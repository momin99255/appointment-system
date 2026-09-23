package com.momin.appointmentsystem.service;

import com.momin.appointmentsystem.model.User;
import com.momin.appointmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    public User register(User u){
        if(repo.existsByEmail(u.getEmail())) throw new IllegalArgumentException("Email already registered");
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }
    public User find(String email){return repo.findByEmail(email).orElseThrow(()->new IllegalArgumentException("Invalid credentials"));}
    public boolean matches(String raw,String hash){return encoder.matches(raw,hash);}
    public List<User> findAll(){return repo.findAllByOrderByNameAsc();}
    public User findById(String id){return repo.findById(id).orElseThrow(()->new IllegalArgumentException("User not found"));}
    public User save(User u){return repo.save(u);}
    public void delete(String id){repo.deleteById(id);}
}
