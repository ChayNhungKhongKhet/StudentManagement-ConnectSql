package com.java.connection.app;

import com.java.connection.service.StudentService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome Student management system!");
        showMenu();
        while (true) {
            System.out.println("Enter funtions");
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
                    //                   studentService.deleteStudentById();
                    break;
                case 4:
                    //                   studentService.modifyStudent();
                    break;

                case 5:
                    studentService.findById();
                    break;
                case 6:
                    showMenu();
                    break;

                case 7:
                    //                   classService.findStudentByClassName();
                    break;
                case 8:
//                    classService.findStudentByMajor();
                    break;
                case 10:
//                    studentService.modifyDobById();
                    break;
                case 11:
                    studentService.findByName();
                    break;
                case 12:
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
        System.out.println("4. Update student name");
        System.out.println("5. Search student By Id");
        System.out.println("6. Show menu");
//        System.out.println("7. List Students By Class name");
//        System.out.println("8. List Students By major");
		/*System.out.println("7. Tinh tuoi trung binh");
		System.out.println("8. Tim sinh vien co diem cao nhat");
		System.out.println("9. Sua diem toan  sinh vien");
		System.out.println("10. Sua ngay sinh");*/
        System.out.println("11. Exist");
        /*System.out.println("12.Sua diem tat ca");*/
    }
}
