package com.java.connection.service;

import com.java.connection.dao.StudentDao;
import com.java.connection.entity.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    static final StudentDao studentDao = new StudentDao();
    static final Student studentEntity=new Student();
    Scanner sc = new Scanner(System.in);

    public void findAll() {
        try {
            List<Student> listStudentFAll = studentDao.findAll();
            for (Student std : listStudentFAll) {
                System.out.println(std);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void findById() {
        try {
            System.out.println("Enter id want find !!!");
            int ID = sc.nextInt();
            Student studentFID = studentDao.findByID(ID);
            if (studentFID == null) {
                System.out.println("Id not exist");
            } else {
                System.out.println(studentFID);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void findByName() {
        try {
            System.out.println("Enter first character want find from first name !!!!");
            String name = sc.nextLine();
            List<Student> listStudentFN = studentDao.findByName(name);
            for (Student std : listStudentFN) {
                System.out.println(std);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void addStudent() {
        Student student=inputStudent();
        try {
            boolean result=studentDao.addStudentWithoutClass(student);
            if (result){
                System.out.println("Add successfully!!!");
            }
            else {
                System.out.println("Add");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void deleteStudentByID() {
        System.out.println("Enter id want delete");
        int ID = sc.nextInt();
        sc.nextLine();
        try {
            boolean result=studentDao.deleteStudentByID(ID);
            if(result){
                System.out.println("Delete successfully !!!");
            }
            else {
                System.out.println("Delete not successfully !!!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void findMaxScore() {
        try {
            Student student = studentDao.findMaxScore();
                System.out.println(student);
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    public void changePlace2Student (){
        System.out.println("Enter id want modify !!!");

        try {
            System.out.println("Enter id 1");
            Student student1=studentDao.findByID(sc.nextInt());
            System.out.println("Enter id 2");
            Student student2= studentDao.findByID(sc.nextInt());
            boolean result=studentDao.changePlace2Student(student1,student2);
            if (result){
                System.out.println("Change successfully !!!");
            }
            else {
                System.out.println("Change not successfully !!!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public void updateAllScore (){
        System.out.println("Enter score !!!");
        try{
            while (true) {
                float score = sc.nextFloat();
                if (studentEntity.setAverage_score(score)){
                    boolean result=studentDao.updateAllScore(score);
                    if (result){
                        System.out.println("Update successfully !!!");
                    }
                    else {
                        System.out.println("Update not successfully !!!");
                    }
                    break;
                }
                else {
                    System.err.println("Please retype !!!");
                }
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public Student inputStudent (){
        Student student = new Student();
        System.out.print("Enter first name : ");
        String firs_name = sc.nextLine();
        student.setFirst_name(firs_name);
        System.out.print("Enter last_name : ");
        String last_name = sc.nextLine();
        student.setLast_name(last_name);
        System.out.print("Enter city : ");
        String city = sc.nextLine();
        student.setCity(city);
        System.out.print("Enter average_score : ");
        while (true) {
            float average_score = sc.nextFloat();
            sc.nextLine();
            if (student.setAverage_score(average_score)){
                student.setAverage_score(average_score);
                break;
            }
            else {
                System.out.println("Retype !!!");
            }

        }
        System.out.print("Enter gender : ");
        char gender = sc.nextLine().charAt(0);
        student.setGender(gender);
        System.out.print("Enter day of birth : ");
        String dobString = sc.nextLine();
        LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ISO_LOCAL_DATE);
        student.setDob(dob);
        return student;
    }


}
