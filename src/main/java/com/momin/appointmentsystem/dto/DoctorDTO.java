package com.momin.appointmentsystem.dto;
import jakarta.validation.constraints.*; import lombok.Data;
@Data public class DoctorDTO { @NotBlank private String name; @NotBlank private String specialization; @Email @NotBlank private String email; private String phone; private boolean available = true; }
