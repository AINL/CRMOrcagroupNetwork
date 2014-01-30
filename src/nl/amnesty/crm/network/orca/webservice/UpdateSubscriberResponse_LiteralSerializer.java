package nl.amnesty.crm.network.orca.webservice;

import com.sun.xml.rpc.encoding.CombinedSerializer;
import com.sun.xml.rpc.encoding.DeserializationException;
import com.sun.xml.rpc.encoding.Initializable;
import com.sun.xml.rpc.encoding.InternalTypeMappingRegistry;
import com.sun.xml.rpc.encoding.SOAPDeserializationContext;
import com.sun.xml.rpc.encoding.SOAPSerializationContext;
import com.sun.xml.rpc.encoding.SerializationException;
import com.sun.xml.rpc.encoding.literal.LiteralObjectSerializerBase;
import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLReaderUtil;
import com.sun.xml.rpc.streaming.XMLWriter;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;

public class UpdateSubscriberResponse_LiteralSerializer extends LiteralObjectSerializerBase
  implements Initializable
{
  private static final QName ns1_UpdateSubscriberResult_QNAME = new QName("http://www.orcagroup.com/", "UpdateSubscriberResult");
  private static final QName ns2_int_TYPE_QNAME = SchemaConstants.QNAME_TYPE_INT;
  private CombinedSerializer ns2_myns2__int__int_Int_Serializer;

  public UpdateSubscriberResponse_LiteralSerializer(QName type, String encodingStyle)
  {
    this(type, encodingStyle, false);
  }

  public UpdateSubscriberResponse_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
    super(type, true, encodingStyle, encodeType);
  }

  public void initialize(InternalTypeMappingRegistry registry) throws Exception {
    this.ns2_myns2__int__int_Int_Serializer = ((CombinedSerializer)registry.getSerializer("", Integer.TYPE, ns2_int_TYPE_QNAME));
  }

  public Object doDeserialize(XMLReader reader, SOAPDeserializationContext context) throws Exception
  {
    UpdateSubscriberResponse instance = new UpdateSubscriberResponse();
    Object member = null;

    reader.nextElementContent();
    QName elementName = reader.getName();
    if (reader.getState() == 1) {
      if (elementName.equals(ns1_UpdateSubscriberResult_QNAME)) {
        member = this.ns2_myns2__int__int_Int_Serializer.deserialize(ns1_UpdateSubscriberResult_QNAME, reader, context);
        if (member == null) {
          throw new DeserializationException("literal.unexpectedNull");
        }
        instance.setUpdateSubscriberResult(((Integer)member).intValue());
        reader.nextElementContent();
      } else {
        throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_UpdateSubscriberResult_QNAME, reader.getName() });
      }
    }
    else {
      throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
    }

    XMLReaderUtil.verifyReaderState(reader, 2);
    return instance;
  }

  public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    UpdateSubscriberResponse instance = (UpdateSubscriberResponse)obj;
  }

  public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    UpdateSubscriberResponse instance = (UpdateSubscriberResponse)obj;

    if (new Integer(instance.getUpdateSubscriberResult()) == null) {
      throw new SerializationException("literal.unexpectedNull");
    }
    this.ns2_myns2__int__int_Int_Serializer.serialize(new Integer(instance.getUpdateSubscriberResult()), ns1_UpdateSubscriberResult_QNAME, null, writer, context);
  }
}