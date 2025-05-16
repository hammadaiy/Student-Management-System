package util;

/**
 * This is a utility class for validating user input.
 * 
 * UTILITY CLASSES contain static helper methods that perform common operations.
 * These classes typically don't maintain any state (no object variables).
 * 
 * This utility class provides methods to validate different types of input:
 * - Strings (to check if they're empty)
 * - Grade values (to check if they're between 0 and 5)
 * - Roll numbers (to check if they contain only valid characters)
 * 
 * All methods are static, which means you can call them directly without
 * creating a Validator object, like: Validator.isValidString("Hello")
 */
public class Validator {
    
    /**
     * Check if a string is valid (not null and not empty).
     * 
     * This is used to validate text input fields like name and course.
     * 
     * Examples:
     * - "John" -> true (valid)
     * - "" -> false (empty string)
     * - null -> false (null value)
     * - "   " -> false (only whitespace)
     * 
     * @param value The string to validate
     * @return true if the string is not null and not empty, false otherwise
     */
    public static boolean isValidString(String value) {
        // Check if the string is null
        if (value == null) {
            return false;
        }
        
        // Check if the string is empty after removing whitespace
        // trim() removes spaces from the beginning and end of the string
        return !value.trim().isEmpty();
    }
    
    /**
     * Check if a grade value is between 0 and 5 (inclusive).
     * 
     * This is used to validate grade input as an integer.
     * 
     * Examples:
     * - 0 -> true (valid)
     * - 3 -> true (valid)
     * - 5 -> true (valid)
     * - -1 -> false (too low)
     * - 6 -> false (too high)
     * 
     * @param grade The grade value to validate
     * @return true if the grade is between 0 and 5, false otherwise
     */
    public static boolean isValidGrade(int grade) {
        // Check if the grade is between 0 and 5 (inclusive)
        return grade >= 0 && grade <= 5;
    }
    
    /**
     * Check if a string represents a valid grade (a number between 0 and 5).
     * 
     * This is used to validate the grade input from a text field.
     * It first tries to convert the string to an integer, then checks
     * if that integer is a valid grade.
     * 
     * Examples:
     * - "0" -> true (valid)
     * - "3" -> true (valid)
     * - "5" -> true (valid)
     * - "-1" -> false (too low)
     * - "6" -> false (too high)
     * - "abc" -> false (not a number)
     * 
     * @param gradeString The grade as a string to validate
     * @return true if the string is a valid grade, false otherwise
     */
    public static boolean isValidGradeString(String gradeString) {
        try {
            // Try to convert the string to an integer
            int grade = Integer.parseInt(gradeString);
            
            // Check if that integer is a valid grade
            return isValidGrade(grade);
        } catch (NumberFormatException e) {
            // If the string cannot be converted to an integer, it's not valid
            return false;
        }
    }
    
    /**
     * Check if a roll number is valid (not empty and contains only alphanumeric characters).
     * 
     * This is used to validate the roll number input field.
     * Roll numbers should only contain letters and numbers, no spaces or special characters.
     * 
     * Examples:
     * - "A123" -> true (valid)
     * - "CS101" -> true (valid)
     * - "R2D2" -> true (valid)
     * - "" -> false (empty)
     * - "CS-101" -> false (contains hyphen)
     * - "Stud 1" -> false (contains space)
     * 
     * @param rollNumber The roll number to validate
     * @return true if the roll number is valid, false otherwise
     */
    public static boolean isValidRollNumber(String rollNumber) {
        // First check if it's a valid string (not null, not empty)
        if (!isValidString(rollNumber)) {
            return false;
        }
        
        // Then check if it contains only alphanumeric characters (letters and numbers)
        // This uses a regular expression (regex) to match the pattern
        // ^ means start of string, [a-zA-Z0-9]+ means one or more letters or numbers, $ means end of string
        return rollNumber.trim().matches("^[a-zA-Z0-9]+$");
    }
} 