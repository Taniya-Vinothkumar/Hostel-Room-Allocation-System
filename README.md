# 🏠 Hostel Room Allocation System

A Java Swing desktop application for managing students, hostels, rooms, and hostel room allocations using JDBC and MySQL.

## 📌 Project Overview

The **Hostel Room Allocation System** is a database-driven desktop application developed using **Java Swing, JDBC, and MySQL**.

The application provides a simple graphical interface to manage hostel-related information and perform CRUD operations. It also checks room availability before allocating a room to a student and automatically maintains the room's occupied count.

## 🎯 Objectives

* Manage student information.
* Manage hostel information.
* Manage room details.
* Allocate rooms to students.
* Check room availability based on capacity.
* Maintain room occupancy.
* Perform Create, Read, Update, and Delete operations.
* Demonstrate Java-to-MySQL connectivity using JDBC.

## 🛠️ Technologies Used

| Technology | Purpose                  |
| ---------- | ------------------------ |
| Java       | Application development  |
| Java Swing | Graphical User Interface |
| JDBC       | Database connectivity    |
| MySQL      | Relational database      |
| Eclipse    | Development environment  |
| XAMPP      | MySQL server             |

## 🏗️ Project Architecture

```text
Java Swing UI
      ↓
DAO Classes
      ↓
JDBC
      ↓
MySQL Database
```

## 📂 Project Structure

```text
HostelRoomAllocation
│
├── src
│   ├── db
│   │   └── DBConnection.java
│   │
│   ├── model
│   │   ├── Student.java
│   │   ├── Hostel.java
│   │   ├── Room.java
│   │   └── Allocation.java
│   │
│   ├── dao
│   │   ├── StudentDAO.java
│   │   ├── HostelDAO.java
│   │   ├── RoomDAO.java
│   │   └── AllocationDAO.java
│   │
│   └── ui
│       ├── StudentFrame.java
│       ├── HostelFrame.java
│       ├── RoomFrame.java
│       ├── AllocationFrame.java
│       └── MainFrame.java
│
└── README.md
```

## ✨ Features

### 👨‍🎓 Student Management

* Add student
* View student records
* Update student details
* Delete student details
* Store department, year, phone number, and gender

### 🏢 Hostel Management

* Add hostel
* View hostel records
* Update hostel details
* Delete hostel details
* Categorize hostels as MEN or WOMEN

### 🚪 Room Management

* Add room
* View room records
* Update room details
* Delete room details
* Maintain floor and room number
* Maintain room type
* Maintain room capacity
* Track occupied students

### 🛏️ Room Allocation

* Select a student
* Identify the student's gender
* Display suitable hostels
* Display available rooms
* Check room capacity
* Allocate a room
* Store allocation date
* Automatically increase occupied count
* Delete allocation and restore room occupancy

## 🗄️ Database Design

The project uses a MySQL database named:

```text
hostel_db
```

### Tables

```text
students
hostels
rooms
allocations
```

### Relationships

```text
HOSTELS
   │
   │ 1 : N
   ↓
ROOMS
   │
   │ 1 : N
   ↓
ALLOCATIONS
   ↑
   │
   │ N : 1
STUDENTS
```

Primary keys and foreign keys are used to maintain relationships between the tables.

## 🔄 Application Workflow

```text
Start Application
       ↓
    Main Menu
       ↓
Manage Students
       ↓
Manage Hostels
       ↓
Manage Rooms
       ↓
Room Allocation
       ↓
Select Student
       ↓
Check Student Gender
       ↓
Display Matching Hostels
       ↓
Display Available Rooms
       ↓
Check Room Capacity
       ↓
Allocate Room
       ↓
Update Occupied Count
       ↓
Store Allocation in MySQL
```

## 🔍 Room Availability

A room is considered available when:

```text
occupied < capacity
```

For example:

```text
Capacity = 3
Occupied = 2

2 < 3 → Room Available
```

If the room is full:

```text
Capacity = 3
Occupied = 3

3 < 3 → Room Not Available
```

This prevents students from being allocated beyond the room capacity.

## 🔗 JDBC Connectivity

The application uses **JDBC (Java Database Connectivity)** to communicate with MySQL.

The connection flow is:

```text
Java Application
      ↓
JDBC Driver
      ↓
MySQL Database
```

DAO classes are used to execute database operations separately from the user interface.

## 📋 CRUD Operations

The application supports:

* **Create** → Insert new records
* **Read** → View records
* **Update** → Modify records
* **Delete** → Remove records

CRUD operations are implemented for the major modules of the application.

## ▶️ How to Run

### 1. Start MySQL

Start **MySQL** using XAMPP.

### 2. Create the Database

Create the database:

```sql
CREATE DATABASE hostel_db;
```

### 3. Create the Required Tables

Create the following tables:

```text
students
hostels
rooms
allocations
```

using the SQL scripts provided with the project.

### 4. Configure JDBC

Update the database connection details in:

```text
src/db/DBConnection.java
```

Example:

```java
jdbc:mysql://localhost:3306/hostel_db
```

### 5. Add MySQL Connector

Add **MySQL Connector/J** to the project's referenced libraries.

### 6. Run the Application

Run:

```text
src/ui/MainFrame.java
```

The main menu will open and provide access to all modules.

## 📸 Application Modules

The application contains the following main screens:

1. Main Menu
2. Student Management
3. Hostel Management
4. Room Management
5. Room Allocation

Screenshots can be added to this README after capturing the application screens.

## 🎓 Academic Project

This project was developed as a **Java database term-work project** to demonstrate practical implementation of:

* Java programming
* Object-oriented programming
* Java Swing
* JDBC
* SQL
* MySQL
* Relational database design
* CRUD operations
* Primary and foreign keys

## 🚀 Future Enhancements

Possible future improvements include:

* Administrator login
* Student login
* Hostel fee management
* Check-in and check-out management
* Search and filtering
* Automatic room assignment
* Hostel vacancy reports
* Email notifications
* Web or mobile version

## 👩‍💻 Author

**Taniya Vinothkumar**

Computer Science and Engineering

---

⭐ **Hostel Room Allocation System | Java Swing + JDBC + MySQL**
