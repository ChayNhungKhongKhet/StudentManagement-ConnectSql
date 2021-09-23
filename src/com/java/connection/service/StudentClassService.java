package com.java.connection.service;

import com.java.connection.dao.ClassDao;
import com.java.connection.dao.StudentClassDao;
import com.java.connection.entity.Class;
import com.java.connection.entity.Student;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class StudentClassService {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentClassDao studentClassDao = new StudentClassDao();
    private static final ClassService classService = new ClassService();
    private static final ClassDao classDao = new ClassDao();

    public void addStudent() {
        Student student = inputStudent();
        if (student == null) {
            System.out.println("Add not successfully");
            return;
        }
        try {
            boolean result = studentClassDao.addStudent(student);
            if (result) {
                System.out.println("Add successfully !!!");
            } else {
                System.out.println("Add not successfully !!!");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Student inputStudent() {
        Student student = new Student();
        System.out.print("Enter last name : ");
        String last_name = sc.nextLine();
        student.setLast_name(last_name);
        System.out.print("Enter last_name : ");
        String first_name = sc.nextLine();
        student.setFirst_name(first_name);
        System.out.print("Enter city : ");
        String city = sc.nextLine();
        student.setCity(city);
        System.out.print("Enter average_score : ");
        while (true) {
            float average_score = sc.nextFloat();
            sc.nextLine();
            if (student.setAverage_score(average_score)) {
                student.setAverage_score(average_score);
                break;
            } else {
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
        System.out.println("Enter id of class : ");
        classService.findAll();
        int id_class = sc.nextInt();
        try {
            Class studentClass = classDao.findByID(id_class);
            if (studentClass != null) {
                student.setStudentClass(studentClass);
            } else {
                System.out.println("ID doesn't exist !!!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return student;
    }

    public void findAllStudentOfClass() {
        classService.findAll();
        System.out.println("Enter id of class !!!");
        int id = sc.nextInt();
        ResultSet resultSet = null;
        try {
            resultSet = studentClassDao.findAllStudentOfClass(id);
            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            System.out.printf("%-2s%21s%17s%16s%n%n", resultSetMetaData.getColumnName(1), resultSetMetaData.getColumnName(2),
                    resultSetMetaData.getColumnName(3), resultSetMetaData.getColumnName(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {

                System.out.printf("%-2d%20s%15s%15s%n", resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void findNumberStudentOfMajor() {
        ResultSet resultSet = null;
        try {
            resultSet = studentClassDao.findNumberStudentOfMajor();
            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            System.out.printf("%s%20s%n", resultSetMetaData.getColumnName(1), resultSetMetaData.getColumnName(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next())
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                System.out.printf("%10d%30s%n", resultSet.getInt(1), resultSet.getString(2));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void findMaxScoreEachClass() {
        try {
            ResultSet resultSet = studentClassDao.findMaxScoreEachClass();
            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            System.out.printf("%s | %14s | %17s | %16s | %16s | %n%n", resultSetMetaData.getColumnName(1), resultSetMetaData.getColumnName(2),
                    resultSetMetaData.getColumnName(3), resultSetMetaData.getColumnName(4), resultSetMetaData.getColumnName(5));
            while (resultSet.next()) {
                System.out.printf("%13.2f | %14d | %17s | %16s | %16s | %n", resultSet.getFloat(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void findNumberStudentEachCity() {
        try {
            ResultSet resultSet = studentClassDao.findNumberStudentEachCity();
            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            System.out.printf("%s | %14s | %17s | %16s |%n%n", resultSetMetaData.getColumnName(1), resultSetMetaData.getColumnName(2),
                    resultSetMetaData.getColumnName(3), resultSetMetaData.getColumnName(4));
            while (resultSet.next()) {
                System.out.printf("%2d | %14s | %17s | %16s |%n",  resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
