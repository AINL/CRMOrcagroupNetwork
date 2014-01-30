package nl.amnesty.crm.network.orca.webservice;

import com.sun.xml.rpc.client.BasicService;
import com.sun.xml.rpc.client.HandlerChainImpl;
import com.sun.xml.rpc.client.ServiceExceptionImpl;
import com.sun.xml.rpc.util.exception.LocalizableExceptionAdapter;
import java.rmi.Remote;
import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerRegistry;

public class AmnestyExternalService_Impl extends BasicService
  implements AmnestyExternalService
{
  private static final QName serviceName = new QName("http://www.orcagroup.com/", "AmnestyExternalService");
  private static final QName ns1_AmnestyExternalServiceSoap_QNAME = new QName("http://www.orcagroup.com/", "AmnestyExternalServiceSoap");
  private static final Class amnestyExternalServiceSoap_PortClass = AmnestyExternalServiceSoap.class;

  public AmnestyExternalService_Impl() {
    super(serviceName, new QName[] { ns1_AmnestyExternalServiceSoap_QNAME }, new AmnestyExternalService_SerializerRegistry().getRegistry());
  }

  public Remote getPort(QName portName, Class serviceDefInterface)
    throws ServiceException
  {
    try
    {
      if ((portName.equals(ns1_AmnestyExternalServiceSoap_QNAME)) && (serviceDefInterface.equals(amnestyExternalServiceSoap_PortClass)))
      {
        return getAmnestyExternalServiceSoap();
      }
    } catch (Exception e) {
      throw new ServiceExceptionImpl(new LocalizableExceptionAdapter(e));
    }
    return super.getPort(portName, serviceDefInterface);
  }

  public Remote getPort(Class serviceDefInterface) throws ServiceException {
    try {
      if (serviceDefInterface.equals(amnestyExternalServiceSoap_PortClass))
        return getAmnestyExternalServiceSoap();
    }
    catch (Exception e) {
      throw new ServiceExceptionImpl(new LocalizableExceptionAdapter(e));
    }
    return super.getPort(serviceDefInterface);
  }

  public AmnestyExternalServiceSoap getAmnestyExternalServiceSoap() {
    String[] roles = new String[0];
    HandlerChainImpl handlerChain = new HandlerChainImpl(getHandlerRegistry().getHandlerChain(ns1_AmnestyExternalServiceSoap_QNAME));
    handlerChain.setRoles(roles);
    AmnestyExternalServiceSoap_Stub stub = new AmnestyExternalServiceSoap_Stub(handlerChain);
    try {
      stub._initialize(this.internalTypeRegistry);
    } catch (JAXRPCException e) {
      throw e;
    } catch (Exception e) {
      throw new JAXRPCException(e.getMessage(), e);
    }
    return stub;
  }
}