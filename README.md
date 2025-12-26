# Hibernate Entity Update & Merge Example

## Overview
This project demonstrates how Hibernate manages entity state transitions and updates data using **automatic dirty checking** and the **`merge()`** method. It focuses on real-world scenarios where entities are updated both within an active session and after a session has been closed (detached state).

The application shows:
- Updating a **persistent entity** without explicitly calling `persist()` or `merge()`
- Updating a **detached entity** using `merge()` with a new session

---

## Technologies Used
- Java
- Hibernate ORM
- Maven
- MySQL
- JDBC

---

## Key Concepts Covered
- Hibernate Entity States:
  - Transient
  - Persistent
  - Detached
- Automatic Dirty Checking
- Session and Transaction management
- Updating detached entities using `merge()`

---

## Project Flow Explanation

### Case 1: Update Using Persistent State (No `merge()`)
- Entity is fetched using `session.get()`
- Session remains open
- Changes are automatically detected by Hibernate
- Update is executed during transaction commit

### Case 2: Update Using Detached State (`merge()`)
- Session is closed after fetching the entity
- Entity becomes detached
- Entity is modified outside the session
- A new session is opened
- `merge()` is used to synchronize changes with the database

---

## Why `merge()` is Used
`merge()` is required when an entity is modified after its original Hibernate session has been closed. Since Hibernate no longer tracks the object, `merge()` re-synchronizes the detached entity’s state with the database using a new session.

---

## Database Configuration
- Database: MySQL
- Dialect: `org.hibernate.dialect.MySQLDialect`
- Driver: `com.mysql.cj.jdbc.Driver`

---

## How to Run
1. Configure MySQL database and update `hibernate.cfg.xml`
2. Build the project using Maven
3. Run the `App` class
4. Enter the student ID and updated values when prompted

---

## Learning Outcome
This project provides a clear understanding of:
- How Hibernate tracks entity changes
- When `merge()` is required
- Proper session handling in real-world Hibernate applications

---

## Author
Developed as part of hands-on Hibernate ORM practice using Java and MySQL.
