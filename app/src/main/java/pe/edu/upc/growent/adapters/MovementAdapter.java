package pe.edu.upc.growent.adapters;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import pe.edu.upc.growent.R;
import pe.edu.upc.growent.models.Movement;
import pe.edu.upc.growent.models.MovementRepository;

public class MovementAdapter extends RecyclerView.Adapter<MovementAdapter.ViewHolder>{
    private List<Movement> movements;

    public MovementAdapter(List<Movement> movements) {
        this.setMovements(movements);
    }

    public MovementAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_expenses,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movement movement = movements.get(position);
        holder.exampleImageView.setImageResource(R.mipmap.ic_launcher);
        holder.incomeTextView.setText(movement.getMovement());
        holder.placeTextView.setText(movement.getPlace());
        holder.hourTextView.setText(movement.getHour());
    }

    @Override
    public int getItemCount() {
        return getMovements().size();
    }

    //
    public List<Movement> getMovements() {
        return movements;
    }

    public MovementAdapter setMovements(List<Movement> movements) {
        this.movements = movements;
        return this;

    }
    //View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView exampleImageView;
        TextView incomeTextView;
        TextView hourTextView;
        TextView placeTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            exampleImageView = (ImageView) itemView.findViewById(R.id.exampleImageView);
            incomeTextView = (TextView) itemView.findViewById(R.id.incomeTextView);
            hourTextView = (TextView) itemView.findViewById(R.id.hourTextView);
            placeTextView = (TextView) itemView.findViewById(R.id.placeTextView);
        }


    }
}
