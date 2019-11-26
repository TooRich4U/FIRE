package com.pixelnos.fire.ymlreader;

import java.util.ArrayList;
import java.util.Arrays;

public class YMLReader {
    static public YMLValue read(String yml){
        String[] lines = yml.split("\n");
        return new YMLValue(new ArrayList<>(Arrays.asList(lines)));
    }


}

