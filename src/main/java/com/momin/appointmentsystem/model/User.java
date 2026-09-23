package com.momin.appointmentsystem.model;

import com.momin.appointmentsystem.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "users")
public class User {
    @Id private String id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
