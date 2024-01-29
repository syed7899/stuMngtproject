package com.coursemanagement.studentmangement.service;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Student;
import com.coursemanagement.studentmangement.exception.CourseNotFoundException;
import com.coursemanagement.studentmangement.exception.StudentNotFoundException;
import com.coursemanagement.studentmangement.respository.CourseRepository;
import com.coursemanagement.studentmangement.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getAllStudents(int studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void addCoursesForStudent(int studentId, int courseId) {
        Student theStudent=studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student Not found with given id:******"+ studentId));
        Course theCourse=courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course Not found with given id:****** "+ courseId));
        theCourse.addStudent(theStudent);
        courseRepository.save(theCourse);
    }

    @Override
    public List<Course> getCoursesForStudent(int studentId) {
        Student theStudent=studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student Not found with given id:****** "+ studentId));
          return theStudent.getCourseList();
    }
}
