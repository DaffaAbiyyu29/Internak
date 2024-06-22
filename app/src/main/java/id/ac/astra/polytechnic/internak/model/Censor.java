package id.ac.astra.polytechnic.internak.model;

import com.google.gson.annotations.SerializedName;

public class Censor {
    private int cns_id;
    @SerializedName("cns_name")
    private String cns_name;
    @SerializedName("cns_description")
    private String cns_description;
    @SerializedName("cns_status")
    private int cns_status;

    public Censor() {
    }

    public Censor(int cns_id, String cns_name, String cns_description) {
        this.cns_id = cns_id;
        this.cns_name = cns_name;
        this.cns_description = cns_description;
    }

    public int getCns_id() {
        return cns_id;
    }

    public void setCns_id(Integer cns_id) {
        this.cns_id = cns_id;
    }

    public String getCns_name() {
        return cns_name;
    }

    public void setCns_name(String cns_name) {
        this.cns_name = cns_name;
    }

    public String getCns_description() {
        return cns_description;
    }

    public void setCns_description(String cns_description) {
        this.cns_description = cns_description;
    }

    public int getCns_status() {
        return cns_status;
    }

    public void setCns_status(Integer cns_status) {
        this.cns_status = cns_status;
    }
}
