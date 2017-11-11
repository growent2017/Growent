package pe.edu.upc.growent.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.activities.LoginActivity;
import pe.edu.upc.growent.models.MovementRepository;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {
    String mCurrentPhotoPath;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText incomeEditText;
    EditText placeEditText;
    Button acceptButton;
    ImageView walletImageView;
    ImageButton cameraImageButton;
    ImageButton addPhotoImageButton;
    EditText dateEditTex;
    private int year,month, day;
    public ExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        incomeEditText = (EditText) view.findViewById(R.id.incomeEditText);
        walletImageView = (ImageView) view.findViewById(R.id.walletImageView);
        placeEditText = (EditText) view.findViewById(R.id.placeEditText);
        cameraImageButton = (ImageButton) view.findViewById(R.id.cameraImageButton);
        addPhotoImageButton = (ImageButton) view.findViewById(R.id.addPhotoImageButton );
        dateEditTex = (EditText) view.findViewById(R.id.dateEditText);
        dateEditTex.setOnClickListener(new View.OnClickListener() {
                                           @RequiresApi(api = Build.VERSION_CODES.N)
                                           @Override
                                           public void onClick(View view) {
                                               final Calendar c = Calendar.getInstance();


                                               DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                                                   @Override
                                                   public void onDateSet(DatePicker datePicker, int yearC, int monthOfYear, int dayOfMonth) {
                                                       c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                                       c.set(Calendar.MONTH, monthOfYear);
                                                       c.set(Calendar.YEAR,yearC);

                                                       datePicker.init(yearC, monthOfYear, dayOfMonth,null);

                                                       dateEditTex.setText(dayOfMonth + " / " + monthOfYear + " / " + yearC);
                                                   }
                                               }, day, month, year);
                                               datePickerDialog.show();
                                           }
                                       });
        acceptButton = (Button) view.findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateData()) {
                    MovementRepository.getInstance().
                            addMovement(dateEditTex.getText().toString(),
                                    incomeEditText.getText().toString(), placeEditText.getText().toString());
                    clearFields();
                }
                else {
                    Toast t=Toast.makeText(getActivity(), R.string.information_invalidate, Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        cameraImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        addPhotoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryAddPic();
            }
        });
        return view;


    }
    private boolean validateData(){
        if(incomeEditText.getText().toString().isEmpty() || dateEditTex.getText().toString().isEmpty() ||
                placeEditText.getText().toString().isEmpty())
            return false;
        return true;

    }
    private void clearFields(){
        incomeEditText.setText("");
        dateEditTex.setText("");
        placeEditText.setText("");
    }
    public void galleryAddPic(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            walletImageView.setImageBitmap(imageBitmap);
        }
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            walletImageView .setImageURI(imageUri);
        }
    }
}
