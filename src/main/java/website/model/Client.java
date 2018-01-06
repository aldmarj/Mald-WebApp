package website.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import website.utils.DateUtils;

import java.util.List;

/**
 * Model for client information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
	
	/** The business context of the client **/
    private String businessTag;
    
    /** The unique id of the client **/
    private Integer clientId;
    
    /** The name of the client **/
    private String clientName;
    
    /** The locations associated with the client **/
    private List<Locations> locations = null;
    
    /** A optional field to return an amount of hours worked. Used
     * for dashboard purposes.
     **/
    private Long hoursWorked;
    
    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    public Client withBusinessTag(String businessTag) {
        this.businessTag = businessTag;
        return this;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Client withClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Client withClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }

    public Client withLocations(List<Locations> locations) {
        this.locations = locations;
        return this;
    }
    
    public int getHoursWorked() {
		return DateUtils.convertMillitoHours(hoursWorked);
	}

	public void setHoursWorked(Long hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
}
