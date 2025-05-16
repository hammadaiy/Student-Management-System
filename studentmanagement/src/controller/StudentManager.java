package controller;

import java.util.ArrayList;
import java.util.List;
import model.Student;
import util.DataManager;

/**
 * This class implements the StudentManageable interface to manage student data.
 * 
 * IMPLEMENTATION is when a class provides actual code for methods defined in an interface.
 * This is a key part of Object-Oriented Programming.
 * 
 * The StudentManager acts as the "brain" of our application:
 * - It stores the list of students in memory
 * - It handles adding, updating, and deleting students
 * - It uses DataManager to save and load data from files
 */
public class StudentManager implements StudentManageable {
    // This list stores all our Student objects in memory
    private List<Student> students;
    
    /**
     * Constructor - initializes a new StudentManager with an empty list of students
     */
    public StudentManager() {
        // Create a new empty ArrayList to store our students
        students = new ArrayList<>();
    }
    
    /**
     * Add a new student to the system.
     * 
     * This is an IMPLEMENTATION of the method defined in StudentManageable interface.
     * 
     * @param student The student object to add
     * @return true if the student was added successfully, false otherwise
     */
    @Override
    public boolean addStudent(Student student) {
        // Check if the student is null before trying to add it
        if (student == null) {
            return false; // Can't add a null student
        }
        
        try {
            // Add the student to our list
            students.add(student);
            return true; // Success
        } catch (Exception e) {
            // If something goes wrong (rare with ArrayList), print the error
            System.err.println("Error adding student: " + e.getMessage());
            return false; // Failure
        }
    }
    
    /**
     * Update an existing student's information.
     * 
     * This replaces the student at the given index with the new student.
     * It first validates that the index is valid and the student is not null.
     * 
     * @param index The position of the student in the list (0-based)
     * @param student The new student information to update with
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateStudent(int index, Student student) {
        // Check if the index is valid and the student is not null
        if (index < 0 || index >= students.size() || student == null) {
            return false; // Invalid parameters
        }
        
        try {
            // Replace the student at the specified index with the new student
            students.set(index, student);
            return true; // Success
        } catch (Exception e) {
            // If something goes wrong, print the error
            System.err.println("Error updating student: " + e.getMessage());
            return false; // Failure
        }
    }
    
    /**
     * Delete a student from the system.
     * 
     * This removes the student at the given index from the list.
     * It first validates that the index is valid.
     * 
     * @param index The position of the student in the list (0-based)
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean deleteStudent(int index) {
        // Check if the index is valid
        if (index < 0 || index >= students.size()) {
            return false; // Invalid index
        }
        
        try {
            // Remove the student at the specified index
            students.remove(index);
            return true; // Success
        } catch (Exception e) {
            // If something goes wrong, print the error
            System.err.println("Error deleting student: " + e.getMessage());
            return false; // Failure
        }
    }
    
    /**
     * Get all students currently in the system.
     * 
     * We return a new list containing all the students to prevent
     * the caller from modifying our internal list directly.
     * This is called DEFENSIVE COPYING.
     * 
     * @return A list containing all student objects
     */
    @Override
    public List<Student> getAllStudents() {
        // Return a new ArrayList containing all students
        // This is a defensive copy to protect our internal data
        return new ArrayList<>(students);
    }
    
    /**
     * Save all student data to a file using the DataManager utility.
     * 
     * @return true if the data was saved successfully, false otherwise
     */
    @Override
    public boolean saveData() {
        // Use the DataManager utility class to save the students
        return DataManager.saveStudents(students);
    }
    
    /**
     * Load student data from a file using the DataManager utility.
     * 
     * This replaces all current students with the loaded ones.
     * 
     * @return true if the data was loaded successfully, false otherwise
     */
    @Override
    public boolean loadData() {
        try {
            // Load students from the file
            List<Student> loadedStudents = DataManager.loadStudents();
            
            // Clear the current list
            students.clear();
            
            // Add all loaded students to our list
            students.addAll(loadedStudents);
            
            return true; // Success
        } catch (Exception e) {
            // If something goes wrong, print the error
            System.err.println("Error loading data: " + e.getMessage());
            return false; // Failure
        }
    }
} 