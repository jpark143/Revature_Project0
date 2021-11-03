package com.company;

import java.util.HashMap;

public class EmployeeInfo {

    HashMap<String, String> adminInfo = new HashMap<String, String>();
    EmployeeInfo(){

        adminInfo.put("Emp1", "admin123");
    }

    public HashMap getAdminInfo(){
        return adminInfo;
    }
}
