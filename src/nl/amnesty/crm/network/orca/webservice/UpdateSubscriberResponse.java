package nl.amnesty.crm.network.orca.webservice;

public class UpdateSubscriberResponse
{
  protected int updateSubscriberResult;

  public UpdateSubscriberResponse()
  {
  }

  public UpdateSubscriberResponse(int updateSubscriberResult)
  {
    this.updateSubscriberResult = updateSubscriberResult;
  }

  public int getUpdateSubscriberResult() {
    return this.updateSubscriberResult;
  }

  public void setUpdateSubscriberResult(int updateSubscriberResult) {
    this.updateSubscriberResult = updateSubscriberResult;
  }
}