package nl.amnesty.crm.network.orca.webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface AmnestyExternalServiceSoap extends Remote
{
  public abstract ArrayOfSubscriber getSubscribers(String paramString)
    throws RemoteException;

  public abstract int updateSubscriber(String paramString1, String paramString2, String paramString3)
    throws RemoteException;
}