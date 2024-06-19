package id.ac.astra.polytechnic.internak.api;

import com.google.gson.annotations.SerializedName;

public class SingleObjectApiResponse<T> {
    @SerializedName("status")
    private int status;

    @SerializedName("data")
    private T data;

    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
