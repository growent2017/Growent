
package pe.edu.upc.growent.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.models.User;

public class ForgotPasswordActivity extends AppCompatActivity {
    Button findAccountButton;
    EditText emailEditText;
    Boolean foundMail=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        findAccountButton = (Button) findViewById(R.id.findAccountButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        findAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recoverAccount(emailEditText.getText().toString());

            }
        });
    }

    public void recoverAccount(final String email){
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
                                foundMail = true;
                                Toast t = Toast.makeText(ForgotPasswordActivity.this, "Welcome " + user.getName(), Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                        if(!foundMail){
                            Toast t = Toast.makeText(ForgotPasswordActivity.this, "Correo no encontrado", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }
}
