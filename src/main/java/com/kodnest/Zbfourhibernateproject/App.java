package com.kodnest.Zbfourhibernateproject;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SessionFactory factory =
                new Configuration().configure("hibernate.cfg.xml")
                        .buildSessionFactory();

        // ---------------- CASE 1 ----------------
        // Session OPEN → Persistent object → NO merge needed

        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        System.out.println("Enter the id of student to update");
        int a = sc.nextInt();

        Student std = session.get(Student.class, a); // persistent object

        if (std != null) {
            System.out.println("Enter the new name and email");
            String name = sc.next();
            String email = sc.next();

            std.setName(name);
            std.setEmail(email);

            // No merge here
            // Hibernate auto-updates because object is persistent
            System.out.println("Updated using dirty checking (no merge)");
        } else {
            System.out.println("Student not found");
        }

        trans.commit();
        session.close(); // object becomes DETACHED here

        // ---------------- CASE 2 ----------------
        // Object is DETACHED → merge REQUIRED

        System.out.println("Updating again using merge...");
        System.out.println("Enter new name and email again");

        String newName = sc.next();
        String newEmail = sc.next();

        // modifying detached object
        std.setName(newName);
        std.setEmail(newEmail);

        // new session
        Session session2 = factory.openSession();
        Transaction trans2 = session2.beginTransaction();

        session2.merge(std); // MERGE happens here

        trans2.commit();
        session2.close();

        factory.close();
        sc.close();
    }
}
