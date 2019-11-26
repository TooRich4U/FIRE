package com.pixelnos.fire.ymlreader;

import java.util.ArrayList;
import java.util.Hashtable;

public class YMLValue {
    private Hashtable<String, YMLValue> values = new Hashtable<>();
    private String value = "";
    private boolean isArray = false;
    private ArrayList<String> keyOrder = new ArrayList<>();

    public YMLValue() {
    }

    public YMLValue(String value) {
        this.value = value.trim();
    }

    public YMLValue(ArrayList<String> lines) {
        ArrayList<String> childLines = new ArrayList<>();
        String currentKey = "";
        int currentIndex = 0;
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            if (getShift(line) == 0 && !line.isEmpty()) {
                currentKey = addPreviousGatheredValue(childLines, currentKey);
                if (line.indexOf('-') >= 0) {
                    isArray = true;
                    line = line.substring(1).trim();
                    currentKey = Integer.toString(currentIndex);
                    currentIndex += 1;
                    childLines.add(line);
                } else if (line.indexOf(':') >= 0) {
                    String[] data = line.trim().split(":");
                    if (data.length > 1) {
                        addEntryFromSplit(data);
                    } else {
                        currentKey = data[0];
                    }
                }
            } else if (!line.isEmpty()) {
                line = removeShift(line);
                childLines.add(line);
            }
        }
        if (!currentKey.isEmpty()) {
            keyOrder.add(currentKey);
            if (childLines.size() > 1) {
                values.put(currentKey, new YMLValue(childLines));
            } else {
                values.put(currentKey, new YMLValue(childLines.get(0)));
            }
        }
    }

    public void addEntry(String key, YMLValue newValue){
        values.put(key, newValue);
    }

    private String addPreviousGatheredValue(ArrayList<String> childLines, String currentKey) {
        if (!currentKey.isEmpty()) {
            keyOrder.add(currentKey);
            if (childLines.size() > 1) {
                values.put(currentKey, new YMLValue(childLines));
            } else {
                values.put(currentKey, new YMLValue(childLines.get(0)));
            }
            childLines.clear();
            currentKey = "";
        }
        return currentKey;
    }

    private void addEntryFromSplit(String[] data) {
        String newValue = data[1];
        if (data.length > 2) {
            for(int valueIndex = 2; valueIndex < data.length; valueIndex++){
                newValue += ":" + data[valueIndex];
            }
        }
        keyOrder.add(data[0]);
        values.put(data[0], new YMLValue(newValue));
    }

    public String removeShift(String string) {
        return string.substring(2);
    }

    int getShift(String string) {
        int shift = 0;
        while (!string.isEmpty() && string.charAt(shift) == ' ') {
            shift += 1;
        }
        return shift;
    }

    public YMLValue get(String key) {
        return values.get(key);
    }

    public boolean isValue(){
        return !value.isEmpty();
    }

    public Double asDouble() {
        return Double.parseDouble(value);
    }

    public ArrayList<YMLValue> asArrayList() {
        ArrayList<YMLValue> array = new ArrayList<>();

        for(int index = 0; index < values.size(); index++){
            array.add(values.get(Integer.toString(index)));
        }

        return array;
    }

    public String asString(){return value;}

    public String toString(String shift){
        if(values.isEmpty()){
            if(value.isEmpty()){
                return "";
            }
            return " " + value + "\n";
        }
        String yml = "\n";
        if(shift.compareTo("") == 0){
            yml = "";
        }
        if(isArray){
            ArrayList<YMLValue> arrayValues = this.asArrayList();
            for(YMLValue entry : arrayValues){
                yml += shift + "-" + entry.toString(shift + "  ") ;
            }
        }
        else {
            for (String key : keyOrder) {
                yml += shift + key + ":" + values.get(key).toString(shift + "  ");
            }
        }
        return yml;
    }
}
