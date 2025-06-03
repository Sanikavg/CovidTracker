import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;

/**
 * The Borough class is a JavaFX application that displays COVID-19 records for a specific borough within a specified date range.
 * @author (Jasmin Bedi)
 */
public class Borough extends Application {
    private SimpleDateFormat sdf; // Define the format the date is represented in
    private String borough;
    private ObservableList<String> boroughRecords;
    private String titles;
    
    /**
     * Constructs a Borough object with the given borough name, start date, and end date.
     * 
     * @param borough The name of the borough for which records will be displayed.
     * @param startDateString The start date of the date range, represented as a string.
     * @param endDateString The end date of the date range, represented as a string.
     */
    public Borough(String borough, String startDateString, String endDateString) 
    {
        this.borough = borough;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> filteredRecords = new ArrayList<>();
        URL url = getClass().getResource("covid_london.csv");
        
        // Read data from the CSV file and filter records based on the borough and date range
        try (BufferedReader br = new BufferedReader(new FileReader(new File(url.toURI()).getAbsolutePath()))) {
            titles = br.readLine(); // Read the title of the CSV file
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String date = parts[0].trim();
                if (date.compareTo(startDateString) >= 0 && date.compareTo(endDateString) <= 0 && borough.equals(parts[1])) {
                    filteredRecords.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        boroughRecords = FXCollections.observableArrayList(filteredRecords);
    
        start(new Stage());
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Records for " + borough);
        
        ListView<String> listView = new ListView<>(boroughRecords);
        
        VBox layout = new VBox(10);
        
        // Check if there are records available for the selected borough and date range
        if (boroughRecords.isEmpty())
        {
            Label titleLabel = new Label("There are no records found for this date range in this Borough");
            layout.getChildren().addAll(titleLabel);
        }
        else {
            Label titleLabel = new Label(titles);

            ChoiceBox<String> sortChoiceBox = new ChoiceBox<>(); // Create a ChoiceBox for sorting options
            sortChoiceBox.getItems().addAll("Date", "Retail Recreation GMR", "Grocery Pharmacy GMR", "Parks GMR","Transit GMR", "Workplaces GMR", "Residential GMR", "New COVID Cases", "Total COVID Cases", "New COVID Deaths");
            sortChoiceBox.setValue("Date"); // Default sorting option
            
            // Define action for sorting choice box
            sortChoiceBox.setOnAction(event -> {
                String selectedSort = sortChoiceBox.getValue();
                switch (selectedSort) {
                    case "Date":
                        boroughRecords.sort(Comparator.comparing(this::getDate));
                        break;
                    case "Retail Recreation GMR":
                        boroughRecords.sort(Comparator.comparing(this::getRetailRecreationGMR));
                        break;
                    case "Grocery Pharmacy GMR":
                        boroughRecords.sort(Comparator.comparing(this::getGroceryPharmacyGMR));
                        break;
                    case "Parks GMR":
                        boroughRecords.sort(Comparator.comparing(this::getParksGMR));
                        break;
                    case "Transit GMR":
                        boroughRecords.sort(Comparator.comparing(this::getTransitGMR));
                        break;
                    case "Workplaces GMR":
                        boroughRecords.sort(Comparator.comparing(this::getWorkplacesGMR));
                        break;
                    case "Residential GMR":
                        boroughRecords.sort(Comparator.comparing(this::getResidentialGMR));
                        break;
                    case "New COVID Cases":
                        boroughRecords.sort(Comparator.comparing(this::getNewCases));
                        break;
                    case "Total COVID Cases":
                        boroughRecords.sort(Comparator.comparing(this::getTotalCases));
                        break;
                    case "New COVID Deaths":
                        boroughRecords.sort(Comparator.comparing(this::getNewDeaths));
                        break;
                }
            });
            layout.getChildren().addAll(titleLabel, listView, sortChoiceBox);
        }
        
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getDate(String record) {
        return record.split(",")[0].trim();
    }

    private int getRetailRecreationGMR(String record) {
        return Integer.parseInt(record.split(",")[2].trim());
    }
    
    private int getGroceryPharmacyGMR(String record) {
        return Integer.parseInt(record.split(",")[3].trim());
    }
    
    private int getParksGMR(String record) {
        return Integer.parseInt(record.split(",")[4].trim());
    }
    
    private int getTransitGMR(String record) {
        return Integer.parseInt(record.split(",")[5].trim());
    }
    
    private int getWorkplacesGMR(String record) {
        return Integer.parseInt(record.split(",")[6].trim());
    }
    
    private int getResidentialGMR(String record) {
        return Integer.parseInt(record.split(",")[7].trim());
    }

    private int getNewCases(String record) {
        return Integer.parseInt(record.split(",")[8].trim());
    }

    private int getTotalCases(String record) {
        return Integer.parseInt(record.split(",")[9].trim());
    }

    private int getNewDeaths(String record) {
        return Integer.parseInt(record.split(",")[10].trim());
    } 

    public static void main(String[] args) {
        launch(args);
    }
}

