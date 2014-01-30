package nl.amnesty.crm.network.orca.webservice;

import java.util.Calendar;

public class Subscriber
{
  protected String phoneNumber;
  protected boolean isActive;
  protected Calendar modificationDate;

  public Subscriber()
  {
  }

  public Subscriber(String phoneNumber, boolean isActive, Calendar modificationDate)
  {
    this.phoneNumber = phoneNumber;
    this.isActive = isActive;
    this.modificationDate = modificationDate;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public boolean isIsActive() {
    return this.isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public Calendar getModificationDate() {
    return this.modificationDate;
  }

  public void setModificationDate(Calendar modificationDate) {
    this.modificationDate = modificationDate;
  }
}