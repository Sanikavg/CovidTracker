import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * The Statistics class is a JavaFX application that displays COVID-19 statistics in different panels. It calculates and presents statistics such 
 * as average Google Mobility Measure for parks and transit, total deaths, and average total cases within a specified date range.
 * @author (Sanika Gadgil)
 */
public class Statistics
{
    private StackPane[] panels; // Array of panels for displaying statistics
    private int currentIndex; // Index of the currently displayed panel
    private SimpleDateFormat sdf; //Define the format the date is represented in
    private CovidDataLoader records; //Load the records to be used for statistics
    private String startDate;
    private String endDate;
    private Button backButton;
    private Button forwardButton;
    private ArrayList<CovidData> recordList;
    
    /**
     * Constructs a Statistics object with the given start date, end date, and root BorderPane.
     * 
     * @param startDate The start date of the selected date range.
     * @param endDate The end date of the selected date range.
     * @param root The BorderPane to which the statistics panels and navigation buttons will be added.
     */
    public Statistics(String startDate, String endDate, BorderPane root)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        
        this.records = new CovidDataLoader();
        recordList = records.load();
        
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        panels = new StackPane[4];
        panels[0] = createPanel(avgParksGMR(startDate, endDate));
        panels[1] = createPanel(avgTransitGMR(startDate, endDate));
        panels[2] = createPanel(totalDeaths(startDate, endDate));
        panels[3] = createPanel(avgTotalCases(startDate, endDate));
        
        currentIndex = 0;

        // Layout setup
        root.setCenter(panels[currentIndex]);
        
        backButton = new Button("<");
        backButton.setOnAction(event -> moveBack(root));
        backButton.setPrefHeight(500);
        backButton.setPrefWidth(50);
        backButton.setStyle("-fx-background-color: white");
        root.setLeft(backButton);
        
        // Creating and styling the Forward Button
        forwardButton = new Button(">");
        forwardButton.setOnAction(event -> moveForward(root));
        forwardButton.setPrefHeight(500);
        forwardButton.setPrefWidth(50);
        forwardButton.setStyle("-fx-background-color: white");
        root.setRight(forwardButton);
        
        
        
    }
    
    /**
     * Moves to the previous panel when the back button is clicked. If on the first page, then moves to the last panel.
     */
    
    private void moveBack(BorderPane root) {
        if (currentIndex == 0)
        {
            currentIndex = panels.length - 1;
            updateCenter(root);
        }
        else
        {
            currentIndex--;
            updateCenter(root);
        }
    }
    
    /**
     * Moves to the next panel when the forward button is clicked. If on the first page, then moves again to the first panel.
     */

    private void moveForward(BorderPane root) {
        if (currentIndex == panels.length - 1)
        {
            currentIndex = 0;
            updateCenter(root);
        }
        else
        {
            currentIndex++;
            updateCenter(root);
        }
    }
    
    /**
     * Updates the center of the layout with the current panel.
     */

    private void updateCenter(BorderPane root) {
        root.setCenter(panels[currentIndex]);
    }

    /**
     * Creates a panel with a label displaying the given name.
     *
     * @param name the name to display on the panel
     * @return the created panel
     */
    private StackPane createPanel(String name) {
        StackPane panel = new StackPane();
        panel.setPrefSize(400, 300);
        
        Label label = new Label(name);
        label.setFont(new Font("Times New Roman", 30));
        panel.getChildren().add(label);
        
        return panel;
    }
    
    /**
     * Calculates the average Google Mobility Measure for parks within the specified date range.
     *
     * @param startDateString the start date of the date range
     * @param endDateString   the end date of the date range
     * @return the average Google Mobility Measure for parks
     */
    private String avgParksGMR(String startDateString, String endDateString)
    {
        float sum = 0;
        float count = 0;
        
        for (CovidData rec: recordList)
        {
            try
            {
                Date date = sdf.parse(rec.getDate());
                Date startDate = sdf.parse(startDateString);
                Date endDate = sdf.parse(endDateString);
                
                if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0)
                {
                    sum += rec.getParksGMR();
                    count++;
                }
            }
            catch (ParseException e) {
            e.printStackTrace();
            return "null";
            }
        }
       
        float average = sum / count;
        
        if (Float.isNaN(average))
        {
            return "There are no records of Total Cases available for this time period";
        }
        
        return "                  Average Of "+ "\n" + "Google Mobility Measure PARKS" + "\n \n" + "                   " + average;
        
    }
    
    /**
     * Calculates the average Google Mobility Measure for transit within the specified date range.
     *
     * @param startDateString the start date of the date range
     * @param endDateString   the end date of the date range
     * @return the average Google Mobility Measure for transit
     */
    private String avgTransitGMR(String startDateString, String endDateString)
    {
        float sum = 0;
        float count = 0;
        
        for (CovidData rec: recordList)
        {
            try
            {
                Date date = sdf.parse(rec.getDate());
                Date startDate = sdf.parse(startDateString);
                Date endDate = sdf.parse(endDateString);

                if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0)
                {
                    sum += rec.getTransitGMR();
                    count++;
                }
            }
            catch (ParseException e) {
            e.printStackTrace();
            return "null";
            }
        }
        
        float average = sum / count;
        
        if (Float.isNaN(average))
        {
            return "There are no records of Transit GMR available for this time period";
        }
        
        return "                    Average Of "+ "\n" + "Google Mobility Measure TRANSIT" + "\n \n" + "                    " + average;
        
    }
    
    /**
     * Calculates the total number of deaths within the specified date range.
     *
     * @param startDateString the start date of the date range
     * @param endDateString   the end date of the date range
     * @return the total number of deaths
     */
    private String totalDeaths(String startDateString, String endDateString)
    {
        float sum = 0;
        float count = 0;
        
        for (CovidData rec: recordList)
        {
            try
            {
                Date date = sdf.parse(rec.getDate());
                Date startDate = sdf.parse(startDateString);
                Date endDate = sdf.parse(endDateString);

                if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0)
                {
                    sum += rec.getTotalDeaths();
                }
            }
            catch (ParseException e) {
            e.printStackTrace();
            return "null";
            }
        }
        
        return "Total Number Of (total) Deaths" + "\n \n" + "                  " + sum;
        
    }
    
    /**
     * Calculates the average total cases within the specified date range.
     *
     * @param startDateString the start date of the date range
     * @param endDateString   the end date of the date range
     * @return the average total cases
     */
    private String avgTotalCases(String startDateString, String endDateString)
    {
        float sum = 0;
        float count = 0;
        
        for (CovidData rec: recordList)
        {
            try
            {
                Date date = sdf.parse(rec.getDate());
                Date startDate = sdf.parse(startDateString);
                Date endDate = sdf.parse(endDateString);

                if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0)
                {
                    sum += rec.getTotalCases();
                    count++;
                
                }
            }
            catch (ParseException e) {
            e.printStackTrace();
            return "null";
            }
        }
        
        float average = sum / count;
        
        if (Float.isNaN(average))
        {
            return "There are no records of Total Cases available for this time period";
        }
        
        return "Average Of Total Cases" + "\n \n" + "           " + average;
        
    }
    
    
}
