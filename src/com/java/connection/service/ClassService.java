package com.java.connection.service;

import com.java.connection.dao.ClassDao;
import com.java.connection.entity.Class;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClassService {
    private final static ClassDao classDao=new ClassDao();
    private final static Scanner sc=new Scanner(System.in);

    public void findAll()  {

        try {
            List<Class> classList = classDao.findAll();
            for (Class classI: classList){
                System.out.println(classI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void findByID(){
        try {
            Class classFindByID = classDao.findByID(sc.nextInt());
            sc.nextLine();
            if (classFindByID==null){
                System.out.println("Not found !!!");
                return;
            }
            else{
                System.out.println(classFindByID);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void addClass(){
        System.out.println("Enter class name ");
        String className = sc.nextLine();
        System.out.println("Enter major");
        String major = sc.nextLine();
        Class addClass = new Class(className,major);
        try {
            boolean result=classDao.addClass(addClass);
            if (result){
                System.out.println("Add successfully !!!");
            }
            else {
                System.out.println("Add not successfully !!!");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteClass (){
        try {
            System.out.println("Enter id of class want to delete !!!");
            classDao.deleteClass(sc.nextInt());
            sc.nextLine();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
