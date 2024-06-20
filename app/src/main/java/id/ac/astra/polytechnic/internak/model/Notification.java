package id.ac.astra.polytechnic.internak.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notification {
    @SerializedName("ntf_id")
    private int id;

    @SerializedName("dtc_id")
    private int categoryId;

    @SerializedName("ntf_title")
    private String title;

    @SerializedName("ntf_desc")
    private String description;

    @SerializedName("ntf_type")
    private String type;

    @SerializedName("ntf_status")
    private int status;

    @SerializedName("ntf_timestamp")
    private Date timestamp;

    public Notification() {
    }

    public Notification(int id, int categoryId, String title, String description, String type, int status, Date timestamp) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
