package pe.edu.upc.growent.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.activities.EditPerfilActivity;
import pe.edu.upc.growent.activities.HomeActivity;
import pe.edu.upc.growent.activities.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    Button profileButton;
    Button logoutButton;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        profileButton = (Button) view.findViewById(R.id.profileButton);
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditPerfilActivity.class);
                getActivity().startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

}
