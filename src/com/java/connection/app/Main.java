package com.java.connection.app;

import com.java.connection.service.ClassService;
import com.java.connection.service.StudentClassService;
import com.java.connection.service.StudentService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClassService classService = new ClassService();
    private static  final StudentClassService studentClassService = new StudentClassService();

    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome Student management system!");
        showMenu();
        while (true) {
            System.out.println("Enter functions");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    studentService.findAll();
                    break;
                case 2:
                    studentService.addStudent();
                    break;
                case 3:
                    studentService.deleteStudentByID();
                    break;
                case 4:
                    studentService.changePlace2Student();
                    break;
                case 5:
                    studentService.findById();
                    break;
                case 6:
                    showMenu();
                    break;
                case 7:
                    studentService.findMaxScore();
                    break;
                case 8:
                    studentService.updateAllScore();
                    break;
                case 9:
                    studentService.findByName();
                    break;
                case 10:
                    studentClassService.findNumberStudentOfMajor();
                    break;
                case 11:
                    classService.findAll();
                    break;
                case 12:
                    studentClassService.addStudent();
                    break;
                case 13:
                    classService.addClass();
                    break;
                case 14:
                    studentClassService.findAllStudentOfClass();
                    break;
                case 15:
                    studentClassService.findMaxScoreEachClass();
                    break;
                case 16:
                    studentClassService.findNumberStudentEachCity();
                    break;
                case 17:
                    studentClassService.findAllStudentOfMajor();
                    break;
                case 18:
                    System.out.println("Good bye.");
                    return;
                default:
                    System.out.println("Not a option. Please choose again");
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. List Student");
        System.out.println("2. Add Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Change place 2 student");
        System.out.println("5. Search student By Id");
        System.out.println("6. Show menu");
        System.out.println("7. Find a student max score");
        System.out.println("8. Update all score of student");
        System.out.println("9.Find by name");
        System.out.println("10. Exist");
    }
}
