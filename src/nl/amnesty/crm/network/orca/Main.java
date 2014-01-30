package nl.amnesty.crm.network.orca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import nl.amnesty.crm.collection.IdStartdateEnddate;
import nl.amnesty.crm.db.Database;
import nl.amnesty.crm.db.DatabaseConfigSAXHandler;
import nl.amnesty.crm.mail.bean.Message;
import nl.amnesty.crm.mail.bean.MessageConfigSAXHandler;
import nl.amnesty.crm.mail.bean.MessageFacade;
import nl.amnesty.sys.sync.Delta;
import nl.amnesty.sys.sync.Replicator;
import org.apache.log4j.PropertyConfigurator;

public class Main {

    private static final String CONFIG_URL = "http://localhost:8080/nlamnestyconfig/network.xml";
    private static final String CONFIG_CRM = "/root/crmorcagroupnetwork/crmconfig.xml";
    private static final String CONFIG_MESSAGE = "/root/crmorcagroupnetwork/messageconfig.xml";
    private static final String LOG4J_PROPERTIES = "/root/crmorcagroupnetwork/log4j.properties";
    private static final String NOTIFICATION_TARGET_1 = "e.vanvelzen@amnesty.nl";
    private static final String NOTIFICATION_TARGET_2 = "r.vanderharst@amnesty.nl";
    private static final String NOTIFICATION_TARGET_3 = "s.grootegoed@amnesty.nl";
    public static Database database;
    public static Message message;
    private static final int limit = 10000;

    public static void main(String[] args) {
        PropertyConfigurator.configure("/root/crmorcagroupnetwork/log4j.properties");
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Main.class);

        getDatabaseConfig();
        getMessageConfig();

        Connection connection = null;

        List recipientlist = new ArrayList();

        Delta delta = new Delta();
        try {
            database.setDbvendor(1);
            connection = database.open();

            URL url = new URL("http://localhost:8080/nlamnestyconfig/network.xml");

            Collection orcagroupcollection = OrcagroupCollection.getOrcagroup(connection);
            Collection amnestycollection = AmnestyCollection.getAmnesty(connection, url);

            delta = Replicator.replicate(delta, amnestycollection, orcagroupcollection);

            doReplication(logger, connection, url, delta);

            String text = getLogfile();
            MessageFacade messagefacade = new MessageFacade();

            recipientlist.add("e.vanvelzen@amnesty.nl");
            recipientlist.add("r.vanderharst@amnesty.nl");
            recipientlist.add("s.grootegoed@amnesty.nl");
            messagefacade.sendEmail(message.getSmtphostname(), message.getSmtpdebug(), message.getMimemessagefrom(), recipientlist, "CRMOrcagroup synchronized", text);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void doReplication(org.apache.log4j.Logger logger, Connection connection, URL url, Delta delta) {
            int count;
        try {
            count = 0;
            Collection<IdStartdateEnddate> increase_collection_a = delta.getIncrease_collection_a();
            for (IdStartdateEnddate inc_a : increase_collection_a) {
                if (count < 10000) {
                    AmnestyCollection.addAmnesty(logger, connection, url, inc_a.getRoleid(), inc_a.getId());
                    count += 1;
                }
            }

            count = 0;
            Collection<IdStartdateEnddate> decrease_collection_a = delta.getDecrease_collection_a();
            for (IdStartdateEnddate dec_a : decrease_collection_a) {
                if (count < 10000) {
                    AmnestyCollection.endAmnesty(logger, connection, url, dec_a.getRoleid(), dec_a.getId());
                    count += 1;
                }
            }

            count = 0;
            Collection<IdStartdateEnddate> increase_collection_b = delta.getIncrease_collection_b();
            for (IdStartdateEnddate inc_b : increase_collection_b) {
                if (count < 10000) {
                    OrcagroupCollection.addOrcagroup(logger, inc_b.getId());
                    count += 1;
                }
            }

            count = 0;
            Collection<IdStartdateEnddate> decrease_collection_b = delta.getDecrease_collection_b();
            for (IdStartdateEnddate dec_b : decrease_collection_b) {
                if (count < 10000) {
                    OrcagroupCollection.endOrcagroup(logger, dec_b.getId());
                    count += 1;
                }
            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void getDatabaseConfig() {
        try {
            File file = new File("/root/crmorcagroupnetwork/crmconfig.xml");
            SAXParserFactory factorySAX = SAXParserFactory.newInstance();
            SAXParser sax = factorySAX.newSAXParser();
            DatabaseConfigSAXHandler saxhandler = new DatabaseConfigSAXHandler();
            sax.parse(file, saxhandler);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void getMessageConfig() {
        try {
            File file = new File("/root/crmorcagroupnetwork/messageconfig.xml");
            SAXParserFactory factorySAX = SAXParserFactory.newInstance();
            SAXParser sax = factorySAX.newSAXParser();
            MessageConfigSAXHandler saxhandler = new MessageConfigSAXHandler();
            sax.parse(file, saxhandler);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static String getLogfile() {
        String text = "";
        FileReader fr = null;
        BufferedReader br = null;
        try {
            Properties log4jproperties = getLOG4JProperties();
            String logfile = log4jproperties.getProperty("log4j.appender.logfile.File");
            fr = new FileReader(logfile);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                text = text.concat(line);
                text = text + '\n';
            }

            return text;
        } catch (IOException ioe) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioe);

            return text;
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

            return text;
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException ioe) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioe);
            }
        }
    }

    private static Properties getLOG4JProperties() {
        try {
            ResourceBundle resourcebundle = new PropertyResourceBundle(new FileInputStream("/root/crmorcagroupnetwork/log4j.properties"));

            Properties properties = new Properties();
            Enumeration enumeration = resourcebundle.getKeys();

            while (enumeration.hasMoreElements()) {
                String property = (String) enumeration.nextElement();
                String value = resourcebundle.getString(property);

                properties.setProperty(property, value);
            }
            return properties;
        } catch (Exception e) {
        }
        return null;
    }
}