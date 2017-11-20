package pe.edu.upc.growent.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.adapters.MovementAdapter;
import pe.edu.upc.growent.models.Movement;
import pe.edu.upc.growent.models.MovementRepository;
import pe.edu.upc.growent.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    TextView dataIncomeTextView;
    RecyclerView movementRecyclerView;
    MovementAdapter movementAdapter;
    RecyclerView.LayoutManager movementLayoutManager;
    List<Movement> movements;
    MovementRepository repository;
    User user;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home, container, false);
        user = User.fromBundle(getActivity().getIntent().getExtras());
        movementRecyclerView  = view.findViewById(R.id.homeRecyclerView);
        movementAdapter = new MovementAdapter(repository.getInstance().getMovements());
        movementLayoutManager = new GridLayoutManager(this.getContext(),1);
        movementRecyclerView.setAdapter(movementAdapter);
        movementRecyclerView.setLayoutManager(movementLayoutManager);
        dataIncomeTextView = (TextView) view.findViewById(R.id.dataIncomeTextView);
        dataIncomeTextView.setText(String.valueOf(user.getIncome()));

        return view;
    }

}
