package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main extends Application {
    private static GridPane root = new GridPane();
    private static ScrollPane sp = new ScrollPane(root);
    private static int howManyNights = 5;

    private static ArrayList<MojeBoxy> boxy = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        sp.setFitToWidth(true);

        primaryStage.setScene(new Scene(sp, 1280, 720));
        primaryStage.centerOnScreen();
        createbttns(howManyNights);



        Label startLabel = new Label("Start Date:");
        root.add(startLabel,0,0);
        GridPane.setColumnSpan(startLabel,2);
        GridPane.setHalignment(startLabel, HPos.CENTER);


        Label endLabel = new Label("End Date:");
        GridPane.setColumnSpan(endLabel,2);
        GridPane.setHalignment(endLabel, HPos.CENTER);
        root.add(endLabel,2,0);


        DatePicker startDatePicker = new DatePicker();
        root.add(startDatePicker, 0 ,1);
        GridPane.setColumnSpan(startDatePicker,2);
        GridPane.setHalignment(startDatePicker, HPos.CENTER);
     //   startDatePicker.setValue(LocalDate.now());

        DatePicker endDatePicker = new DatePicker();
        root.add(endDatePicker, 2 ,1);
        GridPane.setColumnSpan(endDatePicker,2);
        GridPane.setHalignment(endDatePicker, HPos.CENTER);
      //  endDatePicker.setValue(startDatePicker.getValue());


        System.out.println(startDatePicker.getValue());

        TextField startTime = new TextField();
        root.add(startTime,0,3);
        GridPane.setColumnSpan(startTime,2);
        GridPane.setHalignment(startTime, HPos.CENTER);

        TextField endTime = new TextField();
        root.add(endTime,2,3);
        GridPane.setColumnSpan(endTime,2);
        GridPane.setHalignment(endTime, HPos.CENTER);



        String format = "yyyy-MM-ddhh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.GERMANY);


        Button checkButton = new Button();
        checkButton.setText("Check used Meals");
        checkButton.setMinWidth(100);
        root.add(checkButton, 0, 8);
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkIfBreakfastSelected(boxy);
                try {
                    String date1 = startDatePicker.getValue().toString().trim();
                    String date2 = endDatePicker.getValue().toString().trim();
                    Date dateObj1 = sdf.parse(date1 + "" + String.valueOf(startTime.getText()));
                    Date dateObj2 = sdf.parse(date2 + " " + String.valueOf(endTime.getText()));
                    System.out.println(dateObj1);
                    System.out.println(dateObj2 + "\n");
                    long diff = ((dateObj2.getTime() - dateObj1.getTime()));
                    System.out.println(diff);

                } catch (ParseException e){
                    System.out.println(e);
                }

            }
        });

        primaryStage.show();
    }


    public void createbttns(int number){
        for(int i=0; i<=number; i++){
            Label btn = new Label();
            btn.setWrapText(true);
            btn.setText("Day " + i);
            btn.setMinSize(75,15);
            root.add(btn, i, 4);
            root.setHgap(25);
            for(int j =0; j<3; j++){

                //
                CheckBox breakfast = new CheckBox();
                breakfast.setText("Breakfast");
                root.add(breakfast, i, 5);

                //
                CheckBox lunch = new CheckBox();
                lunch.setText("Lunch");
                root.add(lunch, i, 6);

                //
                CheckBox dinner = new CheckBox();
                dinner.setText("Dinner");
                root.add(dinner, i, 7);

                boxy.add(new MojeBoxy(breakfast, lunch, dinner));
            }

        }
    }


    private void checkIfBreakfastSelected(ArrayList<MojeBoxy> arrayList){

        int bNum=0 , lNum =0, dNum =0;


        for (MojeBoxy anArrayList : arrayList) {
            if (anArrayList.getBreakfastCheck().isSelected()) {
                bNum++;
            }
            if (anArrayList.getLunchCheck().isSelected()) {
                lNum++;
            }
            if (anArrayList.getDinnerCheck().isSelected()) {
                dNum++;
            }
        }

        System.out.println("You are entitled to:\n" + (howManyNights-bNum) + " breakfasts\n"
                                        + (howManyNights-lNum) + " lunches\n"
                                        + (howManyNights-dNum) + " dinners" +
                                        "\n" );


    }


    public static void main(String[] args) {
        launch(args);

    }

}
