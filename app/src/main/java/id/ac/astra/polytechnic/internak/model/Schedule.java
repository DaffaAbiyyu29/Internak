package id.ac.astra.polytechnic.internak.model;

public class Schedule {
    private String startTime;
    private String endTime;
    private String name;
    private String description;

    public Schedule(String startTime, String endTime, String name, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
