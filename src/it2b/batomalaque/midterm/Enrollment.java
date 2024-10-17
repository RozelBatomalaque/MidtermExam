package it2b.batomalaque.midterm;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Enrollment {
    public int enrollmentId;
    public String enrollmentDate;
    public String status;
    public String semester;

    // Constructor for new enrollments
    public Enrollment(String enrollmentDate, String status, String semester) {
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.semester = semester;
    }

    // Constructor for existing enrollments
    public Enrollment(int enrollmentId, String enrollmentDate, String status, String semester) {
        this.enrollmentId = enrollmentId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.semester = semester;
    }

    // Default constructor
    Enrollment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Method to display enrollment details
    public void displayEnrollment() {
        System.out.println("Enrollment ID: " + enrollmentId);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Status: " + status);
        System.out.println("Semester: " + semester);
        System.out.println();
    }

    // New method to create an Enrollment object from user input
    public static Enrollment createEnrollmentFromInput(Scanner scanner) {
        System.out.print("Enter status (e.g., 'Active', 'Inactive'): ");
        String status = scanner.nextLine();

        System.out.print("Enter semester (e.g., 'Fall 2024', 'Spring 2025'): ");
        String semester = scanner.nextLine();

        // Get the current date for enrollment
        String enrollmentDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        // Create a new Enrollment object with the provided data
        return new Enrollment(enrollmentDate, status, semester);
    }
}
