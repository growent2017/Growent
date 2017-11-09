package pe.edu.upc.growent.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.models.User;

public class IncomeActivity extends AppCompatActivity {

    TextView nextButton;
    EditText incomeEditText;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        user = User.fromBundle(getIntent().getExtras());
        nextButton = (TextView) findViewById(R.id.nextButton);
        incomeEditText = (EditText) findViewById(R.id.incomeEditText);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(IncomeActivity.this, HomeActivity.class);
                //startActivity(intent);
                if(incomeEditText.getText().length() <= 2) {
                    alertDialog();
                }
                else{

                    int income =Integer.parseInt(incomeEditText.getText().toString());
                    user.setIncome(income);
                    addIncome(user);
                    Bundle bundle = user.toBundle();
                    Context context = view.getContext();
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            }
        });
    }
    public void alertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(IncomeActivity.this);
        dialog.setTitle("We want to know you better");
        dialog.setMessage("Please, aggregate a month income");
        dialog.setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        dialog.show();
    }
    public void addIncome(User exampleUser){

        AndroidNetworking.put("https://growent-quickv98.c9users.io/users/id={id}?name={name}&email={email}&password={password}&income={income}")
                .addPathParameter("id", String.valueOf(10))
                .addPathParameter("name", "ALEXANDER")
                .addPathParameter("email", exampleUser.getEmail())
                .addPathParameter("password",exampleUser.getPassword())
                .addPathParameter("income", String.valueOf(13))
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                Toast t = Toast.makeText(IncomeActivity.this, user.getName() + user.getEmail(), Toast.LENGTH_SHORT);
                                t.show();
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                            }
                        });
    }
}

