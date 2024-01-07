package com.epankaj.mongodbspringboot;

import com.epankaj.mongodbspringboot.constant.Gender;
import com.epankaj.mongodbspringboot.model.Address;
import com.epankaj.mongodbspringboot.model.Student;
import com.epankaj.mongodbspringboot.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@SpringBootApplication
public class MongodbSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbSpringbootApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(
            StudentRepository studentRepository,
            MongoTemplate mongoTemplate
            ) {
        return args -> {
            Address address = new Address(
                    "India",
                    "Gopalganj",
                    "Shekh Parsa",
                    "841427"
            );
            List<String> topics = Arrays.asList("Java", "Spring");
            Student student = new Student(
                    "Arun",
                    "Kumar",
                    "arunshek@gmail.com",
                    "9074451235",
                    Gender.MALE,
                    address,
                    topics,
                    new BigDecimal("789.123"),
                    LocalDateTime.now());

            //usingMongoTemplateAndQuery(studentRepository, mongoTemplate, student);
            String email = "arunshek@gmail.com";
            studentRepository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> {
                            System.out.println("Student already exit "+ s);
                    }, () -> {
                System.out.println("Inserting student " + student);
                studentRepository.insert(student);
            });
        };
    }

    private static void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate, Student student) throws IllegalAccessException {
        Query query = new Query();
        String email = "arunshek@gmail.com";
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if(students.size() > 1){
            throw new IllegalAccessException("Found many student with email "+email);
        }

        if(students.isEmpty()){
            System.out.println("Inserting student " + student);
            studentRepository.insert(student);
        }else {
            System.out.println("Student already exit "+ student);
        }
    }

}
