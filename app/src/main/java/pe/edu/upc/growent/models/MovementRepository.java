package pe.edu.upc.growent.models;

import java.util.ArrayList;
import java.util.List;

public class MovementRepository {
    static MovementRepository movementRepository;
    List<Movement> movements;

    public  MovementRepository () {

        this.movements = new ArrayList<>();
        movements.add(new Movement("comida","alexn","Cafeteria"));
        movements.add(new Movement("comida","alexn","Cafeteria"));
        movements.add(new Movement("comida","alexn","Cafeteria"));
        movements.add(new Movement("comida","alexn","Cafeteria"));

    }

    public static MovementRepository getInstance() {
        if (movementRepository == null)
            movementRepository = new MovementRepository();
        return movementRepository;
    }

    public List<Movement> getMovements() {
        return movements;
    }
    //Validation
    public boolean addMovement(String hour, String movement,String place){
        if(movement.isEmpty())return false;
        movements.add(new Movement(hour,movement,place));
        return true;
    }



}
