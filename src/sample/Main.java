package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private static Button checkForMeals = new Button("Calculate Per Diem");
    private static Button saveAsPDF = new Button("Save Per Diem");

    private static DatePicker startDatePicker = new DatePicker();
    private static DatePicker endDatePicker = new DatePicker();
    private static TextField startTimeTextField = new TextField();
    private static TextField endTimeTextField = new TextField();


    private static int daysEntitled;
    private static long diff;
    private static ArrayList<MojeBoxy> boxy = new ArrayList<>();
    private int row;
    private int columnIndex = 0;
    private int rowIndex = 1;
    private long reminder;
    private int days;


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(final Stage primaryStage) {
        countDays.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boxy.clear();
                daysEntitled = (Main.this.checkTravelTime());
                Main.this.centerScrollPane(daysEntitled);
            }
        });
        checkForMeals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkIfMealsSelected(boxy, days);
            }
        });
        borderPane.setCenter(sp);
        dateAndTimeVBox();
        bottomButtons();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(borderPane, 1280, 720));
        primaryStage.minHeightProperty().bind(primaryStage.widthProperty().multiply(0.5849));
        primaryStage.maxHeightProperty().bind(primaryStage.widthProperty().multiply(0.5849));
        primaryStage.show();
    }

    private void dateAndTimeVBox() {
        VBox leftSideVBox = new VBox();
        leftSideVBox.setPrefHeight(720);
        leftSideVBox.setMinWidth(200);
        borderPane.setLeft(leftSideVBox);
        BorderPane.setAlignment(leftSideVBox, Pos.CENTER);
        leftSideVBox.setAlignment(Pos.TOP_CENTER);
        Label startDateLabel = new Label("Start Date:");
        startDatePicker.prefHeight(27);
        startDatePicker.setPrefWidth(150);
        Label startTimeLabel = new Label("Start Time:");
        startTimeTextField.setPromptText("00:00");
        startTimeTextField.setAlignment(Pos.CENTER);
        VBox.setMargin(startDateLabel, new Insets(50, 0, 0, 0));
        VBox.setMargin(startTimeTextField, new Insets(0, 15, 0, 15));
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
        VBox.setMargin(endDateLabel, new Insets(50, 0, 0, 0));
        VBox.setMargin(endTimeTextField, new Insets(0, 15, 0, 15));
        leftSideVBox.getChildren().add(endDateLabel);
        leftSideVBox.getChildren().add(endDatePicker);
        leftSideVBox.getChildren().add(endTimeLabel);
        leftSideVBox.getChildren().add(endTimeTextField);

    }

    private void centerScrollPane(int daysEntitledToTravel) {
        sp.setFitToWidth(true);
        for (row = 0; row < daysEntitledToTravel; row++) {
            columnIndex++;
            if (row % 7 == 0) {
                rowIndex += 4;
                columnIndex = 0;
                Region rgn = new Region();
                rgn.setMinHeight(75);
                root.add(rgn, 0, rowIndex);
                rowIndex++;
            }
            Label dayLabel = new Label();
            dayLabel.setWrapText(true);
            dayLabel.setText("Day " + (row + 1));
            dayLabel.setMinSize(75, 15);
            root.add(dayLabel, columnIndex, rowIndex);
            root.setHgap(25);
            //
            CheckBox breakfast = new CheckBox();
            breakfast.setText("Breakfast");
            root.add(breakfast, columnIndex, rowIndex + 1);
            //
            CheckBox lunch = new CheckBox();
            lunch.setText("Lunch");
            root.add(lunch, columnIndex, rowIndex + 2);
            //
            CheckBox dinner = new CheckBox();
            dinner.setText("Dinner");
            root.add(dinner, columnIndex, rowIndex + 3);

            boxy.add(new MojeBoxy(breakfast, lunch, dinner));
        }
        if (reminder != 0) {
            Main.this.remainderDays(row, reminder);
        }
        rowIndex += 4;
        Region bottomRegion = new Region();
        bottomRegion.setMinHeight(3);
        bottomRegion.setMinWidth(800);
        GridPane.setMargin(bottomRegion, new Insets(40, 0, 0, 100));
        GridPane.setColumnSpan(bottomRegion, 7);
        bottomRegion.setStyle("-fx-background-color: #000000");
        root.add(bottomRegion, 0, rowIndex);

    }

    private void bottomButtons() {
        HBox bottomHBox = new HBox();
        borderPane.setBottom(bottomHBox);
        bottomHBox.getChildren().add(countDays);
        HBox.setMargin(countDays, new Insets(24, 0, 24, 24));
        Region leftRegion = new Region();
        leftRegion.setMinWidth(432);
        bottomHBox.getChildren().add(leftRegion);
        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.getChildren().add(checkForMeals);
        HBox.setMargin(checkForMeals, new Insets(24, 0, 0, 0));
        Region rightRegion = new Region();

        rightRegion.setMinWidth(400);
        bottomHBox.getChildren().add(rightRegion);
        bottomHBox.getChildren().add(saveAsPDF);
        HBox.setMargin(saveAsPDF, new Insets(24, 0, 0, 0));


    }

    private int checkTravelTime() {
        String format = "yyyy-MM-ddHH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.GERMANY);
        days = 0;
        try {
            String date1 = String.valueOf(startDatePicker.getValue());
            String date2 = String.valueOf(endDatePicker.getValue());
            Date dateObj1 = sdf.parse(date1 + "" + String.valueOf(startTimeTextField.getText()));
            Date dateObj2 = sdf.parse(date2 + " " + String.valueOf(endTimeTextField.getText()));
            System.out.println(dateObj1);
            System.out.println(dateObj2 + "\n");
            diff = ((dateObj2.getTime() - dateObj1.getTime()));
            System.out.println(diff);
            days = (int) (diff / 86_400_000);
            reminder = diff % 86_400_000;

            System.out.println("days entitled: " + days + "reminder: " + reminder);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return days;
    }

    private void remainderDays(int row, long remainder) {

        columnIndex++;
        row++;
        if (row % 7 == 1) {
            rowIndex += 4;
            columnIndex = 0;
            Region rgn = new Region();
            rgn.setMinHeight(75);
            root.add(rgn, 0, rowIndex);
            rowIndex++;
        }
        //remainder for less than 8hours
        if (remainder < 28_800_001 && remainder > 0) {
            Label dayLabel = new Label();
            dayLabel.setWrapText(true);
            dayLabel.setText("Day " + row);
            dayLabel.setMinSize(75, 15);
            GridPane.setColumnSpan(dayLabel, 2);
            root.add(dayLabel, columnIndex, rowIndex);
            root.setHgap(25);
            //
            CheckBox breakfast = new CheckBox();
            breakfast.setText("Breakfast for day <8h");
            GridPane.setColumnSpan(breakfast, 2);
            root.add(breakfast, columnIndex, rowIndex + 1);
            //
            CheckBox lunch = new CheckBox();
            lunch.setText("Lunch for day <8h");
            GridPane.setColumnSpan(lunch, 2);
            root.add(lunch, columnIndex, rowIndex + 2);
            //
            CheckBox dinner = new CheckBox();
            dinner.setText("Dinner for day <8h");
            GridPane.setColumnSpan(dinner, 2);
            root.add(dinner, columnIndex, rowIndex + 3);
            boxy.add(new MojeBoxy(breakfast, lunch, dinner));
        }
        //remainder for more than 8hours but less than 12h
        if (remainder > 28_800_001 && remainder < 43_200_000) {
            Label dayLabel = new Label();
            dayLabel.setWrapText(true);
            dayLabel.setText("Day " + row);
            dayLabel.setMinSize(75, 15);
            GridPane.setColumnSpan(dayLabel, 2);
            root.add(dayLabel, columnIndex, rowIndex);
            root.setHgap(25);
            //
            CheckBox breakfast = new CheckBox();
            breakfast.setText("Breakfast for day 8-12h");
            GridPane.setColumnSpan(breakfast, 2);
            root.add(breakfast, columnIndex, rowIndex + 1);
            //
            CheckBox lunch = new CheckBox();
            lunch.setText("Lunch for day 8-12h");
            GridPane.setColumnSpan(lunch, 2);
            root.add(lunch, columnIndex, rowIndex + 2);
            //
            CheckBox dinner = new CheckBox();
            dinner.setText("Dinner for day 8-12h");
            GridPane.setColumnSpan(dinner, 2);
            root.add(dinner, columnIndex, rowIndex + 3);
            boxy.add(new MojeBoxy(breakfast, lunch, dinner));
        }
        //remainder if more than 12hours
        if (remainder >= 43_200_000 && remainder < 86_400_000) {
            Label dayLabel = new Label();
            dayLabel.setWrapText(true);
            dayLabel.setText("Day " + row);
            dayLabel.setMinSize(75, 15);
            GridPane.setColumnSpan(dayLabel, 2);
            root.add(dayLabel, columnIndex, rowIndex);
            root.setHgap(25);
            for (int j = 0; j < 3; j++) {
                //
                CheckBox breakfast = new CheckBox();
                breakfast.setText("Breakfast 12h travel");
                GridPane.setColumnSpan(breakfast, 2);
                root.add(breakfast, columnIndex, rowIndex + 1);
                //
                CheckBox lunch = new CheckBox();
                lunch.setText("Lunch 12h travel");
                GridPane.setColumnSpan(lunch, 2);
                root.add(lunch, columnIndex, rowIndex + 2);
                //
                CheckBox dinner = new CheckBox();
                dinner.setText("Dinner 12h travel");
                GridPane.setColumnSpan(dinner, 2);
                root.add(dinner, columnIndex, rowIndex + 3);
                boxy.add(new MojeBoxy(breakfast, lunch, dinner));
            }
        }
    }

    private void checkIfMealsSelected(ArrayList<MojeBoxy> arrayList, int days) {
        int bNum = 0, lNum = 0, dNum = 0;
        System.out.println(arrayList.size());

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getBreakfastCheck().isSelected())
                bNum++;
            if (arrayList.get(i).getLunchCheck().isSelected())
                lNum++;
            if (arrayList.get(i).getDinnerCheck().isSelected())
                dNum++;
        }
        System.out.println("You are entitled to:\n" + (days - bNum) + " breakfasts\n"
                + (days - lNum) + " lunches\n"
                + (days - dNum) + " dinners" +
                "\n");
    }
}