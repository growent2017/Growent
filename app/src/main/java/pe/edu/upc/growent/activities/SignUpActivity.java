package pe.edu.upc.growent.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import pe.edu.upc.growent.models.User;

public class SignUpActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    Button nextButton;

    User user = new User();
    Boolean emailInvalid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameEditText= (EditText) findViewById(R.id.nameEditText);
        emailEditText= (EditText) findViewById(R.id.emailEditText);
        passwordEditText= (EditText) findViewById(R.id.passwordEditText);
        nextButton = (Button) findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches() == false){

                    emailEditText.setError(getString(R.string.invalid_email));
                    emailInvalid = false;
                }else {
                    emailInvalid = true;
                    emailEditText.setError(null);

                    user.setName(nameEditText.getText().toString());
                    user.setEmail(emailEditText.getText().toString());
                    user.setPassword(passwordEditText.getText().toString());

                    confirmData(nameEditText.getText().toString(),emailEditText.getText().
                            toString(),passwordEditText.getText().toString());

                }

            }
        });

    }
    public void createUser(String name, String email,String password){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("income", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("https://growent-quickv98.c9users.io/users")
                .addJSONObjectBody(jsonObject)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
    public void confirmData(final String name, final String email, final String password) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SignUpActivity.this);
        dialog.setTitle(R.string.confirm_your_information);
        dialog.setMessage(getString(R.string.name_text)+": " + name + "\n"+getString(R.string.e_mail)+": " + email);
        dialog.setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast t = Toast.makeText(SignUpActivity.this, R.string.thanks_for_registering, Toast.LENGTH_SHORT);
                t.show();
                createUser(name,email,password);
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        dialog.show();
    }

}
