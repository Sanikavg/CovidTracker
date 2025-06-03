import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;

/**
 * This class shows a map with buttons corresponding to each borough of London.
 * Users can click on a button to view data for the selected borough within a specified date range.
 * It assigns a color based on the number of total deaths due to Covid-19 in that borough.
 *
 * @author (Kyran Bailey)
 */
public class Map
{
    /**
     * Constructs a Map object with the given start date, end date, and root BorderPane.
     * 
     * @param startDate The start date of the selected date range.
     * @param endDate The end date of the selected date range.
     * @param root The BorderPane to which the map layout will be added.
     */
    public Map(String startDate, String endDate, BorderPane root)
    {
        // Creating a label telling the user what to do and what the colors indicate
        Label label = new Label("Choose a borough to view data \n red: >1000 deaths \n orange: >500 deaths \n green: low deaths ");
        label.setStyle("-fx-font-size: 14px; -fx-background-color: #F4F4F4; -fx-pref-width: 210px; -fx-pref-height: 400px;");
        
        // Load Covid data
        CovidDataLoader records = new CovidDataLoader();
        ArrayList<CovidData> recordList = records.load();
        ArrayList<Button> buttons = new ArrayList<>();
        String[] boroughs = {"Enfield", "Barnet", "Haringey", "Waltham Forest", "Harrow", "Brent", "Camden", "Islington", "Hackney", "Redbridge",
            "Havering", "Hillingdon", "Ealing", "Kensington And Chelsea", "Westminster", "Tower Hamlets", "Newham", "Barking And Dagenham", "Hounslow",
            "Hammersmith And Fulham", "Wandsworth", "City Of London", "Greenwich", "Bexley", "Richmond Upon Thames", "Merton", "Lambeth", "Southwark", "Lewisham", 
            "Kingston Upon Thames", "Sutton", "Croydon", "Bromley"};
        
        // Create buttons for each borough
        for (int i = 0; i < boroughs.length; i++) {
            Button button = new Button(boroughs[i]);
            buttons.add(button);
            
            // Set action event for each button
            buttons.get(i).setOnAction(event -> select(button.getText(), startDate, endDate));
            
            // Set button color based on total deaths
            for (CovidData rec : recordList) {
                if (boroughs[i].equals(rec.getBorough())) {
                    if (rec.getTotalDeaths() > 1000) {
                        buttons.get(i).setStyle("-fx-background-color: #FF0000; -fx-font-size: 16px; -fx-pref-width: 210px;");
                    }
                    else if (rec.getTotalDeaths() > 500) {
                        buttons.get(i).setStyle("-fx-background-color: orange; -fx-font-size: 16px; -fx-pref-width: 210px;");
                    }
                    else {
                        buttons.get(i).setStyle("-fx-background-color: lightGreen; -fx-font-size: 16px; -fx-pref-width: 210px;");
                    }
                    break;
                }
            }
        }
        
        // Create a new grid pane
        GridPane map = new GridPane();
        map.setPadding(new Insets(10, 10, 10, 10));
        
        // Adding label and buttons to grid pane
        map.add(label, 0, 0);
        map.add(buttons.get(0), 3, 0);
        map.add(buttons.get(1), 2, 1);
        map.add(buttons.get(2), 3, 1);
        map.add(buttons.get(3), 4, 1);
        map.add(buttons.get(4), 0, 2);
        map.add(buttons.get(5), 1, 2);
        map.add(buttons.get(6), 2, 2);
        map.add(buttons.get(7), 3, 2);
        map.add(buttons.get(8), 4, 2);
        map.add(buttons.get(9), 5, 2);
        map.add(buttons.get(10), 6, 2);
        map.add(buttons.get(11), 0, 3);
        map.add(buttons.get(12), 1, 3);
        map.add(buttons.get(13), 2, 3);
        map.add(buttons.get(14), 3, 3);
        map.add(buttons.get(15), 4, 3);
        map.add(buttons.get(16), 5, 3);
        map.add(buttons.get(17), 6, 3);
        map.add(buttons.get(18), 0, 4);
        map.add(buttons.get(19), 1, 4);
        map.add(buttons.get(20), 2, 4);
        map.add(buttons.get(21), 3, 4);
        map.add(buttons.get(22), 4, 4);
        map.add(buttons.get(23), 5, 4);
        map.add(buttons.get(24), 1, 5);
        map.add(buttons.get(25), 2, 5);
        map.add(buttons.get(26), 3, 5);
        map.add(buttons.get(27), 4, 5);
        map.add(buttons.get(28), 5, 5);
        map.add(buttons.get(29), 1, 6);
        map.add(buttons.get(30), 2, 6);
        map.add(buttons.get(31), 3, 6);
        map.add(buttons.get(32), 4, 6);

        map.setHgap(5);
        map.setVgap(10);
        
        root.setCenter(map);
    }
    
    /**
     * Method to handle selection of a borough. On clicking the button, user can view data for the borough.
     * 
     * @param borough The name of the selected borough.
     * @param startDate The start date of the selected date range.
     * @param endDate The end date of the selected date range.
     */
    private void select(String borough, String startDate, String endDate) {
        Borough boroughData = new Borough(borough, startDate, endDate);
    }
}