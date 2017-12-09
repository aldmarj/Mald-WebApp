package website.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client
{
  private int clientId;

  private String clientName;

  private String businessTag;

  public Client()
  {
    this.clientId = -1;
    this.clientName = "";
    this.businessTag = "";
  }

  public Client(final int clientId, final String clientName, final String businessTag)
  {
    this.clientId = clientId;
    this.clientName = clientName;
    this.businessTag = businessTag;
  }

  public int getClientId()
  {
    return this.clientId;
  }

  public String getClientName()
  {
    return this.clientName;
  }

  public String getBusinessTag()
  {
    return this.businessTag;
  }

  public void setClientId(int clientId)
  {
    this.clientId = clientId;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public void setBusinessTag(String businessTag)
  {
    this.businessTag = businessTag;
  }
}
