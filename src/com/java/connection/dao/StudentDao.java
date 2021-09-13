package com.java.connection.dao;


import com.java.connection.entity.Student;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private Connection connection;

    public Connection getConnection() {
        if(connection==null)
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","kokoko2002@");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void closeConnection() throws SQLException{
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
            connection.close();
        }
    }
    public boolean addStudent(Student student) throws SQLException {
        String dob = student.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String query = "insert into student(id,first_name,last_name,city,average_score,gender,dob) values ("
                + student.getID() + ",'" + student.getFirst_name() + "', '" + student.getLast_name() + "', '" +
                student.getCity() + "', " + student.getAverage_score() + ", '" + student.getGender() + "', '" +
                dob + "')";
        Statement statement = getConnection().createStatement();
        int result = statement.executeUpdate(query);
        if(result != 0) {
            System.out.println("Add Student successfully");
            return true;
        }
        return false;
    }
    public Student findByID(int ID) throws SQLException {
        String findByID="select * from Student where id  ="+ID+";";
        Statement statement= getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(findByID);
        if (resultSet.next()){
            Student student=new Student(resultSet);
            return student;
        }
        return null;
    }
    public List<Student> findAll() throws SQLException {
        List<Student> listStudentFAll = new ArrayList<>();
        String findAll = "select * from Student ;";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(findAll);
        while (resultSet.next()) {
            Student student = new Student(resultSet);
            listStudentFAll.add(student);

        }
        return listStudentFAll;
    }
    public List<Student> findByName(String name) throws SQLException{
        List<Student> studentListFName = new ArrayList<>();
        String findAll = "select * from Student where first_name like '"+name+"%';";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(findAll);
        while (resultSet.next()) {
            Student student = new Student(resultSet);
            studentListFName.add(student);

        }
        return studentListFName;
    }

}
