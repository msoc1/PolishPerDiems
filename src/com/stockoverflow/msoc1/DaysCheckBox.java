package com.stockoverflow.msoc1;

import javafx.scene.control.CheckBox;

public class DaysCheckBox {

    private CheckBox breakfastCheck;
    private CheckBox lunchCheck;
    private CheckBox dinnerCheck;
    private int mealClass;


    public DaysCheckBox(int mealClass, CheckBox breakfastCheck, CheckBox lunchCheck, CheckBox dinnerCheck) {
        this.mealClass = mealClass;
        this.breakfastCheck = breakfastCheck;
        this.lunchCheck = lunchCheck;
        this.dinnerCheck = dinnerCheck;
    }


    public CheckBox getBreakfastCheck() {
        return breakfastCheck;
    }

    public CheckBox getLunchCheck() {
        return lunchCheck;
    }

    public CheckBox getDinnerCheck() {
        return dinnerCheck;
    }
    public int getMealClass() {
        return mealClass;
    }


}
