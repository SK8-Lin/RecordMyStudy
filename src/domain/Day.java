package domain;

public class Day {
    private String date;
    private float time;
    private float timeAll;

    public Day() {
    }

    public Day(String date, float time, float timeAll) {
        this.date = date;
        this.time = time;
        this.timeAll = timeAll;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getTimeAll() {
        return timeAll;
    }

    public void setTimeAll(float timeAll) {
        this.timeAll = timeAll;
    }

    @Override
    public String toString() {
        return "Day{" +
                "date='" + date + '\'' +
                ", time=" + time +
                ", timeAll=" + timeAll +
                '}';
    }
}
