package edu.sece.cse;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnect {

	
	private static final String URL = "jdbc:mysql://localhost:3306/secedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Sumsharid@0403";
    private static Connection con = null;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        while (true) {
            printMenu();
            int ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    performInsert(scanner);
                    break;
                case 2:
                    performDelete(scanner);
                    break;
                case 3:
                    performUpdate(scanner);
                    break;
                case 4:
                    performDisplay();
                    break;
                case 5:
                    performSearch(scanner);
                    break;
                case 6:
                    performCount();
                    break;
                case 7:
                    performOrder();
                    break;
                case 8:
                    performAdvancedFilter(scanner);
                    break;
                case 9:
                    System.out.println("\n\t\tExit operation");
                    System.exit(1);
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n\t\tDatabase Operation");
        System.out.println("\n\t\t1.Insert");
        System.out.println("\n\t\t2.Delete");
        System.out.println("\n\t\t3.Update");
        System.out.println("\n\t\t4.Display");
        System.out.println("\n\t\t5.Search");
        System.out.println("\n\t\t6.Count");
        System.out.println("\n\t\t7.Order");
        System.out.println("\n\t\t8.Advanced Filter");
        System.out.println("\n\t\t9.Exit");
        System.out.println("\n\t\tEnter Your Choice(1/2/3/4/5/6/7/8/9)");
    }

    private static void performInsert(Scanner scanner) throws Exception {
        System.out.println("\n\t\tInsert Operation");
        System.out.println("\n\t\tEnter Name=");
        String name = scanner.next();
        System.out.println("\n\t\tEnter Age=");
        int age = scanner.nextInt();

        PreparedStatement ps1 = con.prepareStatement("INSERT INTO emp (name, age) VALUES (?, ?)");
        ps1.setString(1, name);
        ps1.setInt(2, age);
        ps1.execute();
    }

    private static void performDelete(Scanner scanner) throws Exception {
        System.out.println("\n\t\tDelete Operation");
        System.out.println("\n\t\tEnter Name=");
        String name1 = scanner.next();
        PreparedStatement ps2 = con.prepareStatement("DELETE FROM emp WHERE name = ?");
        ps2.setString(1, name1);
        ps2.execute();
    }

    private static void performUpdate(Scanner scanner) throws Exception {
        System.out.println("\n\t\tUpdate Operation");
        System.out.println("\n\t\tEnter Name=");
        String name2 = scanner.next();
        System.out.println("\n\t\tEnter Age=");
        int age2 = scanner.nextInt();
        PreparedStatement ps3 = con.prepareStatement("UPDATE emp SET age = ? , name = ?");
        ps3.setInt(1, age2);
        ps3.setString(2, name2);
        ps3.executeUpdate();
    }

    private static void performDisplay() throws Exception {
        System.out.println("\n\t\tDisplay Operation");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM emp");
        System.out.println("\n\tName\tAge");
        while (rs.next()) {
            System.out.println("\n\t" + rs.getString("name") + "\t" + rs.getInt("age"));
        }
    }

    private static void performSearch(Scanner scanner) throws Exception {
        System.out.println("\n\t\t Search Operation");
        System.out.println("\n\tEnter Name=");
        String name = scanner.next();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM emp WHERE name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        System.out.println("\n\tName\tAge");
        while (rs.next()) {
            System.out.println("\n\t" + rs.getString("name") + "\t" + rs.getInt("age"));
        }
    }

    private static void performCount() throws Exception {
        System.out.println("\n\t\tCount Operation");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM emp");
        if (rs.next()) {
            System.out.println("\n\tTotal Records = " + rs.getInt(1));
        }
    }

    private static void performOrder() throws Exception {
        System.out.println("\n\t\tOrder Operation");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM emp ORDER BY name");
        System.out.println("\n\tName\tAge");
        while (rs.next()) {
            System.out.println("\n\t" + rs.getString("name") + "\t" + rs.getInt("age"));
        }
    }

    private static void performAdvancedFilter(Scanner scanner) throws Exception {
        System.out.println("\n\t\tAdvanced Filter Operation");
        System.out.println("\n\tEnter minimum age = ");
        int minAge = scanner.nextInt();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM emp WHERE age >= ?");
        ps.setInt(1, minAge);
        ResultSet rs = ps.executeQuery();
        System.out.println("\n\tName\tAge");
        while (rs.next()) {
            System.out.println("\n\t" + rs.getString("name") + "\t" + rs.getInt("age"));
        }
    }
}
