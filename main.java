
Shubham Sawant <shubhamvs3011@gmail.com>
Wed, 18 Sept, 14:37
to Raj

package hello_world;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.Scanner;



public class Main {

    private static final String url = "jdbc:mysql://10.10.13.97:3306/te31367_db";

    private static final String user = "te31367";

    private static final String password = "te31367";



    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Connection successful!");



            createTable(con);

            Scanner scanner = new Scanner(System.in);

            boolean exit = false;



            while (!exit) {

                System.out.println("Choose an option: (1) Insert (2) Update (3) Delete (4) Exit");

                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:

                        insertRecord(con, scanner);

                        break;

                    case 2:

                        updateRecord(con, scanner);

                        break;

                    case 3:

                        deleteRecord(con, scanner);

                        break;

                    case 4:

                        exit = true;

                        break;

                    default:

                        System.out.println("Invalid choice. Please try again.");

                }

            }



            scanner.close();

            con.close();

        } catch (ClassNotFoundException e) {

            System.err.println("JDBC Driver Not Found");

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    private static void createTable(Connection con) {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Jdbc_table (name VARCHAR(100), roll_no INT PRIMARY KEY, marks INT)";

        try (Statement stmt = con.createStatement()) {

            stmt.executeUpdate(createTableSQL);

            System.out.println("Table created successfully.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    private static void insertRecord(Connection con, Scanner scanner) {

        System.out.print("Enter name: ");

        String name = scanner.next();

        System.out.print("Enter roll number: ");

        int rollNo = scanner.nextInt();

        System.out.print("Enter marks: ");

        int marks = scanner.nextInt();



        String insertSQL = "INSERT INTO Jdbc_table (name, roll_no, marks) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(insertSQL)) {

            pstmt.setString(1, name);

            pstmt.setInt(2, rollNo);

            pstmt.setInt(3, marks);

            pstmt.executeUpdate();

            System.out.println("Record inserted successfully.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    private static void updateRecord(Connection con, Scanner scanner) {

        System.out.print("Enter roll number of the record to update: ");

        int rollNo = scanner.nextInt();

        System.out.print("Enter new marks: ");

        int marks = scanner.nextInt();



        String updateSQL = "UPDATE Jdbc_table SET marks = ? WHERE roll_no = ?";

        try (PreparedStatement pstmt = con.prepareStatement(updateSQL)) {

            pstmt.setInt(1, marks);

            pstmt.setInt(2, rollNo);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {

                System.out.println("Record updated successfully.");

            } else {

                System.out.println("Record not found.");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    private static void deleteRecord(Connection con, Scanner scanner) {

        System.out.print("Enter roll number of the record to delete: ");

        int rollNo = scanner.nextInt();



        String deleteSQL = "DELETE FROM Jdbc_table WHERE roll_no = ?";

        try (PreparedStatement pstmt = con.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, rollNo);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {

                System.out.println("Record deleted successfully.");

            } else {

                System.out.println("Record not found.");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}