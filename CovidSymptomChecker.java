import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class CovidSymptomChecker {
    /**
     * The `CovidSymptomChecker` class provides a user interface for checking COVID-19 symptoms and suggesting appropriate actions based on user
     * selections. It contains checkboxes for various symptoms, a button to trigger symptom checking, and displays results using alert dialogs.
     * The class aims to assist users in assessing their symptoms and provides guidance on whether they should seek medical help or follow home
     * remedy suggestions.
     * @author (Sanika Gadgil)
    */
    public CovidSymptomChecker(BorderPane root)
    {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label titleLabel = new Label("COVID-19 Symptom Checker");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        GridPane.setConstraints(titleLabel, 0, 0, 2, 1);

        Label symptomLabel = new Label("Select your symptoms:");
        GridPane.setConstraints(symptomLabel, 0, 1);

        // Creating checkboxes for symptoms
        CheckBox feverCheckBox = new CheckBox("Fever");
        CheckBox coughCheckBox = new CheckBox("Cough");
        CheckBox breathCheckBox = new CheckBox("Shortness of Breath");
        CheckBox fatigueCheckBox = new CheckBox("Fatigue");
        CheckBox bodyAcheCheckBox = new CheckBox("Body Aches");
        CheckBox soreThroatCheckBox = new CheckBox("Sore Throat");
        CheckBox lossTasteCheckBox = new CheckBox("Loss of Taste");
        CheckBox lossSmellCheckBox = new CheckBox("Loss of Smell");
        CheckBox headacheCheckBox = new CheckBox("Headache");
        CheckBox congestionCheckBox = new CheckBox("Nasal Congestion");
        CheckBox nauseaCheckBox = new CheckBox("Nausea or Vomiting");

        VBox symptomsBox = new VBox(5,
                feverCheckBox, coughCheckBox, breathCheckBox,
                fatigueCheckBox, bodyAcheCheckBox, soreThroatCheckBox,
                lossTasteCheckBox, lossSmellCheckBox, headacheCheckBox,
                congestionCheckBox, nauseaCheckBox);
        GridPane.setConstraints(symptomsBox, 1, 1);

        // Creating a button to check symptoms
        Button checkButton = new Button("Check Symptoms");
        GridPane.setConstraints(checkButton, 0, 2);
        checkButton.setOnAction(event -> {
            StringBuilder message = new StringBuilder();
            
            // Providing home remedy suggestions
            if (lossTasteCheckBox.isSelected() || lossSmellCheckBox.isSelected() || congestionCheckBox.isSelected()) {
                message.append("You have a serious symptom. Please get checked for COVID-19.");
            } else {
                message.append("Your symptoms don't indicate COVID-19, but stay vigilant. Here are some home remedy suggestions:\n");
                if (feverCheckBox.isSelected()) {
                    message.append("- For fever, rest and drink plenty of fluids. Take acetaminophen or ibuprofen if needed.\n");
                }
                if (coughCheckBox.isSelected()) {
                    message.append("- For cough, stay hydrated and use cough drops or honey.\n");
                }
                if (breathCheckBox.isSelected()) {
                    message.append("- For shortness of breath, sit upright and take deep breaths. Seek medical help if severe.\n");
                }
                if (fatigueCheckBox.isSelected()) {
                    message.append("- For fatigue, get plenty of rest and maintain a healthy diet.\n");
                }
                if (bodyAcheCheckBox.isSelected()) {
                    message.append("- For body aches, apply warm compresses and take over-the-counter pain relievers.\n");
                }
                if (soreThroatCheckBox.isSelected()) {
                    message.append("- For sore throat, gargle with warm salt water and drink soothing beverages.\n");
                }
                if (headacheCheckBox.isSelected()) {
                    message.append("- For headache, rest in a quiet, dark room and use cold compresses on the forehead.\n");
                }
                if (nauseaCheckBox.isSelected()) {
                    message.append("- For nausea or vomiting, drink clear fluids and eat bland foods like crackers or toast.\n");
                }
            }
            
            // Displaying the result in an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Symptom Checker Result");
            alert.setHeaderText(null);
            alert.setContentText(message.toString());
            alert.showAndWait();
        });

        grid.getChildren().addAll(titleLabel, symptomLabel, symptomsBox, checkButton);
        root.setCenter(grid);
    }
}
    