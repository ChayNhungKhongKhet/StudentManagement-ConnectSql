package com.java.connection.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Class {
    private  int ID;
    private String className;
    private String major;

    public Class( String className,String major) {
        this.className = className;
        this.major = major;
    }
    public Class(ResultSet resultSet) throws SQLException {
        this.ID=resultSet.getInt(1);
        this.className=resultSet.getString(2);
        this.major=resultSet.getString(3);
    }

    @Override
    public String toString() {
        return "[" +
                "ID=" + ID +
                ", className='" + className + '\'' +
                ", major='" + major + '\'' +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return ID == aClass.ID && Objects.equals(major, aClass.major) && Objects.equals(className, aClass.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, ID, className);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
