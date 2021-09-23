package com.java.connection.dao;

import com.java.connection.entity.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClassDao {
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

    public List<Class> findAll() throws SQLException {
        String findAll = "Select * from class ";
        Statement statement = getConnection().createStatement();
        List<Class> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(findAll);
        while (resultSet.next()){
            Class classFind=new Class(resultSet);
            list.add(classFind);
        }
        return list;
    }
    public Class findByID(int ID) throws SQLException {
        String findByID = "Select * from class where id="+ID+"";
        Statement statement = getConnection().createStatement();;
        ResultSet resultSet = statement.executeQuery(findByID);
        if (resultSet.next()){
            return new Class(resultSet);
        }
        return null;
    }
    public boolean addClass(Class classAdd) throws SQLException {
        String addClass = "insert into class(class_name,major) value('"+classAdd.getClassName()+"','"+classAdd.getMajor()+"')";
        Statement statement = getConnection().createStatement();
        int result = statement.executeUpdate(addClass);
        return result != 0;
    }
    public boolean deleteClass(int id) throws SQLException {
        String delete = "delete from class where id="+id+"";
        Statement statement = getConnection().createStatement();
        int result = statement.executeUpdate(delete);
        return result != 0;
    }


}
