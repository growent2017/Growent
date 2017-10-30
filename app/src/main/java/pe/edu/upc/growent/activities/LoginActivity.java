package pe.edu.upc.growent.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okio.Source;
import pe.edu.upc.growent.R;
import pe.edu.upc.growent.models.User;

public class LoginActivity extends AppCompatActivity {

    TextView signUpTextView;
    TextView forgotTextView;
    Button loginTextView;
    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    List<User> users;
    User user2;
    String email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        loginTextView = (Button) findViewById(R.id.loginButton);
        forgotTextView = (TextView) findViewById(R.id.forgotTextView);
        emailTextInputEditText = (TextInputEditText) findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*if(Patterns.EMAIL_ADDRESS.matcher(emailTextInputEditText.getText().toString()).matches() == false){
                    emailTextInputEditText.setError("Invalid email");
                    email = false;
                }else {
                    email = true;
                    emailTextInputEditText.setError(null);
                }*/
            //ANTES VA ALGO
                users = new ArrayList<>();
                user2 = new User();
                if(emailTextInputEditText.getText().toString().isEmpty())
                    alertDialog();
                else{
                    updateSources();
                    for(User us:users)
                    {
                        if(us.getEmail() == emailTextInputEditText.getText().toString())
                        {
                            Toast t = Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT);
                            t.show();
                            Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                            startActivity(intent);
                            break;
                        }
                        else {
                            Toast t = Toast.makeText(LoginActivity.this, us.getEmail(), Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
                }
           }
        });
    }
    public void alertDialog()
    {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(LoginActivity.this);
        dialogo1.setTitle("Error de Identificación");
        dialogo1.setMessage("Email o contraseña incorrecta");

        dialogo1.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });

        dialogo1.show();
    }
    private void updateSources() {
        // TODO: Update Sources from NewsApi.org
        AndroidNetworking.get("https://growent-quickv98.c9users.io/users.json")
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                          users = User.from(response.getJSONArray("users"));
                         } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });

    }

}


