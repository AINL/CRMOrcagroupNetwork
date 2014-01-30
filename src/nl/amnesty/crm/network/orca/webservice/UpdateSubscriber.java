package nl.amnesty.crm.network.orca.webservice;

public class UpdateSubscriber
{
  protected String serviceid;
  protected String phonenumber;
  protected String isactive;

  public UpdateSubscriber()
  {
  }

  public UpdateSubscriber(String serviceid, String phonenumber, String isactive)
  {
    this.serviceid = serviceid;
    this.phonenumber = phonenumber;
    this.isactive = isactive;
  }

  public String getServiceid() {
    return this.serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }

  public String getPhonenumber() {
    return this.phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getIsactive() {
    return this.isactive;
  }

  public void setIsactive(String isactive) {
    this.isactive = isactive;
  }
}