package nl.amnesty.crm.network.orca.webservice;

public class GetSubscribersResponse
{
  protected ArrayOfSubscriber getSubscribersResult;

  public GetSubscribersResponse()
  {
  }

  public GetSubscribersResponse(ArrayOfSubscriber getSubscribersResult)
  {
    this.getSubscribersResult = getSubscribersResult;
  }

  public ArrayOfSubscriber getGetSubscribersResult() {
    return this.getSubscribersResult;
  }

  public void setGetSubscribersResult(ArrayOfSubscriber getSubscribersResult) {
    this.getSubscribersResult = getSubscribersResult;
  }
}