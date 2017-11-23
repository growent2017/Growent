package pe.edu.upc.growent.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.activities.EditPerfilActivity;
import pe.edu.upc.growent.activities.HomeActivity;
import pe.edu.upc.growent.activities.LoginActivity;
import pe.edu.upc.growent.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    Button profileButton;
    TextView nameTextView;
    Button logoutButton;
    User user;

    public SettingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        user = User.fromBundle(getActivity().getIntent().getExtras());

        profileButton = (Button) view.findViewById(R.id.profileButton);
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        nameTextView.setText(user.getName());
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = user.toBundle();
                Context context = getActivity();
                Intent intent = new Intent(context, EditPerfilActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });


        return view;
    }
 }

