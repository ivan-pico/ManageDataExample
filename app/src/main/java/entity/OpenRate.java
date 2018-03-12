package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ipicomar on 12/03/2018.
 */

// Class generated from http://www.jsonschema2pojo.org/

public class OpenRate {

    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private Rates rates;

    @Override
    public String toString() {
        return "OpenRate{" +
                "disclaimer='" + disclaimer + '\'' +
                ", license='" + license + '\'' +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }
}
