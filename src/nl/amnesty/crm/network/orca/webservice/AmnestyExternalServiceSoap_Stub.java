package nl.amnesty.crm.network.orca.webservice;

import com.sun.xml.rpc.client.SenderException;
import com.sun.xml.rpc.client.StreamingSenderState;
import com.sun.xml.rpc.client.StubBase;
import com.sun.xml.rpc.encoding.CombinedSerializer;
import com.sun.xml.rpc.encoding.InternalTypeMappingRegistry;
import com.sun.xml.rpc.encoding.SOAPDeserializationContext;
import com.sun.xml.rpc.encoding.SOAPDeserializationState;
import com.sun.xml.rpc.soap.message.InternalSOAPMessage;
import com.sun.xml.rpc.soap.message.SOAPBlockInfo;
import com.sun.xml.rpc.soap.message.SOAPMessageContext;
import com.sun.xml.rpc.streaming.XMLReader;
import java.rmi.RemoteException;
import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.handler.HandlerChain;

public class AmnestyExternalServiceSoap_Stub extends StubBase
  implements AmnestyExternalServiceSoap
{
  private static final QName _portName = new QName("http://www.orcagroup.com/", "AmnestyExternalServiceSoap");
  private static final int UpdateSubscriber_OPCODE = 0;
  private static final int GetSubscribers_OPCODE = 1;
  private static final QName ns1_UpdateSubscriber_UpdateSubscriber_QNAME = new QName("http://www.orcagroup.com/", "UpdateSubscriber");
  private static final QName ns1_UpdateSubscriber_TYPE_QNAME = new QName("http://www.orcagroup.com/", "UpdateSubscriber");
  private CombinedSerializer ns1_myUpdateSubscriber_LiteralSerializer;
  private static final QName ns1_UpdateSubscriber_UpdateSubscriberResponse_QNAME = new QName("http://www.orcagroup.com/", "UpdateSubscriberResponse");
  private static final QName ns1_UpdateSubscriberResponse_TYPE_QNAME = new QName("http://www.orcagroup.com/", "UpdateSubscriberResponse");
  private CombinedSerializer ns1_myUpdateSubscriberResponse_LiteralSerializer;
  private static final QName ns1_GetSubscribers_GetSubscribers_QNAME = new QName("http://www.orcagroup.com/", "GetSubscribers");
  private static final QName ns1_GetSubscribers_TYPE_QNAME = new QName("http://www.orcagroup.com/", "GetSubscribers");
  private CombinedSerializer ns1_myGetSubscribers_LiteralSerializer;
  private static final QName ns1_GetSubscribers_GetSubscribersResponse_QNAME = new QName("http://www.orcagroup.com/", "GetSubscribersResponse");
  private static final QName ns1_GetSubscribersResponse_TYPE_QNAME = new QName("http://www.orcagroup.com/", "GetSubscribersResponse");
  private CombinedSerializer ns1_myGetSubscribersResponse_LiteralSerializer;
  private static final String[] myNamespace_declarations = { "ns0", "http://www.orcagroup.com/" };

  private static final QName[] understoodHeaderNames = new QName[0];

  public AmnestyExternalServiceSoap_Stub(HandlerChain handlerChain)
  {
    super(handlerChain);
    _setProperty("javax.xml.rpc.service.endpoint.address", "http://apps20.orcagroup.com/testsms/dmz/amnestyexternalservice.asmx");
  }

  public int updateSubscriber(String serviceid, String phonenumber, String isactive)
    throws RemoteException
  {
    try
    {
      StreamingSenderState _state = _start(this._handlerChain);

      InternalSOAPMessage _request = _state.getRequest();
      _request.setOperationCode(0);

      UpdateSubscriber _myUpdateSubscriber = new UpdateSubscriber();
      _myUpdateSubscriber.setServiceid(serviceid);
      _myUpdateSubscriber.setPhonenumber(phonenumber);
      _myUpdateSubscriber.setIsactive(isactive);

      SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_UpdateSubscriber_UpdateSubscriber_QNAME);
      _bodyBlock.setValue(_myUpdateSubscriber);
      _bodyBlock.setSerializer(this.ns1_myUpdateSubscriber_LiteralSerializer);
      _request.setBody(_bodyBlock);

      _state.getMessageContext().setProperty("http.soap.action", "http://www.orcagroup.com/UpdateSubscriber");

      _send((String)_getProperty("javax.xml.rpc.service.endpoint.address"), _state);

      UpdateSubscriberResponse _result = null;
      Object _responseObj = _state.getResponse().getBody().getValue();
      if ((_responseObj instanceof SOAPDeserializationState))
        _result = (UpdateSubscriberResponse)((SOAPDeserializationState)_responseObj).getInstance();
      else {
        _result = (UpdateSubscriberResponse)_responseObj;
      }

      return _result.getUpdateSubscriberResult();
    }
    catch (RemoteException e)
    {
      throw e;
    } catch (JAXRPCException e) {
      throw new RemoteException(e.getMessage(), e);
    } catch (Exception e) {
      if ((e instanceof RuntimeException)) {
        throw ((RuntimeException)e);
      }
      throw new RemoteException(e.getMessage(), e);
    }
  }

  public ArrayOfSubscriber getSubscribers(String serviceid)
    throws RemoteException
  {
    try
    {
      StreamingSenderState _state = _start(this._handlerChain);

      InternalSOAPMessage _request = _state.getRequest();
      _request.setOperationCode(1);

      GetSubscribers _myGetSubscribers = new GetSubscribers();
      _myGetSubscribers.setServiceid(serviceid);

      SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_GetSubscribers_GetSubscribers_QNAME);
      _bodyBlock.setValue(_myGetSubscribers);
      _bodyBlock.setSerializer(this.ns1_myGetSubscribers_LiteralSerializer);
      _request.setBody(_bodyBlock);

      _state.getMessageContext().setProperty("http.soap.action", "http://www.orcagroup.com/GetSubscribers");

      _send((String)_getProperty("javax.xml.rpc.service.endpoint.address"), _state);

      GetSubscribersResponse _result = null;
      Object _responseObj = _state.getResponse().getBody().getValue();
      if ((_responseObj instanceof SOAPDeserializationState))
        _result = (GetSubscribersResponse)((SOAPDeserializationState)_responseObj).getInstance();
      else {
        _result = (GetSubscribersResponse)_responseObj;
      }

      return _result.getGetSubscribersResult();
    }
    catch (RemoteException e)
    {
      throw e;
    } catch (JAXRPCException e) {
      throw new RemoteException(e.getMessage(), e);
    } catch (Exception e) {
      if ((e instanceof RuntimeException)) {
        throw ((RuntimeException)e);
      }
      throw new RemoteException(e.getMessage(), e);
    }
  }

  protected void _readFirstBodyElement(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state)
    throws Exception
  {
    int opcode = state.getRequest().getOperationCode();
    switch (opcode) {
    case 0:
      _deserialize_UpdateSubscriber(bodyReader, deserializationContext, state);
      break;
    case 1:
      _deserialize_GetSubscribers(bodyReader, deserializationContext, state);
      break;
    default:
      throw new SenderException("sender.response.unrecognizedOperation", Integer.toString(opcode));
    }
  }

  private void _deserialize_UpdateSubscriber(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state)
    throws Exception
  {
    Object myUpdateSubscriberResponseObj = this.ns1_myUpdateSubscriberResponse_LiteralSerializer.deserialize(ns1_UpdateSubscriber_UpdateSubscriberResponse_QNAME, bodyReader, deserializationContext);

    SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns1_UpdateSubscriber_UpdateSubscriberResponse_QNAME);
    bodyBlock.setValue(myUpdateSubscriberResponseObj);
    state.getResponse().setBody(bodyBlock);
  }

  private void _deserialize_GetSubscribers(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state)
    throws Exception
  {
    Object myGetSubscribersResponseObj = this.ns1_myGetSubscribersResponse_LiteralSerializer.deserialize(ns1_GetSubscribers_GetSubscribersResponse_QNAME, bodyReader, deserializationContext);

    SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns1_GetSubscribers_GetSubscribersResponse_QNAME);
    bodyBlock.setValue(myGetSubscribersResponseObj);
    state.getResponse().setBody(bodyBlock);
  }

  protected String _getDefaultEnvelopeEncodingStyle()
  {
    return null;
  }

  public String _getImplicitEnvelopeEncodingStyle() {
    return "";
  }

  public String _getEncodingStyle() {
    return "http://schemas.xmlsoap.org/soap/encoding/";
  }

  public void _setEncodingStyle(String encodingStyle) {
    throw new UnsupportedOperationException("cannot set encoding style");
  }

  protected String[] _getNamespaceDeclarations()
  {
    return myNamespace_declarations;
  }

  public QName[] _getUnderstoodHeaders()
  {
    return understoodHeaderNames;
  }

  public void _initialize(InternalTypeMappingRegistry registry) throws Exception {
    super._initialize(registry);
    this.ns1_myUpdateSubscriber_LiteralSerializer = ((CombinedSerializer)registry.getSerializer("", UpdateSubscriber.class, ns1_UpdateSubscriber_TYPE_QNAME));
    this.ns1_myGetSubscribers_LiteralSerializer = ((CombinedSerializer)registry.getSerializer("", GetSubscribers.class, ns1_GetSubscribers_TYPE_QNAME));
    this.ns1_myUpdateSubscriberResponse_LiteralSerializer = ((CombinedSerializer)registry.getSerializer("", UpdateSubscriberResponse.class, ns1_UpdateSubscriberResponse_TYPE_QNAME));
    this.ns1_myGetSubscribersResponse_LiteralSerializer = ((CombinedSerializer)registry.getSerializer("", GetSubscribersResponse.class, ns1_GetSubscribersResponse_TYPE_QNAME));
  }
}