package pe.edu.upc.growent.models;

import android.os.Bundle;

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
    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("hour",hour);
        bundle.putString("movement",movement);
        bundle.putString("place",place);
        return bundle;
    }
    public static Movement fromBundle(Bundle bundle){
        Movement movement = new Movement();
        return movement.setHour(bundle.getString("hour"))
                .setMovement(bundle.getString("movement"))
                .setPlace(bundle.getString("place"));
    }
}
