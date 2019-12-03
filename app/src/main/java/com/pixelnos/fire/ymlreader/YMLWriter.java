package com.pixelnos.fire.ymlreader;

public class YMLWriter {
    static public String write(YMLValue value) {
        String yml = value.toString("");
        if (yml.isEmpty()) {
            return yml;
        }
        return yml.substring(0, yml.length() - 1);
    }

}
