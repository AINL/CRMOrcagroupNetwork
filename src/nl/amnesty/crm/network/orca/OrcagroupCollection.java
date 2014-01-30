package nl.amnesty.crm.network.orca;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import javax.xml.rpc.ServiceException;
import nl.amnesty.crm.collection.IdStartdateEnddate;
import nl.amnesty.crm.network.orca.webservice.AmnestyExternalService;
import nl.amnesty.crm.network.orca.webservice.AmnestyExternalServiceSoap;
import nl.amnesty.crm.network.orca.webservice.AmnestyExternalService_Impl;
import nl.amnesty.crm.network.orca.webservice.ArrayOfSubscriber;
import nl.amnesty.crm.network.orca.webservice.Subscriber;
import nl.amnesty.crm.sql.PhoneSQL;

public class OrcagroupCollection
{
  private static final String ORCAGROUP_IDENTIFIER_ACTIVE = "True";
  private static final String ORCAGROUP_IDENTIFIER_PASSIVE = "False";
  private static final int DEELNEMER_NIEUW_INSERT_GELUKT = 10;
  private static final int DEELNEMER_NIEUW_INSERT_GELUKT_DEELNEMER_GEACTIVEERD_ALS_ABONNEE = 11;
  private static final int DEELNEMER_NIEUW_INSERT_GELUKT_DEELNEMER_GEDEACTIVEERD_ALS_ABONNEE = 12;
  private static final int DEELNEMER_BESTAAT_RECORD_GEUPDATE = 20;
  private static final int DEELNEMER_TOEGEVOEGD_ALS_ABONNEE_DEELNEMER_GEACTIVEERD_ALS_ABONNEE = 21;
  private static final int DEELNEMER_TOEGEVOEGD_ALS_ABONNEE_DEELNEMER_GEDEACTIVEERD_ALS_ABONNEE = 22;
  private static final int DEELNEMER_BESTAAT_DEELNEMER_WAS_AL_ABONNEE_GEACTIVEERD_ALS_ABONNEE = 23;
  private static final int DEELNEMER_BESTAAT_DEELNEMER_WAS_AL_ABONNEE_GEDEACTIVEERD_ALS_ABONNEE = 24;

  public static Collection<IdStartdateEnddate> getOrcagroup(Connection connection)
  {
    PhoneSQL phonesql = new PhoneSQL();
    Collection orcagroupcollection = new ArrayList();
    Date startdate = null;
    Date enddate = null;
    try {
      AmnestyExternalService service = new AmnestyExternalService_Impl();
      AmnestyExternalServiceSoap soap = service.getAmnestyExternalServiceSoap();
      ArrayOfSubscriber arrayofsubscriber = soap.getSubscribers("1");
      Subscriber[] subscriberlist = arrayofsubscriber.getSubscriber();
      int listsize = subscriberlist.length;
      for (int i = 0; i < listsize; i++) {
        Subscriber subscriber = subscriberlist[i];
        String id = subscriber.getPhoneNumber();
        if (id.startsWith("+31")) {
          id = "0".concat(id.substring(3));
        }

        Calendar modification = subscriber.getModificationDate();
        if (subscriber.isIsActive()) {
          startdate = modification.getTime();

          enddate = null;
        } else {
          enddate = modification.getTime();

          modification.add(2, -1);
          startdate = modification.getTime();
        }

        long roleid = 0L;
        IdStartdateEnddate idstartdateenddate = new IdStartdateEnddate(roleid, id, startdate, enddate);
        orcagroupcollection.add(idstartdateenddate);
      }
      return orcagroupcollection;
    } catch (ServiceException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    } catch (RemoteException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }return null;
  }

  public static boolean addOrcagroup(org.apache.log4j.Logger logger, String id)
  {
    try {
      AmnestyExternalService service = new AmnestyExternalService_Impl();
      AmnestyExternalServiceSoap soap = service.getAmnestyExternalServiceSoap();
      if (id.startsWith("0")) {
        String serviceid = "1";
        String phonenumber = "+31".concat(id.substring(1));
        String isactive = "True";

        int updatecode = soap.updateSubscriber(serviceid, phonenumber, isactive);

        if (updatecode < 0) {
          logger.error("Error while attempting to add entry with id " + id + " to Orcagroup SMS network");
          logger.error(statusMessage(updatecode));
          return false;
        }
        logger.info("Entry with id " + id + " was added to Orcagroup SMS network");
        logger.info(statusMessage(updatecode));
        return true;
      }

      return false;
    }
    catch (RemoteException ex) {
      logger.fatal(ex.getMessage(), ex);
      return false;
    } catch (ServiceException ex) {
      logger.fatal(ex.getMessage(), ex);
    }return false;
  }

  public static boolean endOrcagroup(org.apache.log4j.Logger logger, String id)
  {
    try {
      AmnestyExternalService service = new AmnestyExternalService_Impl();
      AmnestyExternalServiceSoap soap = service.getAmnestyExternalServiceSoap();
      if (id.startsWith("0")) {
        String serviceid = "1";
        String phonenumber = "+31".concat(id.substring(1));
        String isactive = "False";

        int updatecode = soap.updateSubscriber(serviceid, phonenumber, isactive);

        if (updatecode < 0) {
          logger.error("Error while attempting to remove entry with id " + id + " from Orcagroup SMS network");
          logger.error(statusMessage(updatecode));
          return false;
        }
        logger.info("Entry with id " + id + " was removed from Orcagroup SMS network");
        logger.info(statusMessage(updatecode));
        return true;
      }

      return false;
    }
    catch (RemoteException ex) {
      logger.fatal(ex.getMessage(), ex);
      return false;
    } catch (ServiceException ex) {
      logger.fatal(ex.getMessage(), ex);
    }return false;
  }

  private static String statusMessage(int status)
  {
    switch (status) {
    case 10:
      return "Deelnemer nieuw, insert gelukt";
    case 11:
      return "Deelnemer nieuw, insert gelukt, deelnemer geactiveerd als abonnee";
    case 12:
      return "Deelnemer nieuw, insert gelukt, deelnemer gedeactiveerd als abonnee";
    case 20:
      return "Deelnemer bestaat, record geupdate";
    case 21:
      return "Deelnemer toegevoegd als abonnee, deelnemer geactiveerd als abonnee";
    case 22:
      return "Deelnemer toegevoegd als abonnee, deelnemer gedeactiveerd als abonnee";
    case 23:
      return "Deelnemer bestaat, deelnemer was al (al dan niet actieve) abonnee, geactiveerd als abonnee";
    case 24:
      return "Deelnemer bestaat, deelnemer was al (al dan niet actieve) abonnee, gedeactiveerd als abonnee";
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19: } return "Fout, status is " + String.valueOf(status);
  }

  private static boolean isInteger(String value)
  {
    try {
      Integer.parseInt(value);
      return true; } catch (Exception e) {
    }
    return false;
  }
}