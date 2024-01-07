package com.epankaj.mongodbspringboot.model;

import com.epankaj.mongodbspringboot.constant.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Gender gender;

    private Address address;
    private List<String> favoriteTopics;
    private BigDecimal totalSpendInCourse;
    private LocalDateTime created;

    public Student(String firstName,
                   String lastName,
                   String email,
                   String phone,
                   Gender gender,
                   Address address,
                   List<String> favoriteTopics,
                   BigDecimal totalSpendInCourse,
                   LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.favoriteTopics = favoriteTopics;
        this.totalSpendInCourse = totalSpendInCourse;
        this.created = created;
    }
}
