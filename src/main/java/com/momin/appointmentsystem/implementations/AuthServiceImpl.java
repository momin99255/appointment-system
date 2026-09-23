package com.momin.appointmentsystem.implementations;
import com.momin.appointmentsystem.dto.*; import com.momin.appointmentsystem.enums.Role; import com.momin.appointmentsystem.model.User; import com.momin.appointmentsystem.security.JwtService; import com.momin.appointmentsystem.service.DoctorService; import com.momin.appointmentsystem.service.UserService; import com.momin.appointmentsystem.dto.DoctorDTO; import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor public class AuthServiceImpl {
 private final UserService users; private final JwtService jwt; private final DoctorService doctors;
 public String register(RegisterDTO d){
  Role role=d.getRole()==null?Role.PATIENT:d.getRole();
  if(role==Role.ADMIN) role=Role.PATIENT;
  User u=users.register(User.builder().name(d.getName()).email(d.getEmail()).password(d.getPassword()).role(role).build());
  if(role==Role.DOCTOR){
   String specialization=(d.getSpecialization()==null||d.getSpecialization().isBlank())?"General Physician":d.getSpecialization();
   doctors.create(new DoctorDTOBuilder(d.getName(), specialization, d.getEmail(), d.getPhone()).build());
  }
  return jwt.generateToken(u.getEmail(),u.getRole().name());
 }
 public String login(LoginDTO d){User u=users.find(d.getEmail()); if(!users.matches(d.getPassword(),u.getPassword())) throw new IllegalArgumentException("Invalid credentials"); return jwt.generateToken(u.getEmail(),u.getRole().name());}
 private record DoctorDTOBuilder(String name,String specialization,String email,String phone){DoctorDTO build(){DoctorDTO d=new DoctorDTO();d.setName(name);d.setSpecialization(specialization);d.setEmail(email);d.setPhone(phone);d.setAvailable(true);return d;}}
}
