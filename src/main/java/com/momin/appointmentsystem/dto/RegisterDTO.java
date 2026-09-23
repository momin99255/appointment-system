package com.momin.appointmentsystem.dto;
import com.momin.appointmentsystem.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data public class RegisterDTO {
    @NotBlank private String name;
    @Email @NotBlank private String email;
    @Size(min=6) private String password;
    private Role role = Role.PATIENT;
    private String specialization;
    private String phone;
}
