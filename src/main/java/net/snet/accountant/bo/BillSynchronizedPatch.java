package net.snet.accountant.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.json.JsonSnakeCase;

import java.sql.Timestamp;

@JsonSnakeCase
public class BillSynchronizedPatch {

    @JsonProperty
    private Timestamp synchronizedOn;

    public Timestamp getSynchronizedOn() {
        return synchronizedOn;
    }

    public void setSynchronizedOn(Timestamp synchronizedOn) {
        this.synchronizedOn = synchronizedOn;
    }
}
