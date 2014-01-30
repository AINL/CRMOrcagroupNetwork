package nl.amnesty.crm.network.orca.webservice;

public class ArrayOfSubscriber
{
  protected Subscriber[] subscriber;

  public ArrayOfSubscriber()
  {
  }

  public ArrayOfSubscriber(Subscriber[] subscriber)
  {
    this.subscriber = subscriber;
  }

  public Subscriber[] getSubscriber() {
    return this.subscriber;
  }

  public void setSubscriber(Subscriber[] subscriber) {
    this.subscriber = subscriber;
  }
}