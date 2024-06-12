package id.ac.astra.polytechnic.internak.model;

import com.google.gson.annotations.SerializedName;

public class City {
    private int cty_id;
    @SerializedName("cty_name")
    private String cty_name;
    @SerializedName("prv_id")
    private int prv_id;

    public City() {
    }

    public City(int cty_id, String cty_name, int prv_id) {
        this.cty_id = cty_id;
        this.cty_name = cty_name;
        this.prv_id = prv_id;
    }

    public int getCty_id() {
        return cty_id;
    }

    public void setCty_id(int cty_id) {
        this.cty_id = cty_id;
    }

    public String getCty_name() {
        return cty_name;
    }

    public void setCty_name(String cty_name) {
        this.cty_name = cty_name;
    }

    public int getPrv_id() {
        return prv_id;
    }

    public void setPrv_id(int prv_id) {
        this.prv_id = prv_id;
    }
}
