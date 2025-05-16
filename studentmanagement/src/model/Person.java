package model;

import java.io.Serializable;

/**
 * This is the base Person class that will be extended by other classes like Student.
 * 
 * ABSTRACT CLASS: This means you cannot create a Person object directly.
 * You can only create objects of classes that extend Person, like Student.
 * 
 * This class demonstrates two important OOP concepts:
 * 1. INHERITANCE - Student class will inherit from this class
 * 2. ABSTRACTION - We define a common structure for all person types
 * 
 * It implements Serializable to allow saving objects to a file.
 */
public abstract class Person implements Serializable {
    // This is needed for serialization, like a version number for our class
    private static final long serialVersionUID = 1L;
    
    // The name attribute is marked as protected instead of private
    // Protected means it can be accessed by this class and any class that extends it
    // This way, the Student class can access this attribute directly
    protected String name;
    
    /**
     * Default constructor - creates an empty Person with no name
     */
    public Person() {
        // This doesn't do anything specific, but it's good practice to include it
    }
    
    /**
     * Parameterized constructor - creates a Person with a name
     * 
     * @param name The name of the person
     */
    public Person(String name) {
        this.name = name;
    }
    
    /**
     * Get the name of this person
     * 
     * @return The person's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set/change the name of this person
     * 
     * @param name The new name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * ABSTRACT METHOD: This method doesn't have an implementation here.
     * Any class that extends Person MUST implement this method.
     * 
     * This is a form of POLYMORPHISM - different classes will implement
     * this method differently based on their specific needs.
     * 
     * @return The role of this person (will be implemented by subclasses)
     */
    public abstract String getRole();
} 