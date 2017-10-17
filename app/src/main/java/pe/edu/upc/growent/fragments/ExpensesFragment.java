package pe.edu.upc.growent.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.models.MovementRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {

    EditText incomeEditText;
    EditText hourEditText;
    EditText placeEditText;
    Button acceptButton;
    public ExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        incomeEditText = (EditText) view.findViewById(R.id.incomeEditText);
        hourEditText = (EditText) view.findViewById(R.id.hourEditText);
        placeEditText = (EditText) view.findViewById(R.id.placeEditText);

        acceptButton = (Button) view.findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovementRepository.getInstance().
                addMovement(hourEditText.getText().toString(),
                        incomeEditText.getText().toString(),placeEditText.getText().toString());
                clearFields();
            }
        });
        return view;
    }
    private void clearFields(){
        incomeEditText.setText("");
        hourEditText.setText("");
        placeEditText.setText("");
    }
}
