import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

/**
 * The WelcomeScreen class is the first panel of the Application. 
 * 
 * This class allows the user to view a welcome message and provides instructions on the basic use of the Application.
 * If provided with a start and end date, it displays the selected date range along with the welcome message.
 * Otherwise, it displays only the welcome message.
 * 
 * @author (Manav Sukheja)
 */
public class WelcomeScreen 
{
    
    /**
     * Constructs a WelcomeScreen object with the given root BorderPane and date range.
     * 
     * @param root The BorderPane to which the welcome message and date range (if available) will be added.
     * @param startDate The start date of the selected date range.
     * @param endDate The end date of the selected date range.
     */
    public WelcomeScreen(BorderPane root, String startDate, String endDate)
    {
        Text welcomeText = new Text();
        
        // Set the welcome message text
        welcomeText.setText("Welcome to this Covid Data Program. \n \n This program allows you to find Covid data such as number of deaths " + 
        "or number of cases, \n in a certain London borough, in a certain Date Range. Select the dates from the dropdown \n boxes above " +
        "and click the buttons below to navigate through the panels");
        
        // Set the font properties 
        welcomeText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        welcomeText.setX(170);
        welcomeText.setY(80);
        
        // Check if both start date and end date are provided
        if (startDate != null && endDate != null)
        {
            Text dateRange = new Text();
            // Set the selected date range text
            dateRange.setText("Selected Date Range: " + startDate + " to " + endDate);
            dateRange.setX(170);
            dateRange.setY(250);
            
            dateRange.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
            
            Group welcomeTexts = new Group(welcomeText, dateRange);
            
            root.setCenter(welcomeTexts);
        }
        else
        {
            // If start date or end date is not provided, set only the welcome message as the center content
            root.setCenter(welcomeText);
        }
    }
}
