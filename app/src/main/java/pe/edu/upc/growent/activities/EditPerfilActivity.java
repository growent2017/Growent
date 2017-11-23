package pe.edu.upc.growent.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.fragments.HomeFragment;
import pe.edu.upc.growent.fragments.SettingFragment;
import pe.edu.upc.growent.models.User;

public class EditPerfilActivity extends AppCompatActivity {
    ImageView arrowImageView;
    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText incomeEditText;
    User user;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        user = User.fromBundle(getIntent().getExtras());
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        incomeEditText = (EditText) findViewById(R.id.incomeEditText);
        nextButton = (Button) findViewById(R.id.nextButton);

        nameEditText.setText(user.getName(),TextView.BufferType.EDITABLE);
        emailEditText.setText(user.getEmail(),TextView.BufferType.SPANNABLE);
        passwordEditText.setText(user.getPassword(),TextView.BufferType.EDITABLE);
        incomeEditText.setText(String.valueOf(user.getIncome()),TextView.BufferType.EDITABLE);

        arrowImageView = (ImageView) findViewById(R.id.arrowImageView);
        arrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = user.toBundle();
                Context context = view.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setName(nameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                user.setIncome(Integer.parseInt(incomeEditText.getText().toString()));

                updateUser(user);

                Toast t2 = Toast.makeText(EditPerfilActivity.this, getString(R.string.welcome) + user.getName(), Toast.LENGTH_SHORT);
                t2.show();

                Bundle bundle = user.toBundle();
                Context context = view.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    public void updateUser(User exampleUser){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", exampleUser.getName());
            jsonObject.put("email", exampleUser.getEmail());
            jsonObject.put("password", exampleUser.getPassword());
            jsonObject.put("income", String.valueOf(exampleUser.getIncome()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.patch("https://growent2017-quickv98.c9users.io/users/{id}")
                .addPathParameter("id",String.valueOf(exampleUser.getId()))
                .addJSONObjectBody(jsonObject)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


}
