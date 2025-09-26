package com.tnsif.placement.service;

import com.tnsif.placement.entity.Student;
import com.tnsif.placement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = getStudentById(id);

        existingStudent.setName(studentDetails.getName());
        existingStudent.setCollege(studentDetails.getCollege());
        existingStudent.setCourse(studentDetails.getCourse());
        existingStudent.setYear(studentDetails.getYear());
        existingStudent.setCgpa(studentDetails.getCgpa());
        existingStudent.setPlacementStatus(studentDetails.getPlacementStatus());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}