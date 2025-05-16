package view;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import model.Student;
import controller.StudentManager;
import util.Validator;

/**
 * This is the main UI class for the Student Management System.
 * 
 * JAVAFX BASICS:
 * - JavaFX is a framework for building desktop applications
 * - A Stage is like a window in your application
 * - A Scene is what's displayed in the Stage (like a web page)
 * - Layouts (like BorderPane, VBox) organize elements on the scene
 * - Controls (like Button, TextField) are UI elements users interact with
 * 
 * This class creates the user interface with:
 * 1. A form on the left side for adding/updating students
 * 2. A table on the right side for displaying students
 * 3. Buttons for operations like add, update, delete, and clear
 * 
 * This class uses the MVC pattern:
 * - Model: Student class (data)
 * - View: This class (UI)
 * - Controller: StudentManager class (business logic)
 */
public class StudentManagementUI extends Application {
    
    // The controller that manages student data
    private StudentManager studentManager;
    
    // UI Components - these are the elements that make up our interface
    private TextField nameField;            // For entering student name
    private TextField rollNumberField;      // For entering roll number
    private TextField courseField;          // For entering course
    private TextField gradeField;           // For entering grade
    private Button addUpdateButton;         // Button that serves as Add or Update
    private Button clearButton;             // Button to clear the form
    private Button deleteButton;            // Button to delete a student
    private TableView<Student> studentTable; // Table to display students
    private ObservableList<Student> studentList; // Data for the table (special JavaFX list)
    
    // Keeps track of which student is selected for updating
    // -1 means no student is selected (adding a new student)
    private int selectedStudentIndex = -1;
    
    /**
     * This method is called automatically when the application starts.
     * It sets up the UI and shows it to the user.
     * 
     * @param primaryStage The main window of the application
     */
    @Override
    public void start(Stage primaryStage) {
        // Create the controller and load existing data
        studentManager = new StudentManager();
        studentManager.loadData();
        
        // Create the main layout container (BorderPane)
        // A BorderPane has 5 areas: top, bottom, left, right, and center
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10)); // Add some space around the edges
        
        // Create the left side form
        VBox formLayout = createFormLayout();
        
        // Create the right side table
        VBox tableLayout = createTableLayout();
        
        // Add both layouts to the main layout
        mainLayout.setLeft(formLayout);
        mainLayout.setCenter(tableLayout);
        
        // Create a scene with the main layout
        Scene scene = new Scene(mainLayout, 800, 500); // Width 800, height 500
        
        try {
            // Add CSS styling to the scene
            String css = getClass().getResource("/resources/style.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception e) {
            // In case the CSS file can't be found, print an error but continue
            System.err.println("Warning: Could not load CSS file: " + e.getMessage());
        }
        
        // Configure the main window (Stage)
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show(); // Display the window
        
        // Load student data into the table
        loadTableData();
    }
    
    /**
     * Creates the form layout for adding/updating students.
     * 
     * This method builds the left side of the UI, which contains:
     * - Text fields for entering student information
     * - Labels for each field
     * - Buttons for adding/updating and clearing the form
     * 
     * @return A VBox containing all the form elements
     */
    private VBox createFormLayout() {
        // Create a vertical box layout with 10 pixels spacing between elements
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20)); // Add some space around the edges
        layout.setPrefWidth(300); // Set preferred width
        
        // Create a title for the form
        Label titleLabel = new Label("Student Data Form");
        titleLabel.getStyleClass().add("form-title"); // Apply CSS styling
        
        // Create text fields for student data entry
        nameField = new TextField();
        nameField.setPromptText("Student Name"); // Placeholder text
        
        rollNumberField = new TextField();
        rollNumberField.setPromptText("Roll Number");
        
        courseField = new TextField();
        courseField.setPromptText("Course");
        
        gradeField = new TextField();
        gradeField.setPromptText("Grade (0-5)");
        
        // Create labels for each field
        Label nameLabel = new Label("Student Name:");
        Label rollNumberLabel = new Label("Roll Number:");
        Label courseLabel = new Label("Course:");
        Label gradeLabel = new Label("Grade (0-5):");
        
        // Create the Add/Update button
        addUpdateButton = new Button("Add Student");
        addUpdateButton.getStyleClass().add("primary-button"); // Apply CSS styling
        addUpdateButton.setMaxWidth(Double.MAX_VALUE); // Make the button stretch across the layout
        
        // Set what happens when the button is clicked (event handler)
        // The -> syntax is a lambda expression, a shortcut for creating a simple event handler
        addUpdateButton.setOnAction(e -> handleAddUpdate());
        
        // Create the Clear button
        clearButton = new Button("Clear Form");
        clearButton.getStyleClass().add("secondary-button"); // Apply CSS styling
        clearButton.setMaxWidth(Double.MAX_VALUE); // Make the button stretch
        clearButton.setOnAction(e -> clearForm());
        
        // Add all components to the layout in the order they should appear
        layout.getChildren().addAll(
            titleLabel,
            nameLabel, nameField,
            rollNumberLabel, rollNumberField,
            courseLabel, courseField,
            gradeLabel, gradeField,
            addUpdateButton,
            clearButton
        );
        
        return layout;
    }
    
    /**
     * Creates the table layout for displaying students.
     * 
     * This method builds the right side of the UI, which contains:
     * - A table showing all student records
     * - A delete button for removing selected students
     * 
     * @return A VBox containing the table and delete button
     */
    private VBox createTableLayout() {
        // Create a vertical box layout with 10 pixels spacing
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20)); // Add some space around the edges
        
        // Create a title for the table
        Label titleLabel = new Label("Student Data Table");
        titleLabel.getStyleClass().add("table-title"); // Apply CSS styling
        
        // Create the table for displaying students
        studentTable = new TableView<>();
        studentTable.setPlaceholder(new Label("No students in the system")); // Shows when table is empty
        
        // Create columns for the table
        // Each column displays a specific property of the Student objects
        
        // Name column
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // Links to Student.getName()
        nameColumn.setPrefWidth(150);
        
        // Roll Number column
        TableColumn<Student, String> rollNumberColumn = new TableColumn<>("Roll Number");
        rollNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        rollNumberColumn.setPrefWidth(100);
        
        // Course column
        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        courseColumn.setPrefWidth(150);
        
        // Grade column
        TableColumn<Student, Integer> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gradeColumn.setPrefWidth(80);
        
        // Add all columns to the table
        studentTable.getColumns().addAll(nameColumn, rollNumberColumn, courseColumn, gradeColumn);
        
        // Customize the table rows to highlight students with grade 0 in red
        studentTable.setRowFactory(tv -> new TableRow<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                
                if (student == null || empty) {
                    setStyle(""); // No styling for empty rows
                } else if (student.getGrade() == 0) {
                    // Red background for students with grade 0
                    setStyle("-fx-background-color: #ffcccc;");
                } else {
                    setStyle(""); // No special styling for other rows
                }
            }
        });
        
        // Set up what happens when a row is selected
        // This adds a listener to the selection property of the table
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleStudentSelection(newSelection);
            }
        });
        
        // Create the Delete button
        deleteButton = new Button("Delete Student");
        deleteButton.getStyleClass().add("delete-button"); // Apply CSS styling
        deleteButton.setDisable(true); // Disabled until a student is selected
        deleteButton.setMaxWidth(Double.MAX_VALUE); // Make the button stretch
        deleteButton.setOnAction(e -> handleDelete());
        
        // Add all components to the layout
        layout.getChildren().addAll(titleLabel, studentTable, deleteButton);
        
        // Make the table grow to fill available space
        VBox.setVgrow(studentTable, Priority.ALWAYS);
        
        return layout;
    }
    
    /**
     * Loads student data into the table.
     * 
     * This is called when the application starts and whenever
     * the data changes (after adding, updating, or deleting a student).
     */
    private void loadTableData() {
        // Get the current list of students from the manager
        // and convert it to an ObservableList (special JavaFX list that updates the UI)
        studentList = FXCollections.observableArrayList(studentManager.getAllStudents());
        
        // Set the list as the data source for the table
        studentTable.setItems(studentList);
    }
    
    /**
     * Handles the Add/Update button click event.
     * 
     * This method:
     * 1. Validates all input fields
     * 2. Creates a new Student object from the form data
     * 3. Either adds a new student or updates an existing one
     * 4. Saves the data and refreshes the table
     */
    private void handleAddUpdate() {
        // First validate all input fields
        if (!validateInput()) {
            return; // Exit if validation fails
        }
        
        // Get the data from the form fields
        String name = nameField.getText().trim();
        String rollNumber = rollNumberField.getText().trim();
        String course = courseField.getText().trim();
        int grade = Integer.parseInt(gradeField.getText().trim());
        
        // Create a new Student object with the form data
        Student student = new Student(name, rollNumber, course, grade);
        
        if (selectedStudentIndex >= 0) {
            // Update existing student (if one is selected)
            studentManager.updateStudent(selectedStudentIndex, student);
            clearForm(); // Clear the form after update
        } else {
            // Add new student
            studentManager.addStudent(student);
            clearForm(); // Clear the form after add
        }
        
        // Save the data to file and refresh the table
        studentManager.saveData();
        loadTableData();
    }
    
    /**
     * Handles the Delete button click event.
     * 
     * This method:
     * 1. Confirms the deletion with the user
     * 2. Deletes the selected student if confirmed
     * 3. Saves the data and refreshes the table
     */
    private void handleDelete() {
        if (selectedStudentIndex >= 0) {
            // Show a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Student");
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this student?");
            
            // Wait for the user's response
            if (alert.showAndWait().get() == ButtonType.OK) {
                // User clicked OK, so delete the student
                studentManager.deleteStudent(selectedStudentIndex);
                studentManager.saveData(); // Save changes to file
                loadTableData(); // Refresh the table
                clearForm(); // Clear the form
            }
        }
    }
    
    /**
     * Handles student selection from the table.
     * 
     * When a student is selected in the table, this method:
     * 1. Fills the form with that student's data
     * 2. Changes the Add button to Update
     * 3. Enables the Delete button
     * 
     * @param student The selected Student object
     */
    private void handleStudentSelection(Student student) {
        // Save the index of the selected student
        selectedStudentIndex = studentList.indexOf(student);
        
        // Fill the form with the selected student's data
        nameField.setText(student.getName());
        rollNumberField.setText(student.getRollNumber());
        courseField.setText(student.getCourse());
        gradeField.setText(String.valueOf(student.getGrade()));
        
        // Update button text and enable delete button
        addUpdateButton.setText("Update Student");
        deleteButton.setDisable(false);
    }
    
    /**
     * Validates all input fields before adding or updating a student.
     * 
     * This method checks:
     * - Name is not empty
     * - Roll number is valid (not empty and contains only letters and numbers)
     * - Course is not empty
     * - Grade is a valid number between 0 and 5
     * 
     * If any validation fails, it shows an error message.
     * 
     * @return true if all fields are valid, false otherwise
     */
    private boolean validateInput() {
        // Create a StringBuilder to collect all error messages
        StringBuilder errorMessage = new StringBuilder();
        
        // Validate name
        if (!Validator.isValidString(nameField.getText())) {
            errorMessage.append("- Student name is required\n");
        }
        
        // Validate roll number
        if (!Validator.isValidRollNumber(rollNumberField.getText())) {
            errorMessage.append("- Roll number is required and must contain only letters and numbers\n");
        }
        
        // Validate course
        if (!Validator.isValidString(courseField.getText())) {
            errorMessage.append("- Course is required\n");
        }
        
        // Validate grade
        if (!Validator.isValidGradeString(gradeField.getText())) {
            errorMessage.append("- Grade must be a number between 0 and 5\n");
        }
        
        // If there are any errors, show them to the user
        if (errorMessage.length() > 0) {
            // Create and configure an error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please correct the following errors:");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait(); // Display the alert and wait for user to close it
            return false; // Validation failed
        }
        
        return true; // All fields are valid
    }
    
    /**
     * Clears the form and resets selection.
     * 
     * This method:
     * 1. Clears all text fields
     * 2. Resets the selected student index
     * 3. Changes the Update button back to Add
     * 4. Disables the Delete button
     */
    private void clearForm() {
        // Clear all text fields
        nameField.clear();
        rollNumberField.clear();
        courseField.clear();
        gradeField.clear();
        
        // Reset selection
        selectedStudentIndex = -1;
        studentTable.getSelectionModel().clearSelection();
        
        // Reset buttons
        addUpdateButton.setText("Add Student");
        deleteButton.setDisable(true);
    }
    
    /**
     * This method is called when the application is closing.
     * It saves all student data before the application exits.
     */
    @Override
    public void stop() {
        // Save data on application close
        studentManager.saveData();
    }
} 