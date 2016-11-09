package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemItem {

    private String pod;

    @JsonCreator
    public SystemItem(@JsonProperty("pod") String pod) {
        this.pod=pod;
    }

    public String getPod() {
        return pod;
    }
}
