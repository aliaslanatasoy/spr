package com.spr.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class ApplicationUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String userImage;

    private String name;

    private String surname;

    private Date dateOfBirth;

    private String phoneNumber;

}