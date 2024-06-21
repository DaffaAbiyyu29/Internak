package id.ac.astra.polytechnic.internak.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TabUser {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("usr_id")
    @Expose
    private Integer usr_id;

    @SerializedName("usr_fullname")
    @Expose
    private String usr_fullname;

    @SerializedName("usr_phone")
    @Expose
    private String usr_phone;

    @SerializedName("usr_email")
    @Expose
    private String usr_email;

    @SerializedName("usr_username")
    @Expose
    private String usr_username;

    @SerializedName("usr_password")
    @Expose
    private String usr_password;

    @SerializedName("usr_position")
    @Expose
    private String usr_position;

    @SerializedName("usr_status")
    @Expose
    private Integer usr_status;

    @SerializedName("cty_id")
    @Expose
    private Integer cty_id;

    public TabUser() {
    }

    public TabUser(Integer status, String message, Integer usr_id, String usr_fullname, String usr_phone, String usr_email, String usr_username, String usr_password, String usr_position, Integer usr_status, Integer cty_id) {
        this.status = status;
        this.message = message;
        this.usr_id = usr_id;
        this.usr_fullname = usr_fullname;
        this.usr_phone = usr_phone;
        this.usr_email = usr_email;
        this.usr_username = usr_username;
        this.usr_password = usr_password;
        this.usr_position = usr_position;
        this.usr_status = usr_status;
        this.cty_id = cty_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(Integer usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsr_fullname() {
        return usr_fullname;
    }

    public void setUsr_fullname(String usr_fullname) {
        this.usr_fullname = usr_fullname;
    }

    public String getUsr_phone() {
        return usr_phone;
    }

    public void setUsr_phone(String usr_phone) {
        this.usr_phone = usr_phone;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_username() {
        return usr_username;
    }

    public void setUsr_username(String usr_username) {
        this.usr_username = usr_username;
    }

    public String getUsr_password() {
        return usr_password;
    }

    public void setUsr_password(String usr_password) {
        this.usr_password = usr_password;
    }

    public String getUsr_position() {
        return usr_position;
    }

    public void setUsr_position(String usr_position) {
        this.usr_position = usr_position;
    }

    public Integer getUsr_status() {
        return usr_status;
    }

    public void setUsr_status(Integer usr_status) {
        this.usr_status = usr_status;
    }

    public Integer getCty_id() {
        return cty_id;
    }

    public void setCty_id(Integer cty_id) {
        this.cty_id = cty_id;
    }
}
