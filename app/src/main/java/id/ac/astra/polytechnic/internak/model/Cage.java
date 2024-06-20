package id.ac.astra.polytechnic.internak.model;

import com.google.gson.annotations.SerializedName;

public class Cage {
    private int cagId;
    @SerializedName("cag_name")
    private String cagName;
    @SerializedName("cag_location")
    private String cagLocation;
    @SerializedName("cag_type")
    private String cagType;
    @SerializedName("cty_id")
    private int ctyId;
    @SerializedName("cag_capacity")
    private int cagCapacity;
    @SerializedName("cag_status")
    private int cagStatus;
    @SerializedName("prv_id")
    private int prvId;

    public Cage() {
    }

    public Cage(int cagId, String cagName, String cagType, int ctyId, int cagCapacity, int cagStatus, int prvId) {
        this.cagId = cagId;
        this.cagName = cagName;
        this.cagType = cagType;
        this.ctyId = ctyId;
        this.cagCapacity = cagCapacity;
        this.cagStatus = cagStatus;
        this.prvId = prvId;
    }

    public Cage(int cagId, String cagName, String cagLocation, String cagType, int ctyId, int cagCapacity, int cagStatus, int prvId) {
        this.cagId = cagId;
        this.cagName = cagName;
        this.cagLocation = cagLocation;
        this.cagType = cagType;
        this.ctyId = ctyId;
        this.cagCapacity = cagCapacity;
        this.cagStatus = cagStatus;
        this.prvId = prvId;
    }

    public int getCagId() {
        return cagId;
    }

    public void setCagId(int cagId) {
        this.cagId = cagId;
    }

    public String getCagName() {
        return cagName;
    }

    public void setCagName(String cagName) {
        this.cagName = cagName;
    }

    public String getCagLocation() {
        return cagLocation;
    }

    public void setCagLocation(String cagLocation) {
        this.cagLocation = cagLocation;
    }

    public String getCagType() {
        return cagType;
    }

    public void setCagType(String cagType) {
        this.cagType = cagType;
    }

    public int getCtyId() {
        return ctyId;
    }

    public void setCtyId(int ctyId) {
        this.ctyId = ctyId;
    }

    public int getCagCapacity() {
        return cagCapacity;
    }

    public void setCagCapacity(int cagCapacity) {
        this.cagCapacity = cagCapacity;
    }

    public int getCagStatus() {
        return cagStatus;
    }

    public void setCagStatus(int cagStatus) {
        this.cagStatus = cagStatus;
    }

    public int getPrvId() {
        return prvId;
    }

    public void setPrvId(int prvId) {
        this.prvId = prvId;
    }
}
