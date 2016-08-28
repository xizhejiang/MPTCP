package com.spring.elevator.service;

import com.spring.elevator.service.IService.IRMI;
import com.spring.elevator.utils.rmi.IRunScriptService;
import com.spring.elevator.utils.rmi.RMIClient;
import com.spring.elevator.utils.rmi.RunScriptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by AlexJIANG on 8/14/16.
 */

@Service("IRMIIMPL")
@Transactional
public class IRMIIMPL implements IRMI {
    @Autowired
    Environment env;


    @Override
    public void runScript(int times, String email,String url) throws RemoteException, IOException {
        RMIClient rc = new RMIClient();



    }


    @Override
    public void terminateScript(int times, String email,String url) throws RemoteException, IOException {
        IRunScriptService iss = new RunScriptServiceImpl();
        String script = "";
        iss.runScript(script);
    }
}
