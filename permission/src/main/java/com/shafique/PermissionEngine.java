package com.shafique;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PermissionEngine {

    private static Map<String, String> permissionMap = new HashMap<String, String>();

    public PermissionEngine(){
        permissionMap.put("DO_NOT_EMAIL", "OK_TO_EMAIL");
        permissionMap.put("DO_NOT_CALL", "OK_TO_CALL");
        permissionMap.put("DO_NOT_MAIL", "OK_TO_MAIL");
        permissionMap.put("DO_NOT_CONTACT", "OK_TO_CONTACT");
    }

    public Permission computePermission(PermissionRequest request){
        Permission permission = null;
        if(request != null && request.getSuppression() != null){
           Set<String> suppTypes = request.getSuppression().getSuppMap().keySet();
           permission = new Permission();
           permission.setConsumerId(request.getConsumerId());
            for(String suppType : suppTypes){

                //Getting the list of channels being impacted
                List<String> channels = XLSUtil.readChannels(suppType);

                //get all CP being impacted & for individual also
                List<Object> contactPoints = XLSUtil.getAllCPImpacted(suppType, getEntityType(suppType, request));

                permission = XLSUtil.evaluatePermission(suppType, permission);

                //TODO store the details in permission instance and overlay with preferences.

            }
        }
        return permission;
    }

    private boolean computePermission(String supType, PermissionRequest request){
            return evaluatePermissionFromSuppressionType(supType, request) ;
    }

    private boolean evaluatePermissionFromSuppressionType(String supType, PermissionRequest request ){
        String suppTypeVal = request.getSuppression().getSuppMap().get(supType);
        if(suppTypeVal != null && !suppTypeVal.equals("")){
            return "Y".equals(suppTypeVal) ? false : true;
        }
        return false;
    }

    private String getEntityType(String suppType,PermissionRequest request){
        String type = null;
        if("DO_NOT_CALL".equals(suppType)){
            type =  request.getPhone();
        }else if("DO_NOT_EMAIL".equals(suppType)){
            type =  request.getEmail();
        }else if("DO_NOT_MAIL".equals(suppType)){
            type =  request.getAddress();
        }

        return type;
    }
}
