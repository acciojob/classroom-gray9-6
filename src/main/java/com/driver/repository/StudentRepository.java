package com.driver.repository;


import com.driver.model.Student;
import com.driver.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String,Student> studentHashMap = new HashMap<>();
    HashMap<String, Teacher> teacherHashMap = new HashMap<>();
    HashMap<String,List<String>> teacherStudentHashMap = new HashMap<>();

    // add student
    public String addStudent(Student student) {

        if (studentHashMap.containsKey(student.getName())){
            return null;
        }

        studentHashMap.put(student.getName(), student);
        return "New student added successfully";
    }

    public String addTeacher(Teacher teacher) {
        if (teacherHashMap.containsKey(teacher.getName())){
            return null;
        }

        teacherHashMap.put(teacher.getName(), teacher);
        return "New teacher added successfully";
    }

    public String addStudentTeacherPair(String student, String teacher) {
        if(!studentHashMap.containsKey(student)){
            return "student does not exists";
        }
        if(!teacherHashMap.containsKey(teacher)){
            return "Teacher does not exists";
        }

        // adding student teacher pair
        // get the student list of that teacher
        List<String> studentList = teacherStudentHashMap.get(teacher);

        // if that student list is not null then add the  student to the current list
        if(studentList != null){
            studentList.add(student);
        }else if (studentList == null){  // and if the list is null , then make a new list and add the student to that list
            List<String> newStudentList = new ArrayList<>();
            newStudentList.add(student);
            teacherStudentHashMap.put(teacher,newStudentList);
        }

        // setting the number of students to teacher
        for ( Teacher teacher1: teacherHashMap.values()){
            if(teacher1.getName().equals(teacher)){
                teacher1.setNumberOfStudents(teacher1.getNumberOfStudents() + 1);
            }
        }

        return "New student-teacher pair added successfully";
    }

    public Student getStudentByName(String name) {
        if(!studentHashMap.containsKey(name)){
            return  null;
        }
        return studentHashMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        if(!teacherHashMap.containsKey(name)){
            return  null;
        }
        return teacherHashMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        if(!teacherHashMap.containsKey(teacher)){
            return new ArrayList<>();
        }

        return teacherStudentHashMap.get(teacher);
    }

    public List<String> getAllStudents() {
        if(studentHashMap.isEmpty()){
            return new ArrayList<>();
        }


        List<String> studentList = new ArrayList<>();  // create a new list
        studentList.addAll(studentHashMap.keySet());  // add all the key of studentHasMap to list
        return studentList;
    }

    public String deleteTeacherByName(String teacher) {
        if(!teacherHashMap.containsKey(teacher)){
            return "teacher does not exists";
        }

        // remove the teacher from the teacher hashmap
        teacherHashMap.remove(teacher);

        // remove the teacher from teacherStudentHashmap also
        teacherStudentHashMap.remove(teacher);

        return " removed successfully";
    }

    public String deleteAllTeachers() {
        if (teacherHashMap.isEmpty()){
            return "There are not teachers";
        }

        // remove teacher from teacher hashmap
        teacherHashMap.clear();

        return "All teachers deleted successfully";
    }
}
