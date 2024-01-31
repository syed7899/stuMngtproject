package com.coursemanagement.studentmangement.service;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Instructor;
import com.coursemanagement.studentmangement.entity.Student;
import com.coursemanagement.studentmangement.exception.CourseNotFoundException;
import com.coursemanagement.studentmangement.exception.StudentNotFoundException;
import com.coursemanagement.studentmangement.respository.CourseRepository;
import com.coursemanagement.studentmangement.respository.StudentRepository;
import com.coursemanagement.studentmangement.utility.EmailValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
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
        Student returnStudent=null;
        String regexPattern = "^(?=.*[@])(?:(?:[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*)@(?:gmail\\.com|yahoo\\.com|yahoo\\.co\\.in|gmail\\.co\\.in))$";
        String emailaddress=student.getEmail();

        Boolean emailValid= EmailValidator.patternMatches(emailaddress,regexPattern);
        log.info(emailValid);
        log.info(emailaddress);
        if (emailValid) {
            student.setEmail(emailaddress);
            returnStudent = studentRepository.save(student);
        }else{
            throw new RuntimeException("Invalid email..plz check your emailId");
        }
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
                .orElseThrow(() -> new StudentNotFoundException("Student Not found with given id:******"+ studentId,"COURSE_NOT_FOUND"));
        Course theCourse=courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course Not found with given id:****** "+ courseId,"COURSE_NOT_FOUND"));
        theCourse.addStudent(theStudent);
        courseRepository.save(theCourse);
    }

    @Override
    public List<Course> getCoursesForStudent(int studentId) {
        Student theStudent=studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student Not found with given id:****** "+ studentId,"STUDENT_NOT_FOUND"));
        log.info("Student Object::******************************"+theStudent);
        return theStudent.getCourseList();
    }
}
