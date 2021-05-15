package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.HashMap;
import java.util.function.UnaryOperator;

public class Controller {
    private String type, toCalculate;
    double[] data;
    HashMap<String, Double> dataSet = new HashMap<String, Double>();

    @FXML
    private RadioButton rb_simple;
    @FXML
    private RadioButton rb_compound;
    @FXML
    public RadioButton rb_principle;
    @FXML
    public RadioButton rb_rate;
    @FXML
    public RadioButton rb_time;
    @FXML
    public RadioButton rb_interest;
    @FXML
    public TextField in_principle;
    @FXML
    private TextField in_rate;
    @FXML
    private TextField in_time;
    @FXML
    private TextField in_interest;

    public void typeToggle(){
        if(rb_simple.isSelected()){
            type="S";
        }
        if(rb_compound.isSelected()){
            type="C";
        }
        rb_principle.setDisable(false);
        rb_rate.setDisable(false);
        rb_time.setDisable(false);
        rb_interest.setDisable(false);
        Clear();
    }

    public void toCalculateToggle(){
        if(rb_principle.isSelected()){
            in_principle.setEditable(false);
            in_rate.setEditable(true);
            in_time.setEditable(true);
            in_interest.setEditable(true);
            Clear();
            toCalculate = "P";
        }
        if(rb_rate.isSelected()){
            in_principle.setEditable(true);
            in_rate.setEditable(false);
            in_time.setEditable(true);
            in_interest.setEditable(true);
            Clear();
            toCalculate = "R";
        }
        if(rb_time.isSelected()){
            in_principle.setEditable(true);
            in_rate.setEditable(true);
            in_time.setEditable(false);
            in_interest.setEditable(true);
            Clear();
            toCalculate = "T";
        }
        if(rb_interest.isSelected()){
            in_principle.setEditable(true);
            in_rate.setEditable(true);
            in_time.setEditable(true);
            in_interest.setEditable(false);
            Clear();
            toCalculate = "I";
        }
    }
    public void onCalculate(){
        System.out.println("Calculate");
        TextField[] textFields = {in_principle, in_rate, in_time, in_interest};
        int filledField = 0;
        for(TextField tf: textFields){
            if(!tf.getText().isEmpty()){
                filledField++;
            }
        }
        if(filledField==3) {
            switch (toCalculate) {
                case "P":
                    dataSet.put("R", Double.parseDouble(in_rate.getText()));
                    dataSet.put("T", Double.parseDouble(in_time.getText()));
                    dataSet.put("I", Double.parseDouble(in_interest.getText()));
                    in_principle.setText(String.valueOf(Calculations.calculate(type, toCalculate, dataSet)));
                    break;
                case "R":
                    dataSet.put("P", Double.parseDouble(in_principle.getText()));
                    dataSet.put("T", Double.parseDouble(in_time.getText()));
                    dataSet.put("I", Double.parseDouble(in_interest.getText()));
                    in_rate.setText(String.valueOf(Calculations.calculate(type, toCalculate, dataSet)));
                    break;
                case "T":
                    dataSet.put("P", Double.parseDouble(in_principle.getText()));
                    dataSet.put("R", Double.parseDouble(in_rate.getText()));
                    dataSet.put("I", Double.parseDouble(in_interest.getText()));
                    in_time.setText(String.valueOf(Calculations.calculate(type, toCalculate, dataSet)));
                    break;
                case "I":
                    dataSet.put("P", Double.parseDouble(in_principle.getText()));
                    dataSet.put("R", Double.parseDouble(in_rate.getText()));
                    dataSet.put("T", Double.parseDouble(in_time.getText()));
                    in_interest.setText(String.valueOf(Calculations.calculate(type, toCalculate, dataSet)));
                    break;
            }
            System.out.println("Type: "+type+"\n"+"To calculate: "+toCalculate+"\n"+dataSet);
        }
    }

    public void Clear(){
        in_principle.clear();
        in_rate.clear();
        in_time.clear();
        in_interest.clear();
    }
    public void initialize(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$")) {
                return change;
            }
            return null;
        };
        in_principle.setTextFormatter(new TextFormatter<Double>(filter));
        in_rate.setTextFormatter(new TextFormatter<Double>(filter));
        in_time.setTextFormatter(new TextFormatter<Double>(filter));
        in_interest.setTextFormatter(new TextFormatter<Double>(filter));
    }
}
