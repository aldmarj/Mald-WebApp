package website.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
    private String businessTag;
    private Integer clientId;
    private String clientName;
    private List<Locations> locations = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Client withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
