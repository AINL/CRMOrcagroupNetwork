package nl.amnesty.crm.network.orca.webservice;

import com.sun.xml.rpc.encoding.CombinedSerializer;
import com.sun.xml.rpc.encoding.Initializable;
import com.sun.xml.rpc.encoding.InternalTypeMappingRegistry;
import com.sun.xml.rpc.encoding.SOAPDeserializationContext;
import com.sun.xml.rpc.encoding.SOAPSerializationContext;
import com.sun.xml.rpc.encoding.literal.LiteralObjectSerializerBase;
import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLReaderUtil;
import com.sun.xml.rpc.streaming.XMLWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;

public class ArrayOfSubscriber_LiteralSerializer extends LiteralObjectSerializerBase
  implements Initializable
{
  private static final QName ns1_Subscriber_QNAME = new QName("http://www.orcagroup.com/", "Subscriber");
  private static final QName ns1_Subscriber_TYPE_QNAME = new QName("http://www.orcagroup.com/", "Subscriber");
  private CombinedSerializer ns1_mySubscriber_LiteralSerializer;

  public ArrayOfSubscriber_LiteralSerializer(QName type, String encodingStyle)
  {
    this(type, encodingStyle, false);
  }

  public ArrayOfSubscriber_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
    super(type, true, encodingStyle, encodeType);
  }

  public void initialize(InternalTypeMappingRegistry registry) throws Exception {
    this.ns1_mySubscriber_LiteralSerializer = ((CombinedSerializer)registry.getSerializer("", Subscriber.class, ns1_Subscriber_TYPE_QNAME));
  }

  public Object doDeserialize(XMLReader reader, SOAPDeserializationContext context) throws Exception
  {
    ArrayOfSubscriber instance = new ArrayOfSubscriber();
    Object member = null;

    reader.nextElementContent();
    QName elementName = reader.getName();
    if ((reader.getState() == 1) && (elementName.equals(ns1_Subscriber_QNAME))) {
      List values = new ArrayList();
      while (true) {
        elementName = reader.getName();
        if ((reader.getState() != 1) || (!elementName.equals(ns1_Subscriber_QNAME))) break;
        Object value = this.ns1_mySubscriber_LiteralSerializer.deserialize(ns1_Subscriber_QNAME, reader, context);
        values.add(value);
        reader.nextElementContent();
      }

      member = new Subscriber[values.size()];
      member = values.toArray((Object[])member);
      instance.setSubscriber((Subscriber[])member);
    }
    else {
      instance.setSubscriber(new Subscriber[0]);
    }

    XMLReaderUtil.verifyReaderState(reader, 2);
    return instance;
  }

  public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    ArrayOfSubscriber instance = (ArrayOfSubscriber)obj;
  }

  public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    ArrayOfSubscriber instance = (ArrayOfSubscriber)obj;

    if (instance.getSubscriber() != null)
      for (int i = 0; i < instance.getSubscriber().length; i++)
        this.ns1_mySubscriber_LiteralSerializer.serialize(instance.getSubscriber()[i], ns1_Subscriber_QNAME, null, writer, context);
  }
}