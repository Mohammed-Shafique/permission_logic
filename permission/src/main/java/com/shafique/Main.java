package com.shafique;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PermissionRequest request = new PermissionRequest();
        request.setConsumerId(123);
        request.setLanguage("ENG");
        request.setEmail("abc@xyz.com");
        request.setPhone("0123456789");

        Preference pref = new Preference();
        Map<String, String> prefMap = new HashMap<String, String>();
        prefMap.put("EMAIL","Y");
        prefMap.put("CALL", "N");
        prefMap.put("MAIL", "Y");
        pref.setPrefTypeMap(prefMap);

        Suppression supp = new Suppression();
        Map<String, String> suppMap = new HashMap<String, String>();
        suppMap.put("DO_NOT_EMAIL", "N");
        suppMap.put("DO_NOT_CALL","Y");
        suppMap.put("DO_NOT_MAIL", "N");
        suppMap.put("DO_NOT_CONTACT", "N");
        supp.setSuppMap(suppMap);
        request.setPref(pref);
        request.setSuppression(supp);

        PermissionEngine permissionEngine = new PermissionEngine();
        Permission permission = permissionEngine.computePermission(request);
        System.out.println(permission);
    }

}
