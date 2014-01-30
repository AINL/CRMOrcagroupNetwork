package nl.amnesty.crm.network.orca;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import nl.amnesty.crm.collection.IdStartdateEnddate;
import nl.amnesty.crm.entity.Network;
import nl.amnesty.crm.entity.Phone;
import nl.amnesty.crm.entity.Role;
import nl.amnesty.crm.sql.NetworkSQL;
import nl.amnesty.crm.sql.PhoneSQL;
import nl.amnesty.crm.sql.RoleSQL;

public class AmnestyCollection
{
  public static Collection<IdStartdateEnddate> getAmnesty(Connection connection, URL url)
  {
    Collection amnestycollection = new ArrayList();
    try {
      NetworkSQL networksql = new NetworkSQL();
      String filter = "";
      Network network = networksql.read(connection, url, "TXT", filter);

      return network.getIdlist();
    }
    catch (Exception e)
    {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    }return null;
  }

  public static boolean addAmnesty(org.apache.log4j.Logger logger, Connection connection, URL url, long roleid, String id)
  {
    NetworkSQL networksql = new NetworkSQL();
    RoleSQL rolesql = new RoleSQL();
    PhoneSQL phonesql = new PhoneSQL();
    try
    {
      if ((roleid == 0L) && 
        (isInteger(id))) {
        long number = Long.valueOf(id).longValue();
        List phonelist = phonesql.readPhonelistViaNumber(connection, number);
        if (!phonelist.isEmpty()) {
          Phone phone = (Phone)phonelist.get(0);
          roleid = phone.getRoleid();
        } else {
          Role rolenew = new Role();
          Phone phone = new Phone();
          phone.setNumber(number);
          phonelist.add(phone);
          rolenew.setPhonelist(phonelist);
          Role role = rolesql.create(connection, rolenew);
          roleid = role.getRoleid();
        }
      }

      String source = "";
      if (networksql.add(connection, url, "TXT", roleid, source)) {
        logger.info("Entry with id " + id + " was added to Amnesty SMS network");
        return true;
      }
      logger.error("Error while attempting to add entry with id " + id + " for roleid " + roleid + " to Amnesty SMS network");
      return false;
    }
    catch (Exception e) {
      logger.fatal(e.getMessage(), e);
    }return false;
  }

  public static boolean endAmnesty(org.apache.log4j.Logger logger, Connection connection, URL url, long roleid, String id)
  {
    NetworkSQL networksql = new NetworkSQL();
    PhoneSQL phonesql = new PhoneSQL();
    boolean found = false;
    List roleidlist = new ArrayList();
    String roleidlistdisplay = "";
    try
    {
      if (roleid == 0L) {
        if (isInteger(id)) {
          long number = Long.valueOf(id).longValue();
          List<Phone> phonelist = phonesql.readPhonelistViaNumber(connection, number);
          for (Phone phone : phonelist) {
            roleidlist.add(Long.valueOf(phone.getRoleid()));
            found = true;
          }
        }
        if (found) {
          for (Iterator i$ = roleidlist.iterator(); i$.hasNext(); ) { long roleidfound = ((Long)i$.next()).longValue();
            if (networksql.end(connection, url, "TXT", roleidfound)) {
              logger.info("Entry with id " + id + " was removed from Amnesty SMS network");
              return true;
            }
            roleidlistdisplay = roleidlistdisplay.concat(String.valueOf(roleidfound)).concat(" ");
          }
          roleidlistdisplay = roleidlistdisplay.trim();
          logger.error("Error while attempting to remove entry with id " + id + " for roleid " + roleidlistdisplay + " from Amnesty SMS network");
          return false;
        }
        logger.warn("Unable to locate entry with id " + id + " in Amnesty SMS network");
        return false;
      }

      if (networksql.end(connection, url, "TXT", roleid)) {
        logger.info("Entry with id " + id + " was removed from Amnesty SMS network");
        return true;
      }
      logger.error("Error while attempting to remove entry with id " + id + " for roleid " + roleid + " from Amnesty SMS network");
      return false;
    }
    catch (Exception e)
    {
      logger.fatal(e.getMessage(), e);
    }return false;
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