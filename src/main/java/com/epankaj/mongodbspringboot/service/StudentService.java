package com.epankaj.mongodbspringboot.service;

import com.epankaj.mongodbspringboot.model.Student;
import com.epankaj.mongodbspringboot.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
