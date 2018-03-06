/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX_Testing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Evan
 */
public class Scenes extends Application {

    Stage window;
    Scene scene1, scene2;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        Label label1 = new Label("Welcome to Scene 1!");
        Button btn1 = new Button("Go to Scene 2");
        btn1.setOnAction(e -> {
            boolean result = ConfirmBox.display("title of window", "Sure you wanna send naked pics of your girlfriend..?");
            System.out.println(result);
        }); //sending parameters to display class
        
        //Layout 1 - vertical column
        StackPane layout1 = new StackPane();
        layout1.getChildren().add(btn1);
        scene1 = new Scene(layout1, 200, 200);
        
        //Button 2
        Button btn2 = new Button("This is scene 2, go back now!");
        btn2.setOnAction(e -> window.setScene(scene1));
        
        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(btn2);
        scene2 = new Scene(layout2, 600, 300);
        
        window.setScene(scene1);
        window.setTitle("Title");
        window.show();
        
        StackPane layout = new StackPane();

        
        Scene scene = new Scene(layout, 300, 250);
        
    }
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
