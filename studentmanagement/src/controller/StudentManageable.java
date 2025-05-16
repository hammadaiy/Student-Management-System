package controller;

import java.util.List;
import model.Student;

/**
 * This is an INTERFACE that defines operations for student management.
 * 
 * INTERFACES in Java are like contracts:
 * - They define what methods a class must implement
 * - They don't contain implementation code
 * - A class can implement multiple interfaces
 * 
 * This interface defines the essential operations our student management system
 * needs to provide: adding, updating, deleting, and retrieving students,
 * as well as saving and loading data.
 * 
 * Any class that implements this interface MUST provide code for all these methods.
 */
public interface StudentManageable {
    /**
     * Add a new student to the system.
     * 
     * This method should validate the student and add it to the collection
     * of students if it's valid.
     * 
     * @param student The student object to add
     * @return true if the student was added successfully, false otherwise
     */
    boolean addStudent(Student student);
    
    /**
     * Update an existing student's information.
     * 
     * This method should replace the student at the specified index with
     * the new student information.
     * 
     * @param index The position of the student in the list (0-based)
     * @param student The new student information to update with
     * @return true if the student was updated successfully, false otherwise
     */
    boolean updateStudent(int index, Student student);
    
    /**
     * Delete a student from the system.
     * 
     * This method should remove the student at the specified index from
     * the collection of students.
     * 
     * @param index The position of the student in the list (0-based)
     * @return true if the student was deleted successfully, false otherwise
     */
    boolean deleteStudent(int index);
    
    /**
     * Get all students currently in the system.
     * 
     * This method should return a list containing all student objects currently
     * stored in the system. The list can be empty if there are no students.
     * 
     * @return A list containing all student objects
     */
    List<Student> getAllStudents();
    
    /**
     * Save all student data to persistent storage.
     * 
     * This method should save the current state of all students to
     * a file or database for permanent storage.
     * 
     * @return true if the data was saved successfully, false otherwise
     */
    boolean saveData();
    
    /**
     * Load student data from persistent storage.
     * 
     * This method should read previously saved student data from
     * a file or database and restore the state of the system.
     * 
     * @return true if the data was loaded successfully, false otherwise
     */
    boolean loadData();
} 