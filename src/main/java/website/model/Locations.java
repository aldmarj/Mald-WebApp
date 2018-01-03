package website.model;

import java.util.HashMap;
import java.util.Map;

public class Locations {
    private String description;
    private String postCode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Locations withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Locations withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Locations withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
