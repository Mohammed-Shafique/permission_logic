package com.shafique;

import java.util.HashMap;
import java.util.Map;

public class Preference {
    private Map<String,String> prefTypeMap =new HashMap<String, String>();

    public Map<String, String> getPrefTypeMap() {
        return prefTypeMap;
    }

    public void setPrefTypeMap(Map<String, String> prefTypeMap) {
        this.prefTypeMap = prefTypeMap;
    }
}
