package sample;

import java.util.HashMap;

public class Calculations {
    public static double calculate(String type, String toCalculate, HashMap<String, Double> data){
        double result = 0;
        switch (type){
            case "S":
                result = simple(toCalculate, data);
                break;
            case "C":
                result = compound(toCalculate, data);
                break;
        }
        return result;
    }
    private static double compound(String toCalculate, HashMap<String, Double> data){
        double result = 0;
        switch (toCalculate){
            case "P":
                result = data.get("I") / Math.pow((1 + (data.get("R") / 100)), 2);
                break;
            case "R":
                result = (Math.pow(data.get("P") / data.get("I"), 1 / data.get("T")) * 100) - 100;
                break;
            case "T":
                result = (Math.log(data.get("I") / data.get("P"))) / Math.log(1 + (data.get("R") * 0.01));
                break;
            case "I":
                result = data.get("P") * Math.pow((1 + (data.get("R") / 100)), data.get("T")) - data.get("P");
                break;
        }
        return Math.round(result);
    }

    private static double simple(String toCalculate, HashMap<String, Double> data){
        double result = 0;
        switch (toCalculate){
            case "P":
                result = (data.get("I") * 100) / (data.get("R") * data.get("T"));
                break;
            case "R":
                result = (data.get("I") * 100) / (data.get("P") * data.get("T"));
                break;
            case "T":
                result = (data.get("I") * 100) / (data.get("P") * data.get("R"));
                break;
            case "I":
                result = (data.get("P")*data.get("R")*data.get("T"))/100;
                break;
        }
        return Math.round(result);
    }
}
