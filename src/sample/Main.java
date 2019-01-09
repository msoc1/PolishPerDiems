package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Main extends Application {
    private static GridPane root = new GridPane();
    private static BorderPane borderPane = new BorderPane();
    private static ScrollPane sp = new ScrollPane(root);
    private static Button countDays = new Button("Count Trip Length");
    private static DatePicker startDatePicker = new DatePicker();
    private static DatePicker endDatePicker = new DatePicker();
    private static TextField startTimeTextField = new TextField();
    private static TextField endTimeTextField = new TextField();



    private static int daysEntitled;
    private static long diff;
    private int row;
    private int columnIndex=0;
    private int rowIndex=1;
    private long reminder;

    private static ArrayList<MojeBoxy> boxy = new ArrayList<>();


    @Override
    public void start(Stage primaryStage)  {
        countDays.setOnAction(event -> {

            daysEntitled = (checkTravelTime());
            centerScrollPane(daysEntitled);
            remainderDays(row, reminder);

        });





        dateAndTimeVBox();
        bottomButtons();


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(borderPane, 1280, 720));
        primaryStage.show();
    }


    private void dateAndTimeVBox(){
        VBox leftSideVBox = new VBox();
        leftSideVBox.setPrefHeight(720);
        leftSideVBox.setPrefWidth(186);
        borderPane.setLeft(leftSideVBox);
        BorderPane.setAlignment(leftSideVBox, Pos.CENTER);
        leftSideVBox.setAlignment(Pos.TOP_CENTER);
        Label startDateLabel = new Label("Start Date:");
        startDatePicker.prefHeight(27);
        startDatePicker.setPrefWidth(150);
        Label startTimeLabel = new Label("Start Time:");
        startTimeTextField.setPromptText("00:00");
        startTimeTextField.setAlignment(Pos.CENTER);
        VBox.setMargin(startDateLabel, new Insets(50,0,0,0));
        VBox.setMargin(startTimeTextField, new Insets(0,15,0,15));
        leftSideVBox.getChildren().add(startDateLabel);
        leftSideVBox.getChildren().add(startDatePicker);
        leftSideVBox.getChildren().add(startTimeLabel);
        leftSideVBox.getChildren().add(startTimeTextField);
        Label endDateLabel = new Label("End Date:");
        endDatePicker.prefHeight(27);
        endDatePicker.setPrefWidth(150);
        Label endTimeLabel = new Label("End Time:");
        endTimeTextField.setPromptText("00:00");
        endTimeTextField.setAlignment(Pos.CENTER);
        VBox.setMargin(endDateLabel, new Insets(50,0,0,0));
        VBox.setMargin(endTimeTextField, new Insets(0,15,0,15));
        leftSideVBox.getChildren().add(endDateLabel);
        leftSideVBox.getChildren().add(endDatePicker);
        leftSideVBox.getChildren().add(endTimeLabel);
        leftSideVBox.getChildren().add(endTimeTextField);
    }


    private void centerScrollPane(int daysEntitledToTravel){
       borderPane.setCenter(sp);
        sp.setFitToWidth(true);



        for (row = 0; row < daysEntitledToTravel; row++) {
            columnIndex++;

            if(row%7==0){
                rowIndex+=4;
                columnIndex=0;
                Region rgn = new Region();
                rgn.setMinHeight(75);
                root.add(rgn, 0, rowIndex);
                rowIndex++;

            }
            Label btn = new Label();
            btn.setWrapText(true);
            btn.setText("Day " + (row+1));
            btn.setMinSize(75, 15);
            root.add(btn, columnIndex,rowIndex);
            root.setHgap(25);

            for (int j = 0; j < 3; j++) {

                //
                CheckBox breakfast = new CheckBox();
                breakfast.setText("Breakfast");
                root.add(breakfast, columnIndex, rowIndex+1);

                //
                CheckBox lunch = new CheckBox();
                lunch.setText("Lunch");
                root.add(lunch, columnIndex, rowIndex+2);

                //
                CheckBox dinner = new CheckBox();
                dinner.setText("Dinner");
                root.add(dinner, columnIndex, rowIndex+3);

                boxy.add(new MojeBoxy(breakfast, lunch, dinner));
            }
        }
    }





    private void bottomButtons(){
        HBox bottomHBox = new HBox();
        borderPane.setBottom(bottomHBox);


        bottomHBox.getChildren().add(countDays);
        bottomHBox.setAlignment(Pos.TOP_LEFT);
    }


