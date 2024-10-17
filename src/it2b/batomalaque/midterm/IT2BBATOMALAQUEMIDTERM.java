package it2b.batomalaque.midterm;

import java.sql.*; 
import java.text.SimpleDateFormat; 
import java.util.Scanner; 

public class IT2BBATOMALAQUEMIDTERM { 

    public static void main(String[] args) { 
        Connection conn = null;
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enrollment\"");
            System.out.println("Connected to the database!");

            Scanner scanner = new Scanner(System.in); 
            int choice; 

            do { 
                
                System.out.println("1. Add Enrollment "); 
                System.out.println("2. View Enrollments "); 
                System.out.println("3. Update Enrollment "); 
                System.out.println("4. Delete Enrollment "); 
                System.out.println("5. Exit"); 
                System.out.print("Enter your choice: "); 
                choice = scanner.nextInt(); 
                scanner.nextLine(); 

                switch (choice) { 
                    case 1: 
                        addEnrollment(conn, scanner); 
                        break; 
                    case 2: 
                        viewEnrollments(conn); 
                        break; 
                    case 3: 
                        updateEnrollment(conn, scanner); 
                        break; 
                    case 4: 
                        deleteEnrollment(conn, scanner); 
                        break; 
                    case 5: 
                        System.out.println("Exiting..."); 
                        break; 
                    default: 
                        System.out.println("Invalid choice. Try again."); 
                } 
            } while (choice != 5); 

            scanner.close(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } finally {
            if (conn != null) {
                try {
                    conn.close(); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
    public static void addEnrollment(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter status (e.g., 'Active', 'Inactive'): ");
        String status = scanner.nextLine();

        System.out.print("Enter semester (e.g., 'Fall 2024', 'Spring 2025'): ");
        String semester = scanner.nextLine();

        
        String enrollmentDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        
        String sql = "INSERT INTO Enrollment (enrollment_date, status, semester) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, enrollmentDate);
        pstmt.setString(2, status);
        pstmt.setString(3, semester);
        pstmt.executeUpdate();

        System.out.println("Enrollment added successfully!\n");
    }

    
    public static void viewEnrollments(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Enrollment";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.isBeforeFirst()) { 
            System.out.println("No enrollments found.\n");
        } else {
            while (rs.next()) {
                int enrollmentId = rs.getInt("enrollment_id");
                String enrollmentDate = rs.getString("enrollment_date");
                String status = rs.getString("status");
                String semester = rs.getString("semester");

                Enrollment enrollment = new Enrollment(enrollmentId, enrollmentDate, status, semester);
                enrollment.displayEnrollment();
            }
        }
    }

    // Method to update an existing enrollment
    public static void updateEnrollment(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the Enrollment ID to update: ");
        int enrollmentId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        System.out.print("Enter new status (e.g., 'Active', 'Inactive'): ");
        String status = scanner.nextLine();

        System.out.print("Enter new semester (e.g., 'Fall 2024', 'Spring 2025'): ");
        String semester = scanner.nextLine();

        // SQL statement to update the enrollment
        String sql = "UPDATE Enrollment SET status = ?, semester = ? WHERE enrollment_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, status);
        pstmt.setString(2, semester);
        pstmt.setInt(3, enrollmentId);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Enrollment updated successfully!\n");
        } else {
            System.out.println("No enrollment found with the provided ID.\n");
        }
    }

    // Method to delete an enrollment
    public static void deleteEnrollment(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the Enrollment ID to delete: ");
        int enrollmentId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        // SQL statement to delete the enrollment
        String sql = "DELETE FROM Enrollment WHERE enrollment_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, enrollmentId);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Enrollment deleted successfully!\n");
        } else {
            System.out.println("No enrollment found with the provided ID.\n");
        }
    }
}
