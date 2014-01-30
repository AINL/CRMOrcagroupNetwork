package nl.amnesty.crm.network.orca.webservice;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public abstract interface AmnestyExternalService extends Service
{
  public abstract AmnestyExternalServiceSoap getAmnestyExternalServiceSoap()
    throws ServiceException;
}