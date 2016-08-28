package com.spring.elevator.service.IService;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by AlexJIANG on 8/14/16.
 */

public interface IRMI{

    public void runScript(int times, String email,String url) throws RemoteException,IOException;
    public void terminateScript(int times, String email,String url) throws RemoteException,IOException;
}
