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

import java.util.Date;
import java.util.List;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.adapters.MiniMovementAdapter;
import pe.edu.upc.growent.adapters.MovementAdapter;
import pe.edu.upc.growent.models.Movement;
import pe.edu.upc.growent.models.MovementRepository;
import pe.edu.upc.growent.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    TextView numberDayTextView;
    TextView dayTextView;
    TextView monthTextView;
    TextView dataIncomeTextView;
    RecyclerView movementRecyclerView;
    MiniMovementAdapter miniMovementAdapter;
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
        Date date = new Date();
        Integer month = date.getMonth() + 1;
        Integer day = date.getDate() ;
        Integer dayofweek = date.getDay();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        user = User.fromBundle(getActivity().getIntent().getExtras());
        movementRecyclerView = view.findViewById(R.id.homeRecyclerView);
        miniMovementAdapter = new MiniMovementAdapter(repository.getInstance().getMovements());
        movementLayoutManager = new GridLayoutManager(this.getContext(), 1);
        movementRecyclerView.setAdapter(miniMovementAdapter);
        movementRecyclerView.setLayoutManager(movementLayoutManager);
        dataIncomeTextView = (TextView) view.findViewById(R.id.dataIncomeTextView);
        monthTextView = (TextView) view.findViewById(R.id.monthTextView);
        dayTextView = (TextView) view.findViewById(R.id.dayTextView);
        numberDayTextView = (TextView) view.findViewById(R.id.numberDayTextView);
        dataIncomeTextView.setText("S/. " + String.valueOf(user.getIncome()));
        monthTextView.setText(getMonth(month));
        dayTextView.setText(getWeek(dayofweek));
        numberDayTextView.setText(getDay(day));


        return view;
    }

    private String getMonth(int month) {
        switch (month) {
            case 1:
                return "Enero 2017";
            case 2:
                return "Febrero 2017";

            case 3:
                return "Marzo 2017";

            case 4:
                return "Abril 2017";

            case 5:
                return "Mayo 2017";

            case 6:
                return "Junio 2017";

            case 7:
                return "Julio 2017";

            case 8:
                return "Agosto 2017";

            case 9:
                return "Septiembre 2017";

            case 10:
                return "Octubre 2017";

            case 11:
                return "Noviembre 2017";

            case 12:
                return "Diciembre 2017";
        }
        return "";
    }
    private String getWeek(int num) {
        switch (num) {
            case 1:
                return "Lunes";
            case 2:
                return "Martes";

            case 3:
                return "Miércoles";

            case 4:
                return "Jueves";

            case 5:
                return "Viernes";

            case 6:
                return "Sabado";

            case 7:
                return "Domingo";
        }
        return "";
    }
    private String getDay(int day) {
        if (day<9)
            return "0"+ String.valueOf(day);
        return String.valueOf(day);
    }
}


