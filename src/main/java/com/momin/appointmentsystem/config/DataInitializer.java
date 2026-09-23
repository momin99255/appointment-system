package com.momin.appointmentsystem.config;

import com.momin.appointmentsystem.enums.Role;
import com.momin.appointmentsystem.model.Doctor;
import com.momin.appointmentsystem.model.User;
import com.momin.appointmentsystem.repository.DoctorRepository;
import com.momin.appointmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository users;
    private final DoctorRepository doctors;
    private final PasswordEncoder encoder;
    @Value("${app.admin.email:admin@example.com}") private String adminEmail;
    @Value("${app.admin.password:change-me-in-render}") private String adminPassword;

    @Override public void run(String... args) {
        if (!users.existsByEmail(adminEmail) && adminPassword != null && !adminPassword.isBlank() && !adminPassword.equals("change-me-in-render")) {
            users.save(User.builder().name("System Administrator").email(adminEmail).password(encoder.encode(adminPassword)).role(Role.ADMIN).build());
        }
        if (doctors.count()==0) {
            doctors.save(Doctor.builder().name("Dr. Rahim Ahmed").specialization("Cardiology").email("rahim.doctor@appointment.local").phone("+880 1700-000001").available(true).build());
            doctors.save(Doctor.builder().name("Dr. Nusrat Jahan").specialization("Dermatology").email("nusrat.doctor@appointment.local").phone("+880 1700-000002").available(true).build());
            doctors.save(Doctor.builder().name("Dr. Tanvir Hasan").specialization("Neurology").email("tanvir.doctor@appointment.local").phone("+880 1700-000003").available(true).build());
        }
    }
}
