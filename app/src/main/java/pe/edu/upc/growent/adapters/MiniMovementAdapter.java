package pe.edu.upc.growent.adapters;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import pe.edu.upc.growent.R;
import pe.edu.upc.growent.models.Movement;
import pe.edu.upc.growent.models.MovementRepository;

public class MiniMovementAdapter extends RecyclerView.Adapter<MiniMovementAdapter.ViewHolder>{
    private List<Movement> movements;

    public MiniMovementAdapter(List<Movement> movements) {
        this.setMovements(movements);
    }

    public MiniMovementAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.card_mini_expenses,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movement movement = movements.get(position);
        holder.logoImageView.setImageResource(R.drawable.ic_assignment_teal_24dp);
        holder.cantTextView.setText(movement.getMovement());
        holder.nameTextView.setText(movement.getPlace());

    }

    @Override
    public int getItemCount() {
        return getMovements().size();
    }

    //
    public List<Movement> getMovements() {
        return movements;
    }

    public MiniMovementAdapter setMovements(List<Movement> movements) {
        this.movements = movements;
        return this;

    }
    //View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logoImageView;
        TextView nameTextView;
        TextView cantTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            logoImageView = (ImageView) itemView.findViewById(R.id.logoImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            cantTextView = (TextView) itemView.findViewById(R.id.cantTextView);

        }


    }
}
