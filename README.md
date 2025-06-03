# CovidTracker: Group Project
Description of the GUI and functionality of all 4 panels:

Welcome:
- When the application is first started the user lands on a Welcome
screen window which gives them instructions on the basic use of
the application.
- The buttons for navigation are disabled until the user selects a
“from” date and a “to” date. Upon selecting an invalid data range
or no data range, an appropriate error message is displayed.
- Once a valid data range is selected, the forward and back button is
enabled, and the user is free to navigate through the panels.
Further the user is also shown the selected data range on the
welcome screen.

Map:
- The Map panel displays all the London Boroughs as a visual
representation of COVID death rates. The boroughs are displayed
in a geographically accurate manner, so the northern boroughs are
near the top of the window and the southern boroughs are near the
bottom etc.
- If the total number of deaths in that borough is above 1000, the
colour of that borough button on the panel is red. If the total
number of deaths in that borough is greater than 500 but less than
1000, then the colour of that borough button on the panel is
orange. If the total number of deaths in that borough is below 500,
the colour of that borough button is light green.
- Each borough is a button which users can click to find out more
information about the borough. This is explained in the Borough
classs functionality.

Borough:
- The Borough panel displays the relevant data and dates for the
borough that has been selected including the borough name, the
date, google mobility data and the covid data.
- It is shown in the form of a table, to sort the results by the
columns. This table is displayed in a new window with all the
details shown clearly in a list.
- This new window is only opened after the user has picked a date
range and chosen a London borough from the Map.

Statistics:
- The Statistics panel presents a series of statistics for the period of
the dates selected by the user.
- When a time period and a borough is selected, the average Google
Mobility Measure for parks, the average Google Mobility Measure
for transit, the total number of deaths and the average total cases
are displayed.
- The user can navigate through these statistics using buttons which
are located at the left and right corners of the screen.
- If a certain date range has no records for that particular type of
data, then the message “No records available” is displayed.

CovidSymptomChecker: (Challenge Task)
- The CovidSymptomChecker class provides a user interface for
checking COVID-19 symptoms and suggesting appropriate actions
based on user selections.
- It contains checkboxes for various symptoms, a button to trigger
symptom checking, and displays results using alert dialogs.
- The class aims to assist users in assessing their symptoms and
provides guidance on whether they should seek medical help or
follow home remedy suggestions.

Description of Unit Tests:
-Unit testing was conducted on the Borough class to ensure its
functionality and accuracy.
- Ensure that data retrieval methods within the Borough class, such
as fetching Google mobility data and COVID-19 statistics, return
the expected values for each borough.
- Verify that the data retrieved corresponds accurately to the
selected borough and date range.
- Test the sorting functionality of the table displaying borough data.
- Verify that it appropriately sorts the data in ascending or
descending order based on the selected attribute (e.g., date,
number of cases, mobility measure).
- Evaluate the behavior of the Borough class when handling edge
cases, such as selecting extreme date ranges or boroughs with
minimal data.

Distribution of tasks:
- Application Window: Sanika Gadgil
- Welcome Screen: Manav Sukheja
- Map: Kyran Bailey
- Statistics: Sanika Gadgil
- Borough: Jasmin Bedi
- CovidSymptomChecker (Challenge Task): Sanika Gadgil
- Unit Testing: Jasmin Bedi, Kyran Bailey
- The Report: Manav Sukheja
