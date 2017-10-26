package pe.edu.upc.growent.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.fragments.HomeFragment;
import pe.edu.upc.growent.fragments.SettingFragment;
public class EditPerfilActivity extends AppCompatActivity {
    ImageView arrowImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_perfil);
        arrowImageView = (ImageView) findViewById(R.id.arrowImageView) ;
        arrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPerfilActivity.this, SettingFragment.class);
                startActivity(intent);
            }
        });
    }
}
