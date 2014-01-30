package nl.amnesty.crm.db;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DatabaseConfigSAXHandler extends DefaultHandler
{
  private boolean inelement = false;
  private String value;
  private String hostname;
  private String instance;
  private int portnumber;
  private String databasename;
  private String username;
  private String password;

  public DatabaseConfigSAXHandler()
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
    if ((!qname.equals("crmconfig")) || 
      (qname.equals("dbserver")))
    {
      Database database = new Database(1, this.hostname, this.instance, this.portnumber, this.databasename, this.username, this.password);
      nl.amnesty.crm.network.orca.Main.database = database;
    }

    if (qname.equals("hostname")) {
      this.hostname = this.value;
    }
    if (qname.equals("instance")) {
      this.instance = this.value;
    }
    if (qname.equals("portnumber")) {
      this.portnumber = Integer.parseInt(this.value);
    }
    if (qname.equals("databasename")) {
      this.databasename = this.value;
    }
    if (qname.equals("username")) {
      this.username = this.value;
    }
    if (qname.equals("password")) {
      this.password = this.value;
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