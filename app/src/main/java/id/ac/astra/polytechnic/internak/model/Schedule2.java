package id.ac.astra.polytechnic.internak.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Schedule2 {
    @SerializedName("sch_id")
    private Integer schId;
    @SerializedName("dtc_id")
    private Integer dtcId;
    @SerializedName("sch_status")
    private Integer schStatus;
    @SerializedName("sch_datestart")
    private LocalDateTime schDateStart;
    @SerializedName("sch_dateend")
    private LocalDateTime schDateEnd;
    @SerializedName("sch_name")
    private String schName;
    @SerializedName("sch_jenismakan")
    private String schJenisMakan;

    public Schedule2() {
    }

    public Schedule2(Integer schId, Integer dtcId, Integer schStatus, LocalDateTime schDateStart, LocalDateTime schDateEnd, String schName, String schJenisMakan) {
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

    public LocalDateTime getSchDateStart() {
        return schDateStart;
    }

    public void setSchDateStart(LocalDateTime schDateStart) {
        this.schDateStart = schDateStart;
    }

    public LocalDateTime getSchDateEnd() {
        return schDateEnd;
    }

    public void setSchDateEnd(LocalDateTime schDateEnd) {
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
}