private int checkTravelTime() {
    String format = "yyyy-MM-ddHH:mm";
    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.GERMANY);
    int days =0;

        try {
            String date1 = startDatePicker.getValue().toString().trim();
            String date2 = endDatePicker.getValue().toString().trim();
            Date dateObj1 = sdf.parse(date1 + "" + String.valueOf(startTimeTextField.getText()));
            Date dateObj2 = sdf.parse(date2 + " " + String.valueOf(endTimeTextField.getText()));
            System.out.println(dateObj1);
            System.out.println(dateObj2 + "\n");
            diff = ((dateObj2.getTime() - dateObj1.getTime()));
            System.out.println(diff);

            days = (int) (diff / 86_400_000);
            reminder = diff % 86_400_000;


           // checkIfMealsSelected(boxy, days);

            System.out.println("days entitled: " + days + "reminder: " + reminder);

        } catch (ParseException e) {
            System.out.println(e);
        }
        return days;

}








//        primaryStage.setTitle("Hello World");
//        sp.setFitToWidth(true);
//
//        primaryStage.setScene(new Scene(sp, 1280, 720));
//        primaryStage.centerOnScreen();
//
//
//        Label startLabel = new Label("Start Date:");
//        root.add(startLabel, 0, 0);
//        GridPane.setColumnSpan(startLabel, 2);
//        GridPane.setHalignment(startLabel, HPos.CENTER);
//
//
//        Label endLabel = new Label("End Date:");
//        GridPane.setColumnSpan(endLabel, 2);
//        GridPane.setHalignment(endLabel, HPos.CENTER);
//        root.add(endLabel, 2, 0);
//
//
//        DatePicker startDatePicker = new DatePicker();
//        root.add(startDatePicker, 0, 1);
//        GridPane.setColumnSpan(startDatePicker, 2);
//        GridPane.setHalignment(startDatePicker, HPos.CENTER);
//
//        DatePicker endDatePicker = new DatePicker();
//        root.add(endDatePicker, 2, 1);
//        GridPane.setColumnSpan(endDatePicker, 2);
//        GridPane.setHalignment(endDatePicker, HPos.CENTER);
//
//
//
//        TextField startTime = new TextField();
//        root.add(startTime, 0, 3);
//        GridPane.setColumnSpan(startTime, 2);
//        GridPane.setHalignment(startTime, HPos.CENTER);
//
//        TextField endTime = new TextField();
//        root.add(endTime, 2, 3);
//        GridPane.setColumnSpan(endTime, 2);
//        GridPane.setHalignment(endTime, HPos.CENTER);
//
//
//        String format = "yyyy-MM-ddHH:mm";
//        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.GERMANY);
//
//
//        Button checkButton = new Button();
//        checkButton.setText("Check used Meals");
//        checkButton.setMinWidth(100);
//        root.add(checkButton, 0, 8);
//        checkButton.setOnAction(event -> {
//            try {
//                String date1 = startDatePicker.getValue().toString().trim();
//                String date2 = endDatePicker.getValue().toString().trim();
//                Date dateObj1 = sdf.parse(date1 + "" + String.valueOf(startTime.getText()));
//                Date dateObj2 = sdf.parse(date2 + " " + String.valueOf(endTime.getText()));
//                System.out.println(dateObj1);
//                System.out.println(dateObj2 + "\n");
//                diff = ((dateObj2.getTime() - dateObj1.getTime()));
//                System.out.println(diff);
//
//                int days = (int) (diff / 86_400_000);
//                long reminder = diff % 86_400_000;
//
//                createbttns(days, reminder);
//                remainderDays(row, reminder);
//                checkIfMealsSelected(boxy,days);
//
//                System.out.println("days entitled: " + days + "reminder: " + reminder);
//
//            } catch (ParseException e) {
//                System.out.println(e);
//            }
//
//        });
//
//
//        primaryStage.show();
//    }
//
//
//    private void createbttns(int number, long reminder) {
//        for (row = 1; row <= number; row++) {
//            Label btn = new Label();
//            btn.setWrapText(true);
//            btn.setText("Day " + row);
//            btn.setMinSize(75, 15);
//            root.add(btn, row, 4);
//            root.setHgap(25);
//            for (int j = 0; j < 3; j++) {
//            reminder++;
//
//                //
//                CheckBox breakfast = new CheckBox();
//                breakfast.setText("Breakfast");
//                root.add(breakfast, row, 5);
//
//                //
//                CheckBox lunch = new CheckBox();
//                lunch.setText("Lunch");
//                root.add(lunch, row, 6);
//
//                //
//                CheckBox dinner = new CheckBox();
//                dinner.setText("Dinner");
//                root.add(dinner, row, 7);
//
//                System.out.println(row);
//                boxy.add(new MojeBoxy(breakfast, lunch, dinner));
//            }
//        }
//    }
//
    private void remainderDays(int row, long remainder) {
        columnIndex++;
        row++;
        //remainder for less than 8hours
        if (remainder < 28_800_001 && remainder > 0) {
            Label btn = new Label();
            btn.setWrapText(true);
            btn.setText("Day " + row );
            btn.setMinSize(75, 15);
            GridPane.setColumnSpan(btn,2);
            root.add(btn, columnIndex, rowIndex);
            root.setHgap(25);
            CheckBox breakfast = new CheckBox();
            breakfast.setText("Breakfast for day <8h");
            GridPane.setColumnSpan(breakfast,2);
            root.add(breakfast, columnIndex, rowIndex+1);

            //
            CheckBox lunch = new CheckBox();
            lunch.setText("Lunch for day <8h");
            GridPane.setColumnSpan(lunch,2);
            root.add(lunch, columnIndex, rowIndex+2);

            //
            CheckBox dinner = new CheckBox();
            dinner.setText("Dinner for day <8h");
            GridPane.setColumnSpan(dinner,2);
            root.add(dinner, columnIndex, rowIndex+3);


            boxy.add(new MojeBoxy(breakfast, lunch, dinner));
        }

        //remainder for more than 8hours but less than 12h
        if (remainder > 28_800_001 && remainder < 43_200_000) {
            Label btn = new Label();
            btn.setWrapText(true);
            btn.setText("Day " + row );
            btn.setMinSize(75, 15);
            GridPane.setColumnSpan(btn,2);

            root.add(btn, columnIndex, rowIndex);
            root.setHgap(25);
            CheckBox breakfast = new CheckBox();
            breakfast.setText("Breakfast for day 8-12h");
            GridPane.setColumnSpan(breakfast,2);

            root.add(breakfast, columnIndex, rowIndex+1);

            //
            CheckBox lunch = new CheckBox();
            lunch.setText("Lunch for day 8-12h");
            GridPane.setColumnSpan(lunch,2);
            root.add(lunch, columnIndex, rowIndex+2);

            //
            CheckBox dinner = new CheckBox();
            dinner.setText("Dinner for day 8-12h");
            GridPane.setColumnSpan(dinner,2);
            root.add(dinner, columnIndex, rowIndex+3);


            boxy.add(new MojeBoxy(breakfast, lunch, dinner));
        }

        if (remainder >= 43_200_000 && remainder < 86_400_000) {
            Label btn = new Label();
            btn.setWrapText(true);
            btn.setText("Day " + row);
            btn.setMinSize(75, 15);
            GridPane.setColumnSpan(btn,2);

            root.add(btn, columnIndex, rowIndex);
            root.setHgap(25);
            for (int j = 0; j < 3; j++) {

                //
                CheckBox breakfast = new CheckBox();
                breakfast.setText("Breakfast 12h travel");
                GridPane.setColumnSpan(breakfast,2);
                root.add(breakfast, columnIndex, rowIndex+1);

                //
                CheckBox lunch = new CheckBox();
                lunch.setText("Lunch 12h travel");
                GridPane.setColumnSpan(lunch,2);
                root.add(lunch, columnIndex, rowIndex+2);

                //
                CheckBox dinner = new CheckBox();
                dinner.setText("Dinner 12h travel");
                GridPane.setColumnSpan(dinner,2);
                root.add(dinner, columnIndex, rowIndex+3);

               // System.out.println(row);
                boxy.add(new MojeBoxy(breakfast, lunch, dinner));
            }
        }
    }

//    private void checkIfMealsSelected(ArrayList<MojeBoxy> arrayList, int days) {
//
//        int bNum = 0, lNum = 0, dNum = 0;
//
//
//        for (MojeBoxy anArrayList : arrayList) {
//            if (anArrayList.getBreakfastCheck().isSelected()) {
//                bNum++;
//            }
//            if (anArrayList.getLunchCheck().isSelected()) {
//                lNum++;
//            }
//            if (anArrayList.getDinnerCheck().isSelected()) {
//                dNum++;
//            }
//        }
//
//        System.out.println("You are entitled to:\n" + (days - bNum) + " breakfasts\n"
//                + (days - lNum) + " lunches\n"
//                + (days - dNum) + " dinners" +
//                "\n");
//
//
//    }


    public static void main(String[] args) {
        launch(args);

    }

}
