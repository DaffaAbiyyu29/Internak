package id.ac.astra.polytechnic.internak.ui.cage;

public class Cage {
    private String title;
    private String date;
    private String location;
    private int qty;
    private int temperature;
    private int amonia;

    public Cage() {
    }

    public Cage(String title, String date, String location, int qty, int temperature, int amonia) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.qty = qty;
        this.temperature = temperature;
        this.amonia = amonia;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getAmonia() {
        return amonia;
    }

    public void setAmonia(int amonia) {
        this.amonia = amonia;
    }
}
