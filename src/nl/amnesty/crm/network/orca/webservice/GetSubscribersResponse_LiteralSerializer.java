package nl.amnesty.crm.network.orca.webservice;

import com.sun.xml.rpc.encoding.CombinedSerializer;
import com.sun.xml.rpc.encoding.DeserializationException;
import com.sun.xml.rpc.encoding.Initializable;
import com.sun.xml.rpc.encoding.InternalTypeMappingRegistry;
import com.sun.xml.rpc.encoding.SOAPDeserializationContext;
import com.sun.xml.rpc.encoding.SOAPSerializationContext;
import com.sun.xml.rpc.encoding.literal.LiteralObjectSerializerBase;
import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLReaderUtil;
import com.sun.xml.rpc.streaming.XMLWriter;
import javax.xml.namespace.QName;

public class GetSubscribersResponse_LiteralSerializer extends LiteralObjectSerializerBase
  implements Initializable
{
  private static final QName ns1_GetSubscribersResult_QNAME = new QName("http://www.orcagroup.com/", "GetSubscribersResult");
  private static final QName ns1_ArrayOfSubscriber_TYPE_QNAME = new QName("http://www.orcagroup.com/", "ArrayOfSubscriber");
  private CombinedSerializer ns1_myArrayOfSubscriber_LiteralSerializer;

  public GetSubscribersResponse_LiteralSerializer(QName type, String encodingStyle)
  {
    this(type, encodingStyle, false);
  }

  public GetSubscribersResponse_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
    super(type, true, encodingStyle, encodeType);
  }

  public void initialize(InternalTypeMappingRegistry registry) throws Exception {
    this.ns1_myArrayOfSubscriber_LiteralSerializer = ((CombinedSerializer)registry.getSerializer("", ArrayOfSubscriber.class, ns1_ArrayOfSubscriber_TYPE_QNAME));
  }

  public Object doDeserialize(XMLReader reader, SOAPDeserializationContext context) throws Exception
  {
    GetSubscribersResponse instance = new GetSubscribersResponse();
    Object member = null;

    reader.nextElementContent();
    QName elementName = reader.getName();
    if ((reader.getState() == 1) && 
      (elementName.equals(ns1_GetSubscribersResult_QNAME))) {
      member = this.ns1_myArrayOfSubscriber_LiteralSerializer.deserialize(ns1_GetSubscribersResult_QNAME, reader, context);
      if (member == null) {
        throw new DeserializationException("literal.unexpectedNull");
      }
      instance.setGetSubscribersResult((ArrayOfSubscriber)member);
      reader.nextElementContent();
    }

    XMLReaderUtil.verifyReaderState(reader, 2);
    return instance;
  }

  public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    GetSubscribersResponse instance = (GetSubscribersResponse)obj;
  }

  public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    GetSubscribersResponse instance = (GetSubscribersResponse)obj;

    if (instance.getGetSubscribersResult() != null)
      this.ns1_myArrayOfSubscriber_LiteralSerializer.serialize(instance.getGetSubscribersResult(), ns1_GetSubscribersResult_QNAME, null, writer, context);
  }
}