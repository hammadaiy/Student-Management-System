package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Student;

/**
 * This is the DataManager class that handles saving and loading student data.
 * 
 * It uses SERIALIZATION to convert Student objects to bytes that can be saved to a file,
 * and then later read back into Student objects. This is how we achieve data persistence.
 * 
 * This class uses only static methods, so you don't need to create a DataManager object.
 * You can just call the methods directly, like: DataManager.saveStudents(...)
 */
public class DataManager {
    // The file where we'll save our student data
    // This is a constant (static final) because we don't want it to change
    private static final String DATA_FILE = "students.dat";
    
    /**
     * Save a list of students to a file using serialization.
     * 
     * The steps are:
     * 1. Create an ObjectOutputStream (for writing objects to a file)
     * 2. Write the entire list of students as one object
     * 3. Close the stream automatically using try-with-resources
     * 
     * @param students List of Student objects to save
     * @return true if saving was successful, false if there was an error
     */
    public static boolean saveStudents(List<Student> students) {
        try (
            // This creates a stream to write bytes to a file
            FileOutputStream fileOut = new FileOutputStream(DATA_FILE);
            
            // This wraps the file stream to be able to write objects
            ObjectOutputStream out = new ObjectOutputStream(fileOut)
        ) {
            // Write the entire list as one object
            out.writeObject(students);
            
            // If we got here without errors, return success
            return true;
        } catch (IOException e) {
            // If there's an error (like can't write to file), print it
            System.err.println("Error saving students: " + e.getMessage());
            
            // Return failure
            return false;
        }
    }
    
    /**
     * Load students from the data file using deserialization.
     * 
     * The steps are:
     * 1. Check if the file exists
     * 2. If it does, create an ObjectInputStream (for reading objects from a file)
     * 3. Read the list of students as one object
     * 4. Close the stream automatically using try-with-resources
     * 
     * @return List of Student objects read from the file, or an empty list if no file exists
     */
    @SuppressWarnings("unchecked") // This tells the compiler not to warn about the cast to List<Student>
    public static List<Student> loadStudents() {
        // Create a File object to represent our data file
        File file = new File(DATA_FILE);
        
        // If the file doesn't exist, return an empty list
        if (!file.exists()) {
            return new ArrayList<>(); // Return an empty list
        }
        
        try (
            // This creates a stream to read bytes from a file
            FileInputStream fileIn = new FileInputStream(file);
            
            // This wraps the file stream to be able to read objects
            ObjectInputStream in = new ObjectInputStream(fileIn)
        ) {
            // Read the entire list as one object and cast it to List<Student>
            // We need the cast because readObject() returns type Object
            return (List<Student>) in.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            // If there's an error (like can't read from file or class definition has changed),
            // print it and return an empty list
            System.err.println("Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }
} 