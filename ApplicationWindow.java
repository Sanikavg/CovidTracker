
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The ApplicationWindow class is the first and main window of this application that displays different panels based on user interaction. 
 * The user can select a date range from the two dropdown boxes on the top. 
 * The user can navigate through the panels with the help of the forward and backward buttons at the bottom.
 * @author (Sanika Gadgil)
 */

public class ApplicationWindow extends Application {
    
    private BorderPane root; // The root layout of the application
    private int currentIndex = 0; // Index of the currently displayed panel
    private ComboBox<String> fromDateComboBox; 
    private ComboBox<String> toDateComboBox; 
    private Button backButton;
    private Button forwardButton;

    @Override
    public void start(Stage primaryStage) {
        this.root = new BorderPane();
        
        // Create and display the first welcome screen panel
        WelcomeScreen welcomeScreen = new WelcomeScreen(this.root, null, null );
        
        // Create and configure navigation buttons, initially disable them
        backButton = new Button("<");
        backButton.setOnAction(event -> moveBack());
        backButton.setDisable(true);
        
        forwardButton = new Button(">");
        forwardButton.setOnAction(event -> moveForward());
        forwardButton.setDisable(true);
        
        // Placing and aligning the buttons
        HBox buttonBox = new HBox(750); 
        buttonBox.getChildren().addAll(backButton, forwardButton);
        buttonBox.setAlignment(Pos.CENTER); 
        root.setBottom(buttonBox); 
        
        // Date selection boxes
        HBox dateSelection = new HBox(10);
        fromDateComboBox = new ComboBox<>();
        toDateComboBox = new ComboBox<>();
        fromDateComboBox.getItems().addAll(getDateOptions("2020-01-01", "2023-02-09"));
        toDateComboBox.getItems().addAll(getDateOptions("2020-01-01", "2023-02-09"));
        fromDateComboBox.setPromptText("From Date");
        toDateComboBox.setPromptText("To Date");
        fromDateComboBox.setOnAction(e -> validateDateRange());
        toDateComboBox.setOnAction(e -> validateDateRange());
        dateSelection.getChildren().addAll(fromDateComboBox, toDateComboBox);
        root.setTop(dateSelection);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("COVID-19 Application");
        primaryStage.show();
    }
    
    /**
     * Generates a list of date options between the specified start and end dates.
     * 
     * @param startDateStr The start date string.
     * @param endDateStr The end date string.
     * @return A list of date options.
     */

    private List<String> getDateOptions(String startDateStr, String endDateStr) {
        List<String> dateOptions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        LocalDate currentDate = startDate;
        
        while (!currentDate.isAfter(endDate)) {
            dateOptions.add(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
        }

        return dateOptions;
    }

    /**
     * Moves to the previous panel when the back button is clicked. If on the first page, then moves to the last panel.
     */
    
    private void moveBack() {
        // Check if dates have been selected. If not, display appropriate error message.
        if (currentIndex == 0)
        {
            currentIndex = 3;
            updateCenter();
        }
        else
        {
            currentIndex--;
            updateCenter();
        }
        }
    
    /**
     * Moves to the next panel when the forward button is clicked. If on the first page, then moves again to the first panel.
     */

    private void moveForward() {
        
        if (currentIndex == 3)
        {
            currentIndex = 0;
            updateCenter();
        }
        else
        {
            currentIndex++;
            updateCenter();
        }
    }

    /**
     * Updates the center of the layout with the current panel.
     */

    private void updateCenter() {
        createPanel(currentIndex);
    }
    
    /**
     * Validates the selected date range.
     */
    private void validateDateRange() {
        if (fromDateComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No date selected");
            alert.setContentText("Must select a from date to proceed");
            alert.showAndWait();
            backButton.setDisable(true);
        }
        
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fromDate = LocalDate.parse(fromDateComboBox.getValue(), formatter);

            if (toDateComboBox.getValue() != null)
            {
                LocalDate toDate = LocalDate.parse(toDateComboBox.getValue(), formatter);
                if (fromDate.isAfter(toDate)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Date Range");
                    alert.setHeaderText("WARNING!!!");
                    alert.setContentText("The 'From' date cannot be after the 'To' date.");
                    alert.showAndWait();
                    backButton.setDisable(true);
                    forwardButton.setDisable(true);
                } else {
                    backButton.setDisable(false);
                    forwardButton.setDisable(false);
                    createPanel(currentIndex);
                }
                
            }    
        }
    }
    
    /**
     * Creates and displays a panel based on the current index.
     * 
     * @param currentIndex The index of the panel to create.
     */
    private void createPanel(int currentIndex)
    {
        if (currentIndex == 0)
        {
            root.setLeft(null);
            root.setRight(null);
            WelcomeScreen welcomeScreen = new WelcomeScreen(this.root, fromDateComboBox.getValue(), toDateComboBox.getValue() );
        }
        else if (currentIndex == 1)
        {
            root.setLeft(null);
            root.setRight(null);
            Map map = new Map(fromDateComboBox.getValue(), toDateComboBox.getValue(), this.root);
        }
        else if (currentIndex == 2)
        {
            Statistics statistics = new Statistics(fromDateComboBox.getValue(), toDateComboBox.getValue(), this.root);
        }
        else if (currentIndex == 3)
        {
            root.setLeft(null);
            root.setRight(null);
            CovidSymptomChecker covidChecker = new CovidSymptomChecker(this.root);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
