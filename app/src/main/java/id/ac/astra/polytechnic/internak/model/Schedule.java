package id.ac.astra.polytechnic.internak.model;

import com.google.gson.annotations.SerializedName;

import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Schedule {
    private Integer schId;
    @SerializedName("dtcId")
    private Integer dtcId;
    @SerializedName("schStatus")
    private Integer schStatus;
    @SerializedName("schDateStart")
    private String schDateStart;
    @SerializedName("schDateEnd")
    private String schDateEnd;
    @SerializedName("schName")
    private String schName;
    @SerializedName("schJenisMakan")
    private String schJenisMakan;

    public Schedule() {
    }

    public Schedule(Integer schId, Integer dtcId, Integer schStatus, String schDateStart, String schDateEnd, String schName, String schJenisMakan) {
        this.schId = schId;
        this.dtcId = dtcId;
        this.schStatus = schStatus;
        this.schDateStart = schDateStart;
        this.schDateEnd = schDateEnd;
        this.schName = schName;
        this.schJenisMakan = schJenisMakan;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public Integer getDtcId() {
        return dtcId;
    }

    public void setDtcId(Integer dtcId) {
        this.dtcId = dtcId;
    }

    public Integer getSchStatus() {
        return schStatus;
    }

    public void setSchStatus(Integer schStatus) {
        this.schStatus = schStatus;
    }

    public String getSchDateStart() {
        return formatTime(schDateStart);
    }

    public void setSchDateStart(String schDateStart) {
        this.schDateStart = schDateStart;
    }

    public String getSchDateEnd() {
        return formatTime(schDateEnd);
    }

    public void setSchDateEnd(String schDateEnd) {
        this.schDateEnd = schDateEnd;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getSchJenisMakan() {
        return schJenisMakan;
    }

    public void setSchJenisMakan(String schJenisMakan) {
        this.schJenisMakan = schJenisMakan;
    }

    private String formatTime(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH.mm", Locale.getDefault());
        try {
            Date date = inputFormat.parse(dateTime);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateTime;
        }
    }
}
