# Student Management System

![JavaFX](https://img.shields.io/badge/JavaFX-8%2B-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Status](https://img.shields.io/badge/Status-Active-brightgreen)

## Overview

This Student Management System provides a complete CRUD (Create, Read, Update, Delete) implementation for managing student records through a modern desktop interface. The application demonstrates proper software architecture and object-oriented programming principles while delivering a seamless user experience.  

![Screenshot 2025-05-16 160051](https://github.com/user-attachments/assets/b156d956-4fcf-4fc7-9f61-2a6737372c9d)  
        
## CRUD Functionality

### Create
- Add new student records with details
- Form validation ensures data integrity
- Grade validation limited to values 0-5

### Read
- View all student records in a sortable table
- Visual indicators for failing students (grade 0 rows highlighted in red)
- Persistent data between sessions

### Update
- Select and modify existing student information
- Dynamic button behavior converts "Add" to "Update" when editing
- Form pre-populates with selected student data

### Delete
- Remove selected student records from the database
- Clean form interface for new entries

### Additional Features
- **Clear Form** - Reset the input fields with a single click
- **Modern UI** - Uses JavaFX styling for a clean and professional look
- **Data Persistence** - Student data is saved to a file and reloads when the app starts

## Architecture

The application follows the MVC (Model-View-Controller) architecture pattern:

```
studentmanagement/
├── src/
│   ├── model/           # Data classes
│   │   ├── Person.java  # Base class
│   │   └── Student.java # Extends Person
│   ├── view/            # UI components
│   │   └── StudentManagementUI.java
│   ├── controller/      # Business logic
│   │   ├── StudentManageable.java
│   │   └── StudentManager.java
│   ├── util/            # Helper classes
│   │   ├── DataManager.java
│   │   └── Validator.java
│   └── App.java         # Main application entry point
├── resources/           # Static resources
│   └── style.css        # UI styling
└── bin/                 # Compiled classes
```

## Requirements

- Java 11 or higher
- JavaFX (included in JDK for Java 8-10, separate download for Java 11+)

## How to Run

1. Make sure you have Java and JavaFX installed
2. Compile the project:
   ```
   javac -d bin src/**/*.java
   ```
3. Run the application:
   ```
   java -cp bin App
   ```

If using Java 11 or higher and JavaFX is not included in your JDK:
```
java --module-path /path/to/javafx-sdk/lib --add-modules=javafx.controls,javafx.fxml -cp bin App
```

## Implementation Details

This application demonstrates:

1. **Object-Oriented Programming (OOP)**
   - Proper encapsulation with private attributes and getters/setters
   - Inheritance hierarchy (Person → Student)
   - Interface implementation (StudentManageable)
   - SOLID principles application

2. **Data Persistence**
   - Object serialization for saving and loading student records
   - Automatic data recovery on application startup
   - Fail-safe data writing to prevent corruption

3. **Graphical User Interface (GUI)**
   - JavaFX UI with form validation and dynamic behavior
   - Styled with CSS for a modern look and feel
   - Responsive layout and intuitive user experience
   
4. **CRUD Operations Implementation**
   - Create: Adding new student records with validation
   - Read: Displaying and filtering student information
   - Update: Modifying existing student records
   - Delete: Removing unwanted student records
   
## Getting Started

### Installation

1. Clone this repository
   ```
   git clone https://github.com/hammadaiy/student-management-system.git
   ```

2. Navigate to the project directory
   ```
   cd student-management-system
   ```
