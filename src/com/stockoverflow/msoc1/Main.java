package com.stockoverflow.msoc1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

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
    private static GridPane rightGridPane = new GridPane();
    private static DatePicker startDatePicker = new DatePicker();
    private static DatePicker endDatePicker = new DatePicker();
    private static TextField startTimeTextField = new TextField();
    private static TextField endTimeTextField = new TextField();
    private static Label daysAndHoursOfTravel = new Label();
    private static int daysEntitled;
    private static ArrayList<DaysCheckBox> boxy = new ArrayList<>();
    private final int DAYBELOW8HOURS = 0;
    private final int DAYBEBELOW12HOURS = 1;
    private final int DAYABOVE12HOURS = 2;
    private Label amountLabel8hTravelMealAmountBreakfast = new Label();
    private Label amountLabel8hTravelMealAmountLunch = new Label();
    private Label amountLabel8hTravelMealAmountDinner = new Label();
    private Label amountLabel8hTravelMealAmountRemainder = new Label();
    private Label amountLabel8hTravelMoneyAmountBreakfast = new Label();
    private Label amountLabel8hTravelMoneyAmountLunch = new Label();
    private Label amountLabel8hTravelMoneyAmountDinner = new Label();
    private Label amountLabel8hTravelMoneyAmountRemainder = new Label();
    private Label amountLabel8hTravelCountryBreakfast = new Label();
    private Label amountLabel8hTravelCountryLunch = new Label();
    private Label amountLabel8hTravelCountryDinner = new Label();
    private Label amountLabel8hTravelCountryRemainder = new Label();
    private Label amountLabel8to12hTravelMoneyAmountBreakfast = new Label();
    private Label amountLabel8to12hTravelMoneyAmountLunch = new Label();
    private Label amountLabel8to12hTravelMoneyAmountDinner = new Label();
    private Label amountLabel8to12hTravelMoneyAmountRemainder = new Label();
    private Label amountLabel8to12hTravelMealAmountBreakfast = new Label();
    private Label amountLabel8to12hTravelMealAmountLunch = new Label();
    private Label amountLabel8to12hTravelMealAmountDinner = new Label();
    private Label amountLabel8to12hTravelMealAmountRemainder = new Label();
    private Label amountLabel8to12hTravelCountryBreakfast = new Label();
    private Label amountLabel8to12hTravelCountryLunch = new Label();
    private Label amountLabel8to12hTravelCountryDinner = new Label();
    private Label amountLabel8to12hTravelCountryRemainder = new Label();
    private Label amountLabel12hTravelMoneyAmountBreakfast = new Label();
    private Label amountLabel12hTravelMoneyAmountLunch = new Label();
    private Label amountLabel12hTravelMoneyAmountDinner = new Label();
    private Label amountLabel12hTravelMoneyAmountRemainder = new Label();
    private Label amountLabel12hTravelMealAmountBreakfast = new Label();
    private Label amountLabel12hTravelMealAmountLunch = new Label();
    private Label amountLabel12hTravelMealAmountDinner = new Label();
    private Label amountLabel12hTravelMealAmountRemainder = new Label();
    private Label amountLabel12hTravelCountryBreakfast = new Label();
    private Label amountLabel12hTravelCountryLunch = new Label();
    private Label amountLabel12hTravelCountryDinner = new Label();
    private Label amountLabel12hTravelCountryRemainder = new Label();
    private Label amountLabelFullDayMoneyAmount = new Label();
    private Label amountLabelFullDayMealAmount = new Label();
    private Label amountLabelFullDayCountry = new Label();
    private int columnIndex = 0;
    private int rowIndex = 1;
    private long reminder;
    private static DateTimeStringConverter df = new DateTimeStringConverter();
    private static Date dateObj1;
    private long datt;


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(final Stage primaryStage) {
        borderPane.setCenter(sp);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);


        countDays.setOnAction(event -> {
            boxy.clear();
            rightGridPane.getChildren().clear();
            daysEntitled = (checkTravelTime());
            centerScrollPane(daysEntitled);
          //  teslaVBOX();
            checkForMeals.setDisable(false);
            sp.setVvalue((sp.getContent().getBoundsInLocal().getMaxY() + 181) );

            System.out.println(" bounds " + sp.getContent().getBoundsInLocal().getMaxY());


        });
        checkForMeals.setOnAction(event -> {
            clearLabels();
            checkIfMealsSelected(boxy);
            checkForMeals.setDisable(true);

        });

        dateAndTimeVBox();
        bottomButtons();
        topLabels();
        teslaVBOX();


        primaryStage.setTitle("Per Diem Calculator");
        primaryStage.setScene(new Scene(borderPane, 1420, 920));
        primaryStage.show();
    }

    private static void dateAndTimeVBox() {
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
        Label timeOfTravel = new Label("Your time of travel:");
        VBox.setMargin(timeOfTravel, new Insets(50, 0, 0, 0));
        leftSideVBox.getChildren().add(timeOfTravel);
        VBox.setMargin(daysAndHoursOfTravel, new Insets(5, 0, 0, 0));
        leftSideVBox.getChildren().add(daysAndHoursOfTravel);
    }

    private static void bottomButtons() {
        HBox bottomHBox = new HBox();
        borderPane.setBottom(bottomHBox);
        countDays.setMinWidth(200);
        countDays.setMinHeight(40);
        countDays.setFont(Font.font(16));
        bottomHBox.getChildren().add(countDays);
        HBox.setMargin(countDays, new Insets(24, 0, 24, 44));
        Region leftRegion = new Region();
        leftRegion.setMinWidth(332);
        bottomHBox.getChildren().add(leftRegion);
        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.getChildren().add(checkForMeals);
        checkForMeals.setMinHeight(40);
        checkForMeals.setMinWidth(200);
        checkForMeals.setFont(Font.font(16));
        HBox.setMargin(checkForMeals, new Insets(24, 0, 0, 0));
        checkForMeals.setDisable(true);
        Region rightRegion = new Region();
        rightRegion.setMinWidth(405);
        bottomHBox.getChildren().add(rightRegion);
        //bottomHBox.getChildren().add(saveAsPDF);
        HBox.setMargin(saveAsPDF, new Insets(24, 0, 0, 0));
    }

    private static void topLabels() {
        HBox topHBox = new HBox();
        borderPane.setTop(topHBox);
        Label setUpDaysLabel = new Label("Please enter date and time");
        setUpDaysLabel.setFont(Font.font(16));
        topHBox.getChildren().add(setUpDaysLabel);
        HBox.setMargin(setUpDaysLabel, new Insets(12, 0, 12, 44));
        Region leftRegion = new Region();
        leftRegion.setMinWidth(272);
        topHBox.getChildren().add(leftRegion);
        topHBox.setAlignment(Pos.TOP_LEFT);
        Label checkMealsLabel = new Label("Check meals which you have eaten");
        checkMealsLabel.setFont(Font.font(16));
        topHBox.getChildren().add(checkMealsLabel);
        HBox.setMargin(checkMealsLabel, new Insets(12, 0, 0, 0));
        Region rightRegion = new Region();
        rightRegion.setMinWidth(275);
        topHBox.getChildren().add(rightRegion);
        Label teslaInputLabel = new Label("Enter below values to Tesla");
        teslaInputLabel.setFont(Font.font(16));
        topHBox.getChildren().add(teslaInputLabel);
        HBox.setMargin(teslaInputLabel, new Insets(12, 40, 0, 0));
    }


    private void createFullDayView() {

        //View for full day travel
        Label amountLabelFullDay = new Label("Full Day");
        amountLabelFullDay.setFont(Font.font(16));
        rightGridPane.add(amountLabelFullDay, 2, 0);

        amountLabelFullDayMoneyAmount.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabelFullDayMoneyAmount, 1, 1);

        amountLabelFullDayMealAmount.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabelFullDayMealAmount, 2, 1);

        amountLabelFullDayCountry.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabelFullDayCountry, 2, 2);
    }

    private void create12hView() {
        //View for 12hour travel

        Label amountLabel12hTravel = new Label("12h Travel");
        amountLabel12hTravel.setFont(Font.font(16));
        rightGridPane.add(amountLabel12hTravel, 2, 4);

        Label breakfast12 = new Label("Breakfast");
        rightGridPane.add(breakfast12, 0, 5);
        amountLabel12hTravelMoneyAmountBreakfast.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelMoneyAmountBreakfast, 1, 5);
        amountLabel12hTravelMealAmountBreakfast.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel12hTravelMealAmountBreakfast, 2, 5);
        amountLabel12hTravelCountryBreakfast.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelCountryBreakfast, 2, 6);


        Label lunch12 = new Label("Lunch");
        rightGridPane.add(lunch12, 0, 7);
        amountLabel12hTravelMealAmountLunch.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel12hTravelMealAmountLunch, 2, 7);
        amountLabel12hTravelMoneyAmountLunch.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelMoneyAmountLunch, 1, 7);
        amountLabel12hTravelCountryLunch.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelCountryLunch, 2, 8);


        Label dinner12 = new Label("Dinner");
        rightGridPane.add(dinner12, 0, 9);
        amountLabel12hTravelMealAmountDinner.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel12hTravelMealAmountDinner, 2, 9);
        amountLabel12hTravelMoneyAmountDinner.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelMoneyAmountDinner, 1, 9);
        amountLabel12hTravelCountryDinner.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelCountryDinner, 2, 10);


        Label remainder12 = new Label("Remainder");
        rightGridPane.add(remainder12, 0, 11);
        amountLabel12hTravelMealAmountRemainder.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel12hTravelMealAmountRemainder, 2, 11);
        amountLabel12hTravelMoneyAmountRemainder.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelMoneyAmountRemainder, 1, 11);
        amountLabel12hTravelCountryRemainder.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel12hTravelCountryRemainder, 2, 12);
    }

    private void create8to12hView() {
        //View for 8-12hour travel

        Label amountLabel8to12hTravel = new Label("8-12h Travel");
        rightGridPane.add(amountLabel8to12hTravel, 2, 13);
        amountLabel8to12hTravel.setFont(Font.font(16));

        Label breakfast8to12 = new Label("Breakfast");
        rightGridPane.add(breakfast8to12, 0, 14);

        amountLabel8to12hTravelMoneyAmountBreakfast.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelMoneyAmountBreakfast, 1, 14);

        amountLabel8to12hTravelMealAmountBreakfast.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8to12hTravelMealAmountBreakfast, 2, 14);

        amountLabel8to12hTravelCountryBreakfast.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelCountryBreakfast, 2, 15);


        Label lunch8to12 = new Label("Lunch");
        rightGridPane.add(lunch8to12, 0, 16);

        amountLabel8to12hTravelMealAmountLunch.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8to12hTravelMealAmountLunch, 2, 16);

        amountLabel8to12hTravelMoneyAmountLunch.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelMoneyAmountLunch, 1, 16);

        amountLabel8to12hTravelCountryLunch.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelCountryLunch, 2, 17);


        Label dinner8to12 = new Label("Dinner");
        rightGridPane.add(dinner8to12, 0, 18);

        amountLabel8to12hTravelMealAmountDinner.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8to12hTravelMealAmountDinner, 2, 18);

        amountLabel8to12hTravelMoneyAmountDinner.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelMoneyAmountDinner, 1, 18);

        amountLabel8to12hTravelCountryDinner.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelCountryDinner, 2, 19);


        Label remainder8to12 = new Label("Remainder");
        rightGridPane.add(remainder8to12, 0, 20);

        amountLabel8to12hTravelMealAmountRemainder.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8to12hTravelMealAmountRemainder, 2, 20);

        amountLabel8to12hTravelMoneyAmountRemainder.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelMoneyAmountRemainder, 1, 20);

        amountLabel8to12hTravelCountryRemainder.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8to12hTravelCountryRemainder, 2, 21);


    }

    private void create8hView() {
        //View for 8hour travel

        Label amountLabel8hTravel = new Label("<8 Travel");
        amountLabel8hTravel.setFont(Font.font(16));
        rightGridPane.add(amountLabel8hTravel, 2, 22);

        Label breakfast8t = new Label("Breakfast");
        rightGridPane.add(breakfast8t, 0, 23);
        amountLabel8hTravelMoneyAmountBreakfast.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelMoneyAmountBreakfast, 1, 23);
        amountLabel8hTravelMealAmountBreakfast.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8hTravelMealAmountBreakfast, 2, 23);
        amountLabel8hTravelCountryBreakfast.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelCountryBreakfast, 2, 24);


        Label lunch8 = new Label("Lunch");
        rightGridPane.add(lunch8, 0, 25);
        amountLabel8hTravelMealAmountLunch.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8hTravelMealAmountLunch, 2, 25);
        amountLabel8hTravelMoneyAmountLunch.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelMoneyAmountLunch, 1, 25);
        amountLabel8hTravelCountryLunch.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelCountryLunch, 2, 26);


        Label dinner8 = new Label("Dinner");
        rightGridPane.add(dinner8, 0, 27);
        amountLabel8hTravelMealAmountDinner.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8hTravelMealAmountDinner, 2, 27);
        amountLabel8hTravelMoneyAmountDinner.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelMoneyAmountDinner, 1, 27);
        amountLabel8hTravelCountryDinner.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelCountryDinner, 2, 28);


        Label remainder8 = new Label("Remainder");
        rightGridPane.add(remainder8, 0, 29);
        amountLabel8hTravelMealAmountRemainder.getStyleClass().add("meal-label");
        rightGridPane.add(amountLabel8hTravelMealAmountRemainder, 2, 29);
        amountLabel8hTravelMoneyAmountRemainder.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelMoneyAmountRemainder, 1, 29);
        amountLabel8hTravelCountryRemainder.getStyleClass().add("custom-label");
        rightGridPane.add(amountLabel8hTravelCountryRemainder, 2, 30);
    }

    private void teslaVBOX() {
        VBox rightSideVBox = new VBox();
        rightSideVBox.setPrefHeight(920);
        rightSideVBox.setMinWidth(340);
        borderPane.setRight(rightSideVBox);
        BorderPane.setAlignment(rightSideVBox, Pos.CENTER);
        rightSideVBox.setAlignment(Pos.TOP_CENTER);
        borderPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        rightGridPane.setHgap(10);
        rightGridPane.setVgap(5);

        rightSideVBox.getChildren().add(rightGridPane);

    }


    private int checkTravelTime() {
        String format = "yyyy-MM-ddHH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.GERMANY);
        int days = 0;
        int hours;
        try {
            String date1 = String.valueOf(startDatePicker.getValue());
            String date2 = String.valueOf(endDatePicker.getValue());
            dateObj1 = sdf.parse(date1 + "" + String.valueOf(startTimeTextField.getText()));
            Date dateObj2 = sdf.parse(date2 + " " + String.valueOf(endTimeTextField.getText()));

            System.out.println(df.toString(dateObj1));

            long diff = ((dateObj2.getTime() - dateObj1.getTime()));
            if (diff <= 0) {
                daysAndHoursOfTravel.setFont(Font.font(16));
                daysAndHoursOfTravel.setText("Your travel time \ncannot be negative");
                return 0;
            }
            days = (int) (diff / 86_400_000);
            reminder = diff % 86_400_000;
            hours = (int) reminder / 3_600_000;
            int remainderForMinutes = (int) (reminder % 3_600_000) / 60_000;

            daysAndHoursOfTravel.setText("\t" + (days == 1 ? days + " day \n" : days + " days \n")

                    + "\t" + (hours == 1 ? hours + " hour \n" : hours + " hours \n")
                    + "\t" + (remainderForMinutes == 1 ? remainderForMinutes + " minute \n" : remainderForMinutes + " minutes \n"));

        } catch (ParseException e) {
            daysAndHoursOfTravel.setTextFill(Color.RED);
            daysAndHoursOfTravel.setText("Enter valid travel time");
        }
        return days;
    }


    private void centerScrollPane(int daysEntitledToTravel) {
        int row;

        Date nextDay = dateObj1;
        datt = nextDay.getTime();


        for (row = 0; row < daysEntitledToTravel; row++) {
            columnIndex++;
            if (row % 7 == 0) {
                rowIndex += 4;
                columnIndex = 0;
                Region topRegion = new Region();
                topRegion.setMinHeight(75);
                root.add(topRegion, 0, rowIndex);
                rowIndex++;
            }

            String pattern = "EEE, dd/MMM/yyyy";
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(pattern, new Locale("en", "EN"));

            nextDay.setTime(datt);

            String date = simpleDateFormat.format(nextDay);
            // System.out.println(date);

            Label dayLabel = new Label(date);
            dayLabel.setWrapText(true);
            dayLabel.setMinSize(75, 15);
            root.add(dayLabel, columnIndex, rowIndex);
            root.setHgap(25);
            //
            CheckBox breakfast = new CheckBox("Breakfast");
            root.add(breakfast, columnIndex, rowIndex + 1);
            //
            CheckBox lunch = new CheckBox("Lunch");
            root.add(lunch, columnIndex, rowIndex + 2);
            //
            CheckBox dinner = new CheckBox("Dinner");
            root.add(dinner, columnIndex, rowIndex + 3);

            boxy.add(new DaysCheckBox(DAYABOVE12HOURS, breakfast, lunch, dinner));

            datt += 86_400_000;
        }
        if (reminder != 0) {
            remainderDays(row, reminder);

        }
        rowIndex += 4;

        Region bottomRegion = new Region();
        bottomRegion.setMinHeight(1);
        bottomRegion.setMinWidth((sp.getWidth()));
        GridPane.setMargin(bottomRegion, new Insets(40, 0, 0, 0));
        GridPane.setColumnSpan(bottomRegion, 7);
        bottomRegion.setStyle("-fx-background-color: #000000");
        root.add(bottomRegion, 0, rowIndex);

    }

    private void remainderDays(int row, long remainder) {

        Date nextDay = dateObj1;
        //   datt = nextDay.getTime();
        // datt+=86_400_000;
        String pattern = "EEE, dd/MMM/yyyy";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("en", "EN"));

        nextDay.setTime(datt);

        String date = simpleDateFormat.format(nextDay);

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
        Label dayLabel = new Label();
        dayLabel.setWrapText(true);
        dayLabel.setText(date);
        dayLabel.setMinSize(75, 15);
        GridPane.setColumnSpan(dayLabel, 2);
        root.add(dayLabel, columnIndex, rowIndex);
        root.setHgap(25);
        CheckBox breakfast = new CheckBox();
        GridPane.setColumnSpan(breakfast, 2);
        CheckBox lunch = new CheckBox();
        GridPane.setColumnSpan(lunch, 2);
        CheckBox dinner = new CheckBox();
        GridPane.setColumnSpan(dinner, 2);
        //remainder for less than 8hours
        if (remainder < 28_800_001 && remainder > 0) {
            breakfast.setText("Breakfast for day <8h");
            lunch.setText("Lunch for day <8h");
            dinner.setText("Dinner for day <8h");
            boxy.add(new DaysCheckBox(DAYBELOW8HOURS, breakfast, lunch, dinner));
        }
        //remainder for more than 8hours but less than 12h
        else if (remainder > 28_800_001 && remainder < 43_200_000) {
            breakfast.setText("Breakfast for day 8-12h");
            lunch.setText("Lunch for day 8-12h");
            dinner.setText("Dinner for day 8-12h");
            boxy.add(new DaysCheckBox(DAYBEBELOW12HOURS, breakfast, lunch, dinner));
        }
        //remainder if more than 12hours
        else if (remainder >= 43_200_000 && remainder < 86_400_000) {
            breakfast.setText("Breakfast 12h travel");
            lunch.setText("Lunch 12h travel");
            dinner.setText("Dinner 12h travel");
            boxy.add(new DaysCheckBox(DAYABOVE12HOURS, breakfast, lunch, dinner));
        }
        root.add(breakfast, columnIndex, rowIndex + 1);
        root.add(lunch, columnIndex, rowIndex + 2);
        root.add(dinner, columnIndex, rowIndex + 3);
    }

    private void checkIfMealsSelected(ArrayList<DaysCheckBox> arrayList) {
        int bNum = 0, lNum = 0, dNum = 0, remainder = 0, fullDay = 0;
        String countryTesla = "Select country in Tesla";
        String amountTesla = "Amounts calculated in Tesla";

        for (DaysCheckBox anArrayList : arrayList) {

            if (anArrayList.getMealClass() == DAYABOVE12HOURS && !anArrayList.getBreakfastCheck().isSelected() &&
                    !anArrayList.getLunchCheck().isSelected() && !anArrayList.getDinnerCheck().isSelected()) {
                amountLabelFullDayMoneyAmount.setText(amountTesla);
                amountLabelFullDayCountry.setText(countryTesla);
                fullDay++;
                if (fullDay == 1) {
                    createFullDayView();
                }

            } else if (anArrayList.getMealClass() == DAYABOVE12HOURS) {

                if (!anArrayList.getBreakfastCheck().isSelected()) {
                    amountLabel12hTravelMoneyAmountBreakfast.setText(amountTesla);
                    amountLabel12hTravelCountryBreakfast.setText(countryTesla);
                    bNum++;
                }
                if (!anArrayList.getLunchCheck().isSelected()) {
                    lNum++;
                    amountLabel12hTravelMoneyAmountLunch.setText(amountTesla);
                    amountLabel12hTravelCountryLunch.setText(countryTesla);
                }
                if (!anArrayList.getDinnerCheck().isSelected()) {
                    dNum++;
                    amountLabel12hTravelMoneyAmountDinner.setText(amountTesla);
                    amountLabel12hTravelCountryDinner.setText(countryTesla);
                }
                amountLabel12hTravelMoneyAmountRemainder.setText(amountTesla);
                amountLabel12hTravelCountryRemainder.setText(countryTesla);
                remainder++;
                if (remainder == 1) {
                    create12hView();
                }

            } else if (anArrayList.getMealClass() == DAYBELOW8HOURS) {
                create8hView();
                if (!anArrayList.getBreakfastCheck().isSelected()) {
                    amountLabel8hTravelMealAmountBreakfast.setText("1");
                    amountLabel8hTravelMoneyAmountBreakfast.setText(amountTesla);
                    amountLabel8hTravelCountryBreakfast.setText(countryTesla);
                }
                if (!anArrayList.getLunchCheck().isSelected()) {
                    amountLabel8hTravelMealAmountLunch.setText("1");
                    amountLabel8hTravelMoneyAmountLunch.setText(amountTesla);
                    amountLabel8hTravelCountryLunch.setText(countryTesla);
                }
                if (!anArrayList.getDinnerCheck().isSelected()) {
                    amountLabel8hTravelMealAmountDinner.setText("1");
                    amountLabel8hTravelMoneyAmountDinner.setText(amountTesla);
                    amountLabel8hTravelCountryDinner.setText(countryTesla);
                }
                amountLabel8hTravelMealAmountRemainder.setText("1");
                amountLabel8hTravelMoneyAmountRemainder.setText(amountTesla);
                amountLabel8hTravelCountryRemainder.setText(countryTesla);

            } else if (anArrayList.getMealClass() == DAYBEBELOW12HOURS) {
                create8to12hView();

                if (!anArrayList.getBreakfastCheck().isSelected()) {
                    amountLabel8to12hTravelMealAmountBreakfast.setText("1");
                    amountLabel8to12hTravelMoneyAmountBreakfast.setText(amountTesla);
                    amountLabel8to12hTravelCountryBreakfast.setText(countryTesla);
                }
                if (!anArrayList.getLunchCheck().isSelected()) {
                    amountLabel8to12hTravelMealAmountLunch.setText("1");
                    amountLabel8to12hTravelMoneyAmountLunch.setText(amountTesla);
                    amountLabel8to12hTravelCountryLunch.setText(countryTesla);
                }
                if (!anArrayList.getDinnerCheck().isSelected()) {
                    amountLabel8to12hTravelMealAmountDinner.setText("1");
                    amountLabel8to12hTravelMoneyAmountDinner.setText(amountTesla);
                    amountLabel8to12hTravelCountryDinner.setText(countryTesla);
                }
                amountLabel8to12hTravelMealAmountRemainder.setText("1");
                amountLabel8to12hTravelMoneyAmountRemainder.setText(amountTesla);
                amountLabel8to12hTravelCountryRemainder.setText(countryTesla);
            }
        }
        if (fullDay != 0)
            amountLabelFullDayMealAmount.setText(String.valueOf(fullDay));

        if (bNum != 0)
            amountLabel12hTravelMealAmountBreakfast.setText(String.valueOf(bNum));

        if (lNum != 0)
            amountLabel12hTravelMealAmountLunch.setText(String.valueOf(lNum));

        if (dNum != 0)
            amountLabel12hTravelMealAmountDinner.setText(String.valueOf(dNum));

        if (remainder != 0)
            amountLabel12hTravelMealAmountRemainder.setText(String.valueOf(remainder));


    }

    private void clearLabels() {
        amountLabel8hTravelMealAmountBreakfast.setText("");
        amountLabel8hTravelMealAmountLunch.setText("");
        amountLabel8hTravelMealAmountDinner.setText("");
        amountLabel8hTravelMealAmountRemainder.setText("");
        amountLabel8hTravelMoneyAmountBreakfast.setText("");
        amountLabel8hTravelMoneyAmountLunch.setText("");
        amountLabel8hTravelMoneyAmountDinner.setText("");
        amountLabel8hTravelMoneyAmountRemainder.setText("");
        amountLabel8hTravelCountryBreakfast.setText("");
        amountLabel8hTravelCountryLunch.setText("");
        amountLabel8hTravelCountryDinner.setText("");
        amountLabel8hTravelCountryRemainder.setText("");
        amountLabel8to12hTravelMoneyAmountBreakfast.setText("");
        amountLabel8to12hTravelMoneyAmountLunch.setText("");
        amountLabel8to12hTravelMoneyAmountDinner.setText("");
        amountLabel8to12hTravelMoneyAmountRemainder.setText("");
        amountLabel8to12hTravelMealAmountBreakfast.setText("");
        amountLabel8to12hTravelMealAmountLunch.setText("");
        amountLabel8to12hTravelMealAmountDinner.setText("");
        amountLabel8to12hTravelMealAmountRemainder.setText("");
        amountLabel8to12hTravelCountryBreakfast.setText("");
        amountLabel8to12hTravelCountryLunch.setText("");
        amountLabel8to12hTravelCountryDinner.setText("");
        amountLabel8to12hTravelCountryRemainder.setText("");
        amountLabel12hTravelMoneyAmountBreakfast.setText("");
        amountLabel12hTravelMoneyAmountLunch.setText("");
        amountLabel12hTravelMoneyAmountDinner.setText("");
        amountLabel12hTravelMoneyAmountRemainder.setText("");
        amountLabel12hTravelMealAmountBreakfast.setText("");
        amountLabel12hTravelMealAmountLunch.setText("");
        amountLabel12hTravelMealAmountDinner.setText("");
        amountLabel12hTravelMealAmountRemainder.setText("");
        amountLabel12hTravelCountryBreakfast.setText("");
        amountLabel12hTravelCountryLunch.setText("");
        amountLabel12hTravelCountryDinner.setText("");
        amountLabel12hTravelCountryRemainder.setText("");
        amountLabelFullDayMoneyAmount.setText("");
        amountLabelFullDayMealAmount.setText("");
        amountLabelFullDayCountry.setText("");
    }


}