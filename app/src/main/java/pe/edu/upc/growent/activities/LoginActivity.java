package pe.edu.upc.growent.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.models.User;

public class LoginActivity extends AppCompatActivity {

    TextView signUpTextView;
    TextView forgotTextView;
    Button loginTextView;
    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;

    boolean email = false, password = false;

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
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(LoginActivity.this);
                dialogo1.setTitle("Importante");
                dialogo1.show();
                /*Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                startActivity(intent);
                if(Patterns.EMAIL_ADDRESS.matcher(emailTextInputEditText.getText().toString()).matches() == false){
                    emailTextInputEditText.setError("Invalid email");
                    email = false;
                }else {
                    email = true;
                    emailTextInputEditText.setError(null);
                }

                if (email) {
                    Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }*/
            }
        });
    }

}


