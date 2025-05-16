package model;

import java.io.Serializable;

/**
 * This is the Student class that represents a student in our system.
 * 
 * It extends the Person class to inherit basic attributes like name.
 * This is an example of INHERITANCE in Object-Oriented Programming.
 * 
 * It implements Serializable so we can save student objects to a file.
 * Serializable is an interface that allows objects to be converted to a byte stream.
 */
public class Student extends Person implements Serializable {
    // This is needed for serialization to work correctly across different versions
    // of our application. It's like a version number for our Student class.
    private static final long serialVersionUID = 1L;
    
    // These are the attributes (data) that each Student object will have
    // By making them private, we are using ENCAPSULATION, which means
    // these variables are only accessible through methods (getters and setters)
    private String rollNumber;  // Unique identifier for the student
    private String course;      // The course the student is enrolled in
    private int grade;          // The student's grade (0-5)
    
    /**
     * Default constructor - creates an empty Student object
     * When we call new Student(), this method runs
     */
    public Student() {
        // super() calls the constructor of the parent class (Person)
        super();
    }
    
    /**
     * Parameterized constructor - creates a Student with all attributes set
     * When we call new Student(name, rollNumber, course, grade), this method runs
     */
    public Student(String name, String rollNumber, String course, int grade) {
        // Call the parent class (Person) constructor with the name
        super(name);
        
        // Set the student-specific attributes
        this.rollNumber = rollNumber;
        this.course = course;
        this.grade = grade;
    }
    
    // GETTERS AND SETTERS - these are methods that allow us to access and modify
    // private attributes in a controlled way (part of ENCAPSULATION)
    
    /**
     * Get the roll number of this student
     * @return The student's roll number
     */
    public String getRollNumber() {
        return rollNumber;
    }
    
    /**
     * Set/change the roll number of this student
     * @param rollNumber The new roll number to set
     */
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    /**
     * Get the course this student is enrolled in
     * @return The student's course
     */
    public String getCourse() {
        return course;
    }
    
    /**
     * Set/change the course this student is enrolled in
     * @param course The new course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }
    
    /**
     * Get the grade of this student
     * @return The student's grade (0-5)
     */
    public int getGrade() {
        return grade;
    }
    
    /**
     * Set/change the grade of this student, with validation
     * This is an example of data validation in setters
     * 
     * @param grade The new grade to set (must be between 0 and 5)
     * @throws IllegalArgumentException if the grade is not between 0 and 5
     */
    public void setGrade(int grade) {
        // Check if the grade is valid before setting it
        if (grade >= 0 && grade <= 5) {
            this.grade = grade;
        } else {
            // If the grade is invalid, throw an exception with an error message
            throw new IllegalArgumentException("Grade must be between 0 and 5");
        }
    }
    
    /**
     * This is an implementation of the abstract method from Person class
     * This is an example of POLYMORPHISM - we're providing a specific implementation
     * of a method defined in the parent class
     * 
     * @return The role of this person, which is "Student"
     */
    @Override
    public String getRole() {
        return "Student";
    }
    
    /**
     * Returns a string representation of the Student object
     * This overrides the toString method from the Object class
     * It's useful for debugging and logging
     * 
     * @return A string with all student information
     */
    @Override
    public String toString() {
        return "Student [name=" + name + ", rollNumber=" + rollNumber + ", course=" + course + ", grade=" + grade + "]";
    }
} 