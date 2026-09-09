package com.mycompany.quickchat;

import java.util.Scanner;

public class QuickChat {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

System.out.print("Enter your first name: ");
String firstName = scanner.nextLine();

System.out.print("Enter your last name: ");
String lastName = scanner.nextLine();

System.out.print("Enter your username: ");
String userName = scanner.nextLine();

System.out.print("Enter your password: ");
String password = scanner.nextLine();

System.out.print("Enter your cell phone number: ");
String cellPhoneNumber = scanner.nextLine();

Login login = new Login(userName, password, cellPhoneNumber, firstName, lastName);

System.out.println(login.registerUser());

System.out.println("\nLogin");

System.out.print("Enter your username: ");
String enteredUserName = scanner.nextLine();

System.out.print("Enter your password: ");
String enteredPassword = scanner.nextLine();

System.out.println(login.returnLoginStatus(enteredUserName, enteredPassword));
        

    }
    
}