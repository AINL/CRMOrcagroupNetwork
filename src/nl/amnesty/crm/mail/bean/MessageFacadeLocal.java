package nl.amnesty.crm.mail.bean;

import java.util.List;

public abstract interface MessageFacadeLocal
{
  public abstract void sendEmail(String paramString1, String paramString2, String paramString3, List<String> paramList, String paramString4, String paramString5);
}