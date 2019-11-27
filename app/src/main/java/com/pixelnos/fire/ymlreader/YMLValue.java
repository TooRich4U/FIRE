package com.pixelnos.fire.ymlreader;

import java.util.ArrayList;
import java.util.Hashtable;

public class YMLValue {
    private Hashtable<String, YMLValue> values = new Hashtable<>();
    private String value = "";
    private boolean isArray = false;
    private ArrayList<String> keyOrder = new ArrayList<>();
    private int arrayKey = 0;
    private Hashtable<String, String> comments = new Hashtable<>();

    public YMLValue() {
    }

    public YMLValue(String value) {
        String tempValue = value.trim();
        if (tempValue.charAt(0) == '[' && tempValue.charAt(tempValue.length() - 1) == ']') {
            isArray = true;
            tempValue = tempValue.substring(1, tempValue.length() - 2);
            String[] elements = tempValue.split(",");
            for (String element : elements) {
                try {
                    add(new YMLValue(element));
                } catch (Exception e) {
                }
            }
        } else {
            this.value = value.trim();
        }
    }

    public YMLValue(ArrayList<String> lines) {
        ArrayList<String> childLines = new ArrayList<>();
        String currentKey = "";
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            if (getShift(line) == 0 && !line.isEmpty()) {
                currentKey = addPreviousGatheredValue(childLines, currentKey);
                if (line.contains("---")) {
                    comments.put(keyOrder.get(keyOrder.size() - 1), line);
                } else if (line.indexOf('-') >= 0) {
                    isArray = true;
                    line = line.substring(1).trim();
                    currentKey = Integer.toString(arrayKey);
                    arrayKey += 1;
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

    public YMLValue addEntry(String key, YMLValue newValue) {
        values.put(key, newValue);
        keyOrder.add(key);
        return this;
    }

    public YMLValue add(YMLValue newValue) throws Exception {
        if (isArray || (value.isEmpty() && values.isEmpty())) {
            values.put(Integer.toString(arrayKey), newValue);
            arrayKey += 1;
            isArray = true;
        } else {
            throw new Exception("Cannot create array. HashMap already filled with values");
        }
        return this;
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
        }
        return "";
    }

    private void addEntryFromSplit(String[] data) {
        String newValue = data[1];
        if (data.length > 2) {
            for (int valueIndex = 2; valueIndex < data.length; valueIndex++) {
                newValue += ":" + data[valueIndex];
            }
        }
        keyOrder.add(data[0]);
        values.put(data[0], new YMLValue(newValue));
    }

    public String removeShift(String string) {
        return string.substring(2);
    }

    private int getShift(String string) {
        int shift = 0;
        while (!string.isEmpty() && string.charAt(shift) == ' ') {
            shift += 1;
        }
        return shift;
    }

    public YMLValue get(String key) {
        return values.get(key);
    }

    public boolean isValue() {
        return !value.isEmpty();
    }

    public Double asDouble() {
        return Double.parseDouble(value);
    }

    public ArrayList<YMLValue> asArrayList() {
        ArrayList<YMLValue> array = new ArrayList<>();

        for (int index = 0; index < values.size(); index++) {
            array.add(values.get(Integer.toString(index)));
        }

        return array;
    }

    public String asString() {
        return value;
    }

    public String toString(String shift) {
        if (values.isEmpty()) {
            if (value.isEmpty()) {
                return "";
            }
            return " " + value + "\n";
        }
        String yml = "\n";
        if (shift.compareTo("") == 0) {
            yml = "";
        }
        if (isArray) {
            ArrayList<YMLValue> arrayValues = this.asArrayList();
            int indexOfEntry = 0;
            for (YMLValue entry : arrayValues) {
                yml += shift + "-" + entry.toString(shift + "  ");
                if (comments.containsKey(Integer.toString(indexOfEntry))) {
                    yml += shift + comments.get(Integer.toString(indexOfEntry)) + "\n";
                }
                indexOfEntry += 1;
            }
        } else {
            for (String key : keyOrder) {
                yml += shift + key + ":" + values.get(key).toString(shift + "  ");
                if (comments.containsKey(key)) {
                    yml += shift + comments.get(key) + "\n";
                }
            }
        }
        return yml;
    }
}
