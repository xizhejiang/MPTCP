package com.spring.elevator.utils.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by AlexJIANG on 8/14/16.
 */
public class RunScriptServiceImpl extends UnicastRemoteObject implements IRunScriptService {
    public RunScriptServiceImpl() throws RemoteException{
        super();
    }

    @Override
    public void runScript(String script) throws RemoteException,IOException {
        Process p = Runtime.getRuntime().exec(script);
    }


}
