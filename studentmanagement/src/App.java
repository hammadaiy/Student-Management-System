import view.StudentManagementUI;

/**
 * This is the main application class for the Student Management System.
 * 
 * The App class is the entry point for our JavaFX application.
 * When you run this class, it starts the Student Management System.
 * 
 * This class follows the Single Responsibility Principle (SRP):
 * Its only job is to launch the application UI.
 */
public class App {
    /**
     * The main method is the entry point for Java applications.
     * When you run the program, the JVM (Java Virtual Machine) starts by calling this method.
     * 
     * This method launches our JavaFX application by calling the static launch method
     * of the Application class (which StudentManagementUI extends).
     * 
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        // This will call the start() method in StudentManagementUI
        StudentManagementUI.launch(StudentManagementUI.class, args);
    }
}
