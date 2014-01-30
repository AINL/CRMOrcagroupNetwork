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
import java.util.Calendar;
import javax.xml.namespace.QName;

public class Subscriber_LiteralSerializer extends LiteralObjectSerializerBase
  implements Initializable
{
  private static final QName ns1_phoneNumber_QNAME = new QName("http://www.orcagroup.com/", "phoneNumber");
  private static final QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
  private CombinedSerializer ns2_myns2_string__java_lang_String_String_Serializer;
  private static final QName ns1_isActive_QNAME = new QName("http://www.orcagroup.com/", "isActive");
  private static final QName ns2_boolean_TYPE_QNAME = SchemaConstants.QNAME_TYPE_BOOLEAN;
  private CombinedSerializer ns2_myns2__boolean__boolean_Boolean_Serializer;
  private static final QName ns1_modificationDate_QNAME = new QName("http://www.orcagroup.com/", "modificationDate");
  private static final QName ns2_dateTime_TYPE_QNAME = SchemaConstants.QNAME_TYPE_DATE_TIME;
  private CombinedSerializer ns2_myns2_dateTime__java_util_Calendar_DateTimeCalendar_Serializer;

  public Subscriber_LiteralSerializer(QName type, String encodingStyle)
  {
    this(type, encodingStyle, false);
  }

  public Subscriber_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
    super(type, true, encodingStyle, encodeType);
  }

  public void initialize(InternalTypeMappingRegistry registry) throws Exception {
    this.ns2_myns2_string__java_lang_String_String_Serializer = ((CombinedSerializer)registry.getSerializer("", String.class, ns2_string_TYPE_QNAME));
    this.ns2_myns2__boolean__boolean_Boolean_Serializer = ((CombinedSerializer)registry.getSerializer("", Boolean.TYPE, ns2_boolean_TYPE_QNAME));
    this.ns2_myns2_dateTime__java_util_Calendar_DateTimeCalendar_Serializer = ((CombinedSerializer)registry.getSerializer("", Calendar.class, ns2_dateTime_TYPE_QNAME));
  }

  public Object doDeserialize(XMLReader reader, SOAPDeserializationContext context) throws Exception
  {
    Subscriber instance = new Subscriber();
    Object member = null;

    reader.nextElementContent();
    QName elementName = reader.getName();
    if ((reader.getState() == 1) && 
      (elementName.equals(ns1_phoneNumber_QNAME))) {
      member = this.ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns1_phoneNumber_QNAME, reader, context);
      if (member == null) {
        throw new DeserializationException("literal.unexpectedNull");
      }
      instance.setPhoneNumber((String)member);
      reader.nextElementContent();
    }

    elementName = reader.getName();
    if (reader.getState() == 1) {
      if (elementName.equals(ns1_isActive_QNAME)) {
        member = this.ns2_myns2__boolean__boolean_Boolean_Serializer.deserialize(ns1_isActive_QNAME, reader, context);
        if (member == null) {
          throw new DeserializationException("literal.unexpectedNull");
        }
        instance.setIsActive(((Boolean)member).booleanValue());
        reader.nextElementContent();
      } else {
        throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_isActive_QNAME, reader.getName() });
      }
    }
    else {
      throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
    }
    elementName = reader.getName();
    if (reader.getState() == 1) {
      if (elementName.equals(ns1_modificationDate_QNAME)) {
        member = this.ns2_myns2_dateTime__java_util_Calendar_DateTimeCalendar_Serializer.deserialize(ns1_modificationDate_QNAME, reader, context);
        if (member == null) {
          throw new DeserializationException("literal.unexpectedNull");
        }
        instance.setModificationDate((Calendar)member);
        reader.nextElementContent();
      } else {
        throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_modificationDate_QNAME, reader.getName() });
      }
    }
    else {
      throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
    }

    XMLReaderUtil.verifyReaderState(reader, 2);
    return instance;
  }

  public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    Subscriber instance = (Subscriber)obj;
  }

  public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
    Subscriber instance = (Subscriber)obj;

    if (instance.getPhoneNumber() != null) {
      this.ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getPhoneNumber(), ns1_phoneNumber_QNAME, null, writer, context);
    }
    if (new Boolean(instance.isIsActive()) == null) {
      throw new SerializationException("literal.unexpectedNull");
    }
    this.ns2_myns2__boolean__boolean_Boolean_Serializer.serialize(new Boolean(instance.isIsActive()), ns1_isActive_QNAME, null, writer, context);
    if (instance.getModificationDate() == null) {
      throw new SerializationException("literal.unexpectedNull");
    }
    this.ns2_myns2_dateTime__java_util_Calendar_DateTimeCalendar_Serializer.serialize(instance.getModificationDate(), ns1_modificationDate_QNAME, null, writer, context);
  }
}