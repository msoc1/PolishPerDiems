package sample;

import javafx.scene.control.CheckBox;

public class MojeBoxy {

    private CheckBox breakfastCheck;
    private CheckBox lunchCheck;
    private CheckBox dinnerCheck;
    private int column;
    private int row;

    public MojeBoxy(CheckBox breakfastCheck, CheckBox lunchCheck, CheckBox dinnerCheck) {
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

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
