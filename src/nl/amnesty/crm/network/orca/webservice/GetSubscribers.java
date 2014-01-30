package nl.amnesty.crm.network.orca.webservice;

public class GetSubscribers
{
  protected String serviceid;

  public GetSubscribers()
  {
  }

  public GetSubscribers(String serviceid)
  {
    this.serviceid = serviceid;
  }

  public String getServiceid() {
    return this.serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }
}