package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variables within SystemInfo.
 * Created by dane on 10/10/16.
 */
public class SystemItem {

    private String pod;

    @JsonCreator
    public SystemItem(@JsonProperty("pod") String pod) {
        this.pod=pod;
    }

    /**
    * Method to get the system data from the website.
    * @return a string containing the data.
    */
    public String getPod() {
        return pod;
    }
}
