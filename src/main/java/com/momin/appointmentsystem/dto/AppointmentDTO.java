package com.momin.appointmentsystem.dto;
import jakarta.validation.constraints.*; import lombok.Data; import java.time.LocalDate; import java.time.LocalTime;
@Data public class AppointmentDTO { @NotBlank private String doctorId; @NotNull private LocalDate appointmentDate; @NotNull private LocalTime appointmentTime; private String reason; }
