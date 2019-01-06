package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private static GridPane root = new GridPane();
    private static ScrollPane sp = new ScrollPane(root);

    private static ArrayList<MojeBoxy> boxy = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        sp.setFitToWidth(true);

        primaryStage.setScene(new Scene(sp, 1280, 720));
        primaryStage.centerOnScreen();
        createbttns(5);

        Button checkButton = new Button();
        checkButton.setText("Check used Meals");
        checkButton.setMinWidth(100);
        root.add(checkButton, 1, 5);
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkIfBreakfastSelected(boxy);
                checkIfLunchSelected(boxy);
                checkIfDinnerSelected(boxy);

            }
        });


        primaryStage.show();
    }

    public void createbttns(int number){
        for(int i=1; i<=number; i++){
            Label btn = new Label();
            btn.setWrapText(true);
            btn.setText("Day " + i);
            btn.setMinSize(75,15);
            root.add(btn, i, 0);
            root.setHgap(25);
            for(int j =0; j<3; j++){

                //
                CheckBox breakfast = new CheckBox();
                breakfast.setText("Breakfast");
                root.add(breakfast, i, 1);

                //
                CheckBox lunch = new CheckBox();
                lunch.setText("Lunch");
                root.add(lunch, i, 2);

                //
                CheckBox dinner = new CheckBox();
                dinner.setText("Dinner");
                root.add(dinner, i, 3);

                boxy.add(new MojeBoxy(breakfast, lunch, dinner));
            }

        }
    }


    public void checkIfBreakfastSelected(ArrayList<MojeBoxy> arrayList){

        for(int i =0; i<arrayList.size(); i++) {
           if(arrayList.get(i).getBreakfastCheck().isSelected())
            System.out.println("breakfast is checked for " + (i/3+1) + " day");

        }
    }

    public void checkIfLunchSelected(ArrayList<MojeBoxy> arrayList){

        for(int i =0; i<arrayList.size(); i++) {
            if(arrayList.get(i).getLunchCheck().isSelected())
                System.out.println("lunch is checked for " + (i/3+1) + " day");

        }
    }


    public void checkIfDinnerSelected(ArrayList<MojeBoxy> arrayList){

        for(int i =0; i<arrayList.size(); i++) {
            if(arrayList.get(i).getDinnerCheck().isSelected())
                System.out.println("Dinner is checked for " + (i/3+1) + " day");

        }
    }

    public static void main(String[] args) {
        launch(args);

    }

}
