package id.ac.astra.polytechnic.internak.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Province {
    @SerializedName("prv_id")
    private int prvId;
    @SerializedName("prv_name")
    private String prvName;

    public Province(int prvId, String prvName) {
        this.prvId = prvId;
        this.prvName = prvName;
    }

    public int getPrvId() {
        return prvId;
    }

    public void setPrvId(int prvId) {
        this.prvId = prvId;
    }

    public String getPrvName() {
        return prvName;
    }

    public void setPrvName(String prvName) {
        this.prvName = prvName;
    }

    @NonNull
    @Override
    public String toString() {
        return prvName;
    }
}
