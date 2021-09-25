package com.java.connection.dao;


import com.java.connection.entity.Student;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

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

    public void closeConnection() throws SQLException{
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
            connection.close();
        }
    }
    public boolean addStudentWithoutClass(Student student) throws SQLException {
        String dob = student.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String query = "insert into student(id,last_name,first_name,city,average_score,gender,dob) values ("
                + student.getID() + ",'" + student.getLast_name() + "', '" + student.getFirst_name() + "', '" +
                student.getCity() + "', " + student.getAverage_score() + ", '" + student.getGender() + "', '" +
                dob + "')";
        Statement statement = getConnection().createStatement();
        int result = statement.executeUpdate(query);
        return result != 0;
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
        String findAll = "select * from Student;";
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
    public boolean deleteStudentByID (int ID) throws SQLException {
        String delete="delete from Student where id="+ID+";";
        Statement statement= getConnection().createStatement();
        int result=statement.executeUpdate(delete);
        return result != 0;
    }
    public Student findMaxScore() throws SQLException {
        String findMaxScore = "select * from Student where average_score=(select max(average_score) from student);";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(findMaxScore);
        if (resultSet.next()) {
            Student student = new Student(resultSet);
            return student;
        }
        System.out.println("Not found !!!");
        return null;
    }
    public boolean changePlace2Student(Student student1,Student student2)throws SQLException{
        String dob1 = student1.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String dob2 = student2.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String modify1="update Student set last_name='"+student2.getLast_name()+"',first_name='"+student2.getFirst_name()+"',city='"+student2.getCity()+"',average_score='"+student2.getAverage_score()+"',gender='"+student2.getGender()+"',dob='"+dob2+"' where id='"+student1.getID()+"';";
        String modify2="update Student set last_name='"+student1.getLast_name()+"',first_name='"+student1.getFirst_name()+"',city='"+student1.getCity()+"',average_score='"+student1.getAverage_score()+"',gender='"+student1.getGender()+"',dob='"+dob1+"' where id='"+student2.getID()+"';";
        Statement statement= getConnection().createStatement();
        int resultSet1=statement.executeUpdate(modify1);
        int resultSet2=statement.executeUpdate(modify2);
        return resultSet1 != 0 && resultSet2 != 0;
    }
    public boolean updateAllScore(float score) throws SQLException {
        String updateAScore="update student set average_score="+score+";";
        Statement statement= getConnection().createStatement();
        int result=statement.executeUpdate(updateAScore);
        return result > 0;
    }

}
