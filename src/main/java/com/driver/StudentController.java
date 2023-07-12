package com.driver;

import java.util.List;

import com.driver.exceptions.StudentDoesNotExists;
import com.driver.Student;
import com.driver.Teacher;
import com.driver.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        String studentResponse = studentService.addStudent(student);
        if(studentResponse == null){
            return new ResponseEntity<>("student already exists",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentResponse,HttpStatus.CREATED);
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        String teacherResponse = studentService.addTeacher(teacher);
        if (teacherResponse == null){
            return new ResponseEntity<>("Teacher Already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teacherResponse,HttpStatus.CREATED);
    }

    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam String student, @RequestParam String teacher){
        String studentTeacherResponse = studentService.addStudentTeacherPair(student,teacher);
        return new ResponseEntity<>(studentTeacherResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        // Assign student by calling service layer method
        Student student = studentService.getStudentByName(name);
        if(student != null){
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
        throw new StudentDoesNotExists("Student does not exits");
    }

    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        // Assign student by calling service layer method
        Teacher teacher = studentService.getTeacherByName(name);
        if (teacher != null){
            return new ResponseEntity<>(teacher, HttpStatus.CREATED);
        }
        throw new RuntimeException("Teacher does not exits");
    }

    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
        // Assign list of student by calling service layer method
        List<String> students = studentService.getStudentsByTeacherName(teacher);
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        // Assign list of student by calling service layer method
        List<String> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher){
        String deleteTeacherResponse = studentService.deleteTeacherByName(teacher);
        return new ResponseEntity<>(teacher + " " + deleteTeacherResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){
        String deleteResponse = studentService.deleteAllTeachers();
        return new ResponseEntity<>(deleteResponse, HttpStatus.CREATED);
    }
}
