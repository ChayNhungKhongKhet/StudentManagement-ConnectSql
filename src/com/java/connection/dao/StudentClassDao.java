package com.java.connection.dao;

import com.java.connection.entity.Class;
import com.java.connection.entity.Student;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentClassDao {
    private Connection connection;
    public Connection getConnection() {
        if(connection==null)
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentClassDatabase","root","kokoko2002@");
            } catch (SQLException e) {
                System.out.println(e);
            }
        return null;
    }
    public boolean addStudent(Student student) throws SQLException {
        String dob=student.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String add = "insert into student values("+student.getID()+", '"+student.getLast_name()+"', '"+
                student.getFirst_name()+"', '"+student.getCity()+"', "+
                student.getAverage_score()+", '"+student.getGender()+"', '"+dob+"', "+student.getStudentClass().getID()+")";
        Statement statement= getConnection().createStatement();
        int resultSet= statement.executeUpdate(add);
        return resultSet != 0;
    }
    public ResultSet findAllStudentOfClass(int id) throws SQLException{
        String findNumber = "SELECT Student.ID,student.last_name,student.first_name, Class.class_name FROM Student " +
                "JOIN Class ON Student.class_id = Class.ID WHERE Class.ID = "+id+"";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(findNumber);
        return resultSet;
    }
    public ResultSet findNumberStudentOfMajor() throws SQLException {
        List<Class> classList=new ArrayList<>();
        String query="SELECT count(Student.ID) as student_number, major FROM Student JOIN Class ON Student.class_id = Class.ID GROUP BY major";
        Statement statement= getConnection().createStatement();
        ResultSet resultSet= statement.executeQuery(query);
        return resultSet;
    }
    public ResultSet findMaxScoreEachClass() throws SQLException {
        String findNumber = "select max(student.average_score) as highest_point,student.ID,student.last_name," +
                "student.first_name,class.class_name from student join class on student.class_id=class.ID GROUP BY class.class_name;";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(findNumber);
        return resultSet;
    }
    public ResultSet findNumberStudentEachCity() throws SQLException {
        String findAverage = "select student.id ,student.last_name,student.first_name,student.city from student" +
                " left join class on student.class_id=class.ID  ORDER BY city;";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(findAverage);
        return resultSet;
    }


}
