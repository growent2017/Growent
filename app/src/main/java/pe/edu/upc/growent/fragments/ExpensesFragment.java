package pe.edu.upc.growent.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import pe.edu.upc.growent.R;
import pe.edu.upc.growent.activities.LoginActivity;
import pe.edu.upc.growent.models.MovementRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {
    private static final int SELECT_FILE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText incomeEditText;
    EditText hourEditText;
    EditText placeEditText;
    Button acceptButton;
    ImageButton cameraImageButton;
    ImageButton addPhotoImageButton;
    public ExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        incomeEditText = (EditText) view.findViewById(R.id.incomeEditText);
        hourEditText = (EditText) view.findViewById(R.id.hourEditText);
        placeEditText = (EditText) view.findViewById(R.id.placeEditText);
        cameraImageButton = (ImageButton) view.findViewById(R.id.cameraImageButton);
        addPhotoImageButton = (ImageButton) view.findViewById(R.id.addPhotoImageButton );
        acceptButton = (Button) view.findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateData()) {
                    MovementRepository.getInstance().
                            addMovement(hourEditText.getText().toString(),
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
                openGallery(view);
            }
        });
        return view;


    }
    private boolean validateData(){
        if(incomeEditText.getText().toString().isEmpty() || hourEditText.getText().toString().isEmpty() ||
                placeEditText.getText().toString().isEmpty())
            return false;
        return true;

    }
    private void clearFields(){
        incomeEditText.setText("");
        hourEditText.setText("");
        placeEditText.setText("");
    }
    public void openGallery(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Seleccione una imagen"),
                SELECT_FILE);
    }



    public void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        Uri selectedImage;

        String filePath = null;
        switch (requestCode) {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getActivity().getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista



                        }
                    }
                }
                break;
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
