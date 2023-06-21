package com.aceadoratech.jumpstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String firstname;
    private String lastname;
    private String address;
    private int age;
    private String gender;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    UserLogin user;
}
