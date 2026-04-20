package com.connectify.backend.model.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String gender;

    private String month;
    private String day;
    private String year;

    @Column(name = "contact_number", unique = true, nullable = true)
    private String contactNumber;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "avatar_url", nullable = true) 
    private String avatar;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false) 
    private OffsetDateTime createAt;
}
