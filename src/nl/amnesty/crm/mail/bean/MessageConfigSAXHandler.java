package nl.amnesty.crm.mail.bean;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MessageConfigSAXHandler extends DefaultHandler
{
  private boolean inelement = false;
  private String value;
  private String hostname;
  private String debug;
  private String from;

  public MessageConfigSAXHandler()
    throws SAXException
  {
  }

  public void startDocument()
  {
  }

  public void endDocument()
  {
  }

  public void startElement(String uri, String localname, String qname, Attributes attributes)
  {
    this.inelement = true;
  }

  public void endElement(String uri, String localname, String qname)
  {
    if (qname.equals("messageconfig"))
    {
      Message message = new Message(this.hostname, this.debug, this.from);
      nl.amnesty.crm.network.orca.Main.message = message;
    }
    if (qname.equals("hostname")) {
      this.hostname = this.value;
    }
    if (qname.equals("debug")) {
      this.debug = this.value;
    }
    if (qname.equals("from")) {
      this.from = this.value;
    }
    this.value = "";
    this.inelement = false;
  }

  public void characters(char[] ch, int start, int length)
  {
    if (this.inelement) {
      String charactervalue = "";
      for (int i = 0; i < length; i++) {
        char c = ch[(start + i)];
        if ((c != '\r') && (c != '\t') && (c != '\f') && (c != '\n')) {
          charactervalue = charactervalue.concat(String.valueOf(c));
        }
      }
      this.value = charactervalue;
    }
  }
}