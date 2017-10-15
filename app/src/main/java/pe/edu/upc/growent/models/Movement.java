package pe.edu.upc.growent.models;

/**
 * Created by Usuario on 15/10/2017.
 */

public class Movement {
    private String hour;
    private String movement;
    private String place;

    public Movement() {
    }

    public Movement(String hour, String movement, String place) {
        this.hour = hour;
        this.movement = movement;
        this.place = place;
    }

    public String getHour() {
        return hour;
    }

    public Movement setHour(String hour) {
        this.hour = hour;
        return this;
    }

    public String getMovement() {
        return movement;
    }

    public Movement setMovement(String movement) {
        this.movement = movement;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public Movement setPlace(String place) {
        this.place = place;
        return this;
    }
}
