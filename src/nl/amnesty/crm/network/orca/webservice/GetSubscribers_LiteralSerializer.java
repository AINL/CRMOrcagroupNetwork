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
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;

public class GetSubscribers_LiteralSerializer extends LiteralObjectSerializerBase
  implements Initializable
{
  private static final QName ns1_serviceid_QNAME = new QName("http://www.orcagroup.com/", "serviceid");
  private static final QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
  private CombinedSerializer ns2_myns2_string__java_lang_String_String_Serializer;

  public GetSubscribers_LiteralSerializer(QName type, String encodingStyle)
  {
    this(type, encodingStyle, false);
  }

  public GetSubscribers_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
    super(type, true, encodingStyle, encodeType);
  }

  public void initialize(InternalTypeMappingRegistry registry) throws Exception {
    this.ns2_myns2_string__java_lang_String_String_Serializer = ((CombinedSerializer)registry.getSerializer("", String.class, ns2_string_TYPE_QNAME));
  }

  public Object doDeserialize(XMLReader reader, SOAPDeserializationContext context) throws Exception
  {
    GetSubscribers instance = new GetSubscribers();
    Object member = null;

    reader.nextElementContent();
    QName elementName = reader.getName();
    if ((reader.getState() == 1) && 
      (elementName.equals(ns1_serviceid_QNAME))) {
      member = this.ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_serviceid_QNAME, reader, context);
      if (member == null) {
        throw new DeserializationException("literal.unexpectedNull");
      }
      instance.setServiceid((String)member);
      reader.nextElementContent();
    }

    XMLReaderUtil.verifyReaderState(reader, 2);
    return instance;
  }

  public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    GetSubscribers instance = (GetSubscribers)obj;
  }

  public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    GetSubscribers instance = (GetSubscribers)obj;

    if (instance.getServiceid() != null)
      this.ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getServiceid(), ns1_serviceid_QNAME, null, writer, context);
  }
}