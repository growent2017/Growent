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
import com.google.gson.reflect.TypeToken;

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
    User user;
    Boolean email;
    Boolean invalidEmail=true;
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
                if(Patterns.EMAIL_ADDRESS.matcher(emailTextInputEditText.getText().toString()).matches() == false){
                    alertDialog();
                    emailTextInputEditText.setError(getString(R.string.invalid_email));
                    email = false;
                }else {
                    email = true;
                    emailTextInputEditText.setError(null);
                    //Json
                    validateUser(emailTextInputEditText.getText().toString());
                }
            }
        });
    }

    public void alertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setTitle(R.string.authentication_error);
        dialog.setMessage(R.string.email_password_invalid);
        dialog.setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        dialog.show();
    }

    public void validateUser(final String email){
        AndroidNetworking.get("https://growent-quickv98.c9users.io/users.json")
                .setPriority(Priority.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsObjectList(User.class, new ParsedRequestListener<List<User>>() {
                    @Override
                    public void onResponse(List<User> users) {
                        // do anything with response

                        for (User user : users) {
                            if(user.getEmail().equalsIgnoreCase(email)){
                                invalidEmail = false;
                                Toast t = Toast.makeText(LoginActivity.this, "Welcome " + user.getName(), Toast.LENGTH_SHORT);
                                t.show();
                                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }

}







