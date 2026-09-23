package com.momin.appointmentsystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "doctors")
public class Doctor {
    @Id private String id;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private boolean available;
}
