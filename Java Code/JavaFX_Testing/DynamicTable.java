package JavaFX_Testing;

//import statements
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
 
/**
 * 
 * @author Evan Glass
 */
 
public class DynamicTable extends Application{
 
    //scenes, tableview, List, etc
    private ObservableList<ObservableList> data;
    private TableView tableview;
    Stage window;
    Scene scene1, scene2, scene3;
    StringBuilder queryInputSB = new StringBuilder();
    String SQL = "";
    String queryInputString;
 
    //JavaFX main method contains "launch(args);" when you are dealing with a JavaFX program, the main method is pretty much ignored.
    
    public static void main(String[] args) {
        launch(args);
    }
 
    //connection to the database
    public void buildData(){
          Connection c ;
          data = FXCollections.observableArrayList();
          try{
            c = DBConnect.connect();
            /****************************************
             * this if / else statement is used because when the program is launched, "SQL" has 0 length, causing the "if" to trigger.. this is used to populate the Table.
             * then, once the user requests any new queries, the "SQL" global variable is updated, and passes the new statement instead.
             */
            if(SQL.length() <= 0)
            {
                queryInputString = "select * from SCREEN;"; //
            }
            else
            {
               queryInputString = queryInputSB.toString(); 
            }
            
            SQL = queryInputString;
            //ResultSet used to execute the query
            ResultSet rs = c.createStatement().executeQuery(SQL);
            //clearing the columns is useful to "refresh" the table. without it, each query would seemingly append to the tableview in the program. very ugly
            tableview.getColumns().clear();
            /**********************************************************************
             * Table columns added dynamically. this logic is from stackExchange *
             **********************************************************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                //addding column names from the resultSet metadata "TableColumn"
                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }
 
            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row );
                data.add(row);
 
            }
 
            //FINALLY ADDED TO TableView
            tableview.setItems(data);
          }catch(SQLException e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
          //resetting the stringbuilder for the SQL statement so that new queries can be requested
          queryInputSB.setLength(0);
      }
 
    
      @Override
      public void start(Stage primaryStage) throws Exception {
        //setting the initial main menu scene
        window = primaryStage;
        window.setTitle("Main Menu");
        //upon clicking X to close, consume this request and call the method closeProgram();
        window.setOnCloseRequest(e -> {
                e.consume();
                closeProgram();
        });

        VBox centerMenu = new VBox();
        Button goBack = new Button("Back");
        goBack.setOnAction(e -> window.setScene(scene1));
        centerMenu.getChildren().add(goBack);

        //grid pane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        //grid pane
        GridPane backGrid = new GridPane();
        backGrid.setPadding(new Insets(20, 20, 20, 20));
        backGrid.setVgap(8);
        backGrid.setHgap(10);

        GridPane backGrid1 = new GridPane();
        backGrid1.setPadding(new Insets(20, 20, 20, 20));
        backGrid1.setVgap(8);
        backGrid1.setHgap(10);

        GridPane conGrid = new GridPane();
        conGrid.setPadding(new Insets(20, 20, 20, 20));
        conGrid.setVgap(8);
        conGrid.setHgap(10);

        Button backButton = new Button("Go Back");
        backButton.setOnAction(e -> window.setScene(scene1));

        Button backButton1 = new Button("Go Back");
        backButton1.setOnAction(e -> window.setScene(scene1));



        //query label for grid pane
        Label query1Label = new Label("Search for movie cost > ");              //select MOVIE_TITLE as 'Movie Title', MOVIE_GENRE as Genre, MOVIE_COST as Cost from MOVIE where MOVIE_COST > 40;
        GridPane.setConstraints(query1Label, 0, 0);

        //query input
        TextField query1Input = new TextField("");
        query1Input.setPromptText("00.00");
        GridPane.setConstraints(query1Input, 1, 0);

        //Query Label
        Label query2Label = new Label("Show records in Table:");
        GridPane.setConstraints(query2Label, 0, 1);

        Label query5Label = new Label("Show list of Tables");
        GridPane.setConstraints(query5Label, 0, 2);
        
        //Query input
        TextField query2Input = new TextField();
        query2Input.setPromptText("");
        GridPane.setConstraints(query2Input, 1, 1);

        //Query Label
        Label query3Label = new Label("Enter Ticket ID Number:");
        GridPane.setConstraints(query3Label, 3, 0);

        //Query input
        TextField query3Input = new TextField();
        query3Input.setPromptText("99999");
        GridPane.setConstraints(query3Input, 4, 0);

        Label query4Label = new Label("Showtimes at location ID: ");
        GridPane.setConstraints(query4Label, 3, 1);

        //Query input
        TextField query4Input = new TextField();
        query4Input.setPromptText("11, 12, or 13");
        GridPane.setConstraints(query4Input, 4, 1);

        //----------------------------------------------------------------------------------------------


        //query label for grid pane
        Label q1Label = new Label("Enter port number: ");
        GridPane.setConstraints(q1Label, 0, 0);

        //query input
        TextField q1Input = new TextField("1433");
        //q1Input.setPromptText("");
        GridPane.setConstraints(q1Input, 1, 0);

        //Query Label
        Label q2Label = new Label("Enter server name: ");
        GridPane.setConstraints(q2Label, 0, 1);

        //Query input
        TextField q2Input = new TextField();
        //passInput.setPromptText("");
        GridPane.setConstraints(q2Input, 1, 1);

        //Query Label
        Label q3Label = new Label("Enter database name: ");
        GridPane.setConstraints(q3Label, 0, 2);

        //Query input
        TextField q3Input = new TextField();
        // q3Input.setPromptText("");
        GridPane.setConstraints(q3Input, 1, 2);

        Label q4Label = new Label("Are you using Windows Authentication?");
        GridPane.setConstraints(q4Label, 0, 3);

        //CheckBox
        CheckBox box1 = new CheckBox("");
        GridPane.setConstraints(box1, 1, 3);

        Label q5Label = new Label("For a SQL connection:");
        GridPane.setConstraints(q5Label, 0, 5);

        Label q6Label = new Label("Username: ");
        GridPane.setConstraints(q6Label, 0, 6);

        //Query input
        TextField q6Input = new TextField();
        //q4Input.setPromptText("111-2222");
        GridPane.setConstraints(q6Input, 1, 6);

        Label q7Label = new Label("Password: ");
        GridPane.setConstraints(q7Label, 0, 7);
        
        tableview = new TableView();
        GridPane.setConstraints(tableview, 0, 2);

        //Query input
        TextField q7Input = new TextField();
        //q4Input.setPromptText("111-2222");
        GridPane.setConstraints(q7Input, 1, 7);
        
        
        
        //**************************************************
        //TableView
        tableview = new TableView();
        buildData();
        //**************************************************
                       //connection menu a work in progress     
        Button connectButton = new Button("Connect");
        GridPane.setConstraints(connectButton, 23, 0);
        connectButton.setOnAction(e -> {
            
            q1Input.getText(); //port number
            q2Input.getText(); //server name
            q3Input.getText(); //database name
            q6Input.getText(); //username
            q7Input.getText(); //password
            
            //connectSB.append("jdbc:sqlserver://" + q2Input.getText() + ";databaseName=" + q3Input.getText() + ";integratedSecurity=True");
            //System.out.println();
            //System.out.println(connectSB);
            //String connectSB1 = connectSB.toString();
            //connectSB.setLength(0);
        });        
        //------------------------------------------------------------------------------------

        Button searchButton1 = new Button("Go");
        searchButton1.setOnAction(e -> {
            queryInputSB.append("select MOVIE_ID as 'Movie ID', MOVIE_GENRE as Genre, MOVIE_COST as Cost from MOVIE where MOVIE_COST > " + query1Input.getText() + ";");  //select MOVIE_TITLE as 'Movie Title', MOVIE_GENRE as Genre, MOVIE_COST as Cost from MOVIE where MOVIE_COST > 40;
            buildData();
        });
        GridPane.setConstraints(searchButton1, 2, 0);

        Button searchButton2 = new Button("Go");
        searchButton2.setOnAction(e -> {
            queryInputSB.append("select * from " + query2Input.getText() + ";");  //
            buildData();
        });
        GridPane.setConstraints(searchButton2, 2, 1);

        Button searchButton3 = new Button("Go");
        searchButton3.setOnAction(e -> {
            queryInputSB.append("select * from TICKET t, CUSTOMER c WHERE t.customer_id = c.cust_id and ticket_id LIKE '%" + query3Input.getText() + "%';");  //some examples: 12346, 12347, 23457, 34567
            buildData();
        });
        GridPane.setConstraints(searchButton3, 5, 0);

        Button searchButton4 = new Button("Go");
        searchButton4.setOnAction(e -> {
            queryInputSB.append("select title as 'Movie Title', start_time as 'Start', end_time as 'End', screen_ID as 'Screen Number', location_title as 'Location' from MOVIE m, SHOWTIME s, MOVIE_LOCATION ml, THEATER_LOCATION tl where m.movie_id = s.movie_id and m.movie_id = ml.movie_id and ml.location_id = tl.location_id and tl.location_id = " + query4Input.getText() + ";"); 
            buildData();
        });
        GridPane.setConstraints(searchButton4, 5, 1);
        
        Button tableListButton = new Button("Go");
        tableListButton.setOnAction(e -> {
            queryInputSB.append("SELECT TABLE_NAME as 'Table Names' FROM INFORMATION_SCHEMA.TABLES;"); 
            buildData();
        });
        GridPane.setConstraints(tableListButton, 1, 2);

        grid.getChildren().addAll(query1Label,
                query1Input,
                query2Label,
                query2Input,
                query3Label,
                query3Input,
                query4Label,
                query4Input,
                query5Label,
                searchButton1,
                searchButton2,
                searchButton3,
                searchButton4,
                tableListButton
                );
        backGrid.getChildren().add(backButton);
        backGrid1.getChildren().addAll(backButton1, connectButton);

        conGrid.getChildren().addAll(q1Label, q1Input, q2Label, q2Input, q3Label, q3Input, q4Label, box1, q5Label, q6Label, q6Input, q7Label, q7Input);
        //Scene loginScene = new Scene(grid, 300, 200);

        BorderPane borderPane = new BorderPane();
        //borderPane.setTop(topMenu);
        //borderPane.setLeft(leftMenu);
        borderPane.setBottom(backGrid);
        borderPane.setTop(grid);
        borderPane.setCenter(tableview);

        BorderPane connectionPane = new BorderPane();
        connectionPane.setCenter(conGrid);
        connectionPane.setBottom(backGrid1);

        //Button 1
        Button mainMenuCloseButton = new Button("Close Program");
        mainMenuCloseButton.setOnAction(e
                -> {
            closeProgram();
        });

        //Button 2
        Button mainMenuForm1 = new Button("Go to Form 1");
        mainMenuForm1.setOnAction(e -> {
            //queryInputSB.append("select * from RENTAL");
            window.setScene(scene2);});

        //Button 3
        Button connection = new Button("Connection");
        connection.setOnAction(e -> window.setScene(scene3));

        //Layout 1 - Vertical Column
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(mainMenuForm1, connection, mainMenuCloseButton);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 400, 200);

        //Layout 2 - BorderPane
        scene2 = new Scene(borderPane, 800, 500);

        scene3 = new Scene(connectionPane, 400, 400);

        window.setScene(scene1);
        window.setTitle("Database Interaction Program");
        window.show();
          
      }
      //method for popup-window accidentily closing program
    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Title", "Sure you want to exit?");
        if (answer) {
            window.close();
        }
    }
}