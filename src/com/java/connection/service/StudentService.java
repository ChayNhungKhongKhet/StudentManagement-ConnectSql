package com.java.connection.service;

import com.java.connection.dao.StudentDao;
import com.java.connection.entity.Student;
import com.sun.javafx.iio.gif.GIFImageLoaderFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    static final StudentDao studentDao = new StudentDao();
    Scanner sc = new Scanner(System.in);
    Boolean result;
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

    public Student addStudent() throws SQLException {
        System.out.print("Enter id : ");
//        List<Student> studentList = studentDao.findAll();
//        while (true) {
            int ID = sc.nextInt();
//            for (Student stn : studentList) {
//                if (stn.getID() == ID) {
//                    System.err.println("Student exist !!!!");
//                    break;
//                }
//            }
//        }

            sc.nextLine();
            System.out.print("Enter first name : ");
            String firs_name = sc.nextLine();
            System.out.print("Enter last_name : ");
            String last_name = sc.nextLine();
            System.out.print("Enter city : ");
            String city = sc.nextLine();
            System.out.print("Enter average_score : ");
            float average_score = sc.nextFloat();
            sc.nextLine();
            System.out.print("Enter gender : ");
            char gender = sc.nextLine().charAt(0);
            System.out.print("Enter day of birth : ");
            String dobString = sc.nextLine();
            LocalDate dobLocal = LocalDate.parse(dobString);
            ZonedDateTime dob = dobLocal.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh"));
            Student student = new Student(ID, firs_name, last_name, city, average_score, gender, dob);
            result=studentDao.addStudent(student);
            if (!result) {
                System.err.println("Not successfully !!!");
                return null;
            } else {
                System.out.println("Successfully !!!");
            }
            return student;
    }
}
