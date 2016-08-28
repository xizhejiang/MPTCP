package com.spring.elevator.utils.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Created by AlexJIANG on 8/14/16.
 */
public class RMIClient {

    public void runScript(String command,String url){
        try{
            System.setProperty("java.rmi.server.hostname",url);
            System.getProperty("java.security.policy","/WEB-INF/server.policy");
            System.setSecurityManager(new RMISecurityManager());
            System.setProperty("java.rmi.server.hostname",url);
            IRunScriptService iss = (IRunScriptService) Naming.lookup("rmi://"+url+":1099/IRunScriptService");
            iss.runScript(command);
            //iss.terminateScript();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
