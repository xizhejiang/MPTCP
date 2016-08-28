package com.spring.elevator.utils.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by AlexJIANG on 8/14/16.
 */

public interface IRunScriptService extends Remote {

    public void runScript(String script) throws RemoteException,IOException;
}
