package nl.amnesty.crm.network.orca.webservice;

import com.sun.xml.rpc.client.BasicService;
import com.sun.xml.rpc.encoding.CombinedSerializer;
import com.sun.xml.rpc.encoding.SerializerConstants;
import com.sun.xml.rpc.encoding.SingletonDeserializerFactory;
import com.sun.xml.rpc.encoding.SingletonSerializerFactory;
import javax.xml.namespace.QName;
import javax.xml.rpc.encoding.Deserializer;
import javax.xml.rpc.encoding.Serializer;
import javax.xml.rpc.encoding.TypeMapping;
import javax.xml.rpc.encoding.TypeMappingRegistry;

public class AmnestyExternalService_SerializerRegistry
        implements SerializerConstants {

    public TypeMappingRegistry getRegistry() {
        QName type;
        CombinedSerializer serializer;
        TypeMappingRegistry registry = BasicService.createStandardTypeMappingRegistry();
        TypeMapping mapping12 = registry.getTypeMapping("http://www.w3.org/2002/06/soap-encoding");
        TypeMapping mapping = registry.getTypeMapping("http://schemas.xmlsoap.org/soap/encoding/");
        TypeMapping mapping2 = registry.getTypeMapping("");

        type = new QName("http://www.orcagroup.com/", "Subscriber");
        serializer = new Subscriber_LiteralSerializer(type, "", false);
        registerSerializer(mapping2, Subscriber.class, type, serializer);

        type = new QName("http://www.orcagroup.com/", "UpdateSubscriberResponse");
        serializer = new UpdateSubscriberResponse_LiteralSerializer(type, "", false);
        registerSerializer(mapping2, UpdateSubscriberResponse.class, type, serializer);

        type = new QName("http://www.orcagroup.com/", "UpdateSubscriber");
        serializer = new UpdateSubscriber_LiteralSerializer(type, "", false);
        registerSerializer(mapping2, UpdateSubscriber.class, type, serializer);

        type = new QName("http://www.orcagroup.com/", "GetSubscribersResponse");
        serializer = new GetSubscribersResponse_LiteralSerializer(type, "", false);
        registerSerializer(mapping2, GetSubscribersResponse.class, type, serializer);

        type = new QName("http://www.orcagroup.com/", "ArrayOfSubscriber");
        serializer = new ArrayOfSubscriber_LiteralSerializer(type, "", false);
        registerSerializer(mapping2, ArrayOfSubscriber.class, type, serializer);

        type = new QName("http://www.orcagroup.com/", "GetSubscribers");
        serializer = new GetSubscribers_LiteralSerializer(type, "", false);
        registerSerializer(mapping2, GetSubscribers.class, type, serializer);

        return registry;
    }

    private static void registerSerializer(TypeMapping mapping, Class javaType, QName xmlType, Serializer ser) {
        mapping.register(javaType, xmlType, new SingletonSerializerFactory(ser), new SingletonDeserializerFactory((Deserializer) ser));
    }
}