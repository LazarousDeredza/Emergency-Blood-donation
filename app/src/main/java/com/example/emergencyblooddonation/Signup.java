package com.example.emergencyblooddonation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;



import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnSuccessListener;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Signup extends AppCompatActivity {
    TextInputLayout firstnameLayout, lastnameLayout, userLayout, phoneLayout,
            userlevelLayout, emailLayout, passLayout, confirmpassLayout, addrLayout;
    EditText firstnameText, lastnameText, txtUsername,
            phoneText, emailText, passText, pass1Text, addrText;

    RadioButton radioAdmin, radioUser;

    EditText edit_text_blood_group;
    MaterialButton signupbtn;
    String regex;
    //Compile regular expression to get the pattern
    Pattern pattern;
    TextView textgroup, textsign;
    Dialog dialog;

    ArrayList<String> arrayList,arrayList1;

    String level, task = "";
    String id;
    private int mYear, mMonth, mDay;
    private ActionBar actionBar;


    CircleImageView imgProfile;
    TextView txtCompanyLogo;
    DatabaseReference reference,reference2;

    //Compile regular expression to get the pattern

    String sImage;

    private static final int REQUEST_OPEN_IMAGE_SELECT_DIALOGUE= 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar=getSupportActionBar();
        assert actionBar != null;

        actionBar.setTitle("Register");
        setContentView(R.layout.activity_signup);

        firstnameLayout = (TextInputLayout) findViewById(R.id.firstnameLayout);
        lastnameLayout = (TextInputLayout) findViewById(R.id.lastnameLayout);
        phoneLayout = (TextInputLayout) findViewById(R.id.phoneLayout);
        emailLayout = (TextInputLayout) findViewById(R.id.emailLayout);
        passLayout = (TextInputLayout) findViewById(R.id.passLayout);
        confirmpassLayout = (TextInputLayout) findViewById(R.id.confirmpassLayout);
        addrLayout = (TextInputLayout) findViewById(R.id.addrLayout);


        firstnameText = (EditText) findViewById(R.id.firstnameText);
        lastnameText = (EditText) findViewById(R.id.lastnameText);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        phoneText = (EditText) findViewById(R.id.phoneText);
        emailText = (EditText) findViewById(R.id.emailText);
        passText = (EditText) findViewById(R.id.passText);
        pass1Text = (EditText) findViewById(R.id.pass1Text);
        addrText = (EditText) findViewById(R.id.addrText);

        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        edit_text_blood_group = findViewById(R.id.edit_text_blood_group);

        textgroup = findViewById(R.id.textGroup);
        textsign = findViewById(R.id.textSign);

        addBloodGroupsToDropDown();
        addBloodSignsToDropDown();



        imgProfile=findViewById(R.id.changeProfile1);
        txtCompanyLogo=findViewById(R.id.changeProfile2);
        sImage=null;




        regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        pattern = Pattern.compile(regex);



        textgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialise Dialog
                dialog = new Dialog(Signup.this);

                //Set Custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);


                //Set Custom Heigth and Width
                dialog.getWindow().setLayout(650, 800);

                //set Transparent Background

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                //show Dialog
                dialog.show();

                //Initialise Dialog Components

                EditText editText = dialog.findViewById(R.id.edit_text_blood_group);
                ListView listView = dialog.findViewById(R.id.list_view);

                //Initialise Adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        Signup.this, android.R.layout.simple_list_item_1, arrayList);

                listView.setAdapter(adapter);

                //Set Listener
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Filter the list
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        //Filter the list
                    }
                });

                //Set Listener
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Get the selected item
                        String selectedItem = arrayList.get(position);

                        //Set the selected item to the text view
                        textgroup.setText(selectedItem);

                        //Close the dialog
                        dialog.dismiss();
                    }


                });
            }
        });
        textsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialise Dialog
                dialog = new Dialog(Signup.this);

                //Set Custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner2);


                //Set Custom Heigth and Width
                dialog.getWindow().setLayout(650, 800);

                //set Transparent Background

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                //show Dialog
                dialog.show();

                //Initialise Dialog Components

                EditText editText = dialog.findViewById(R.id.edit_text_blood_group);
                ListView listView = dialog.findViewById(R.id.list_view);

                //Initialise Adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        Signup.this, android.R.layout.simple_list_item_1, arrayList1);

                listView.setAdapter(adapter);

                //Set Listener
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Filter the list
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        //Filter the list
                    }
                });

                //Set Listener
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Get the selected item
                        String selectedItem = arrayList1.get(position);

                        //Set the selected item to the text view
                        textsign.setText(selectedItem);

                        //Close the dialog
                        dialog.dismiss();
                    }


                });
            }
        });


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if permission to read storage
                if(ContextCompat.checkSelfPermission(Signup.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    //When permission not granted , Request
                    ActivityCompat.requestPermissions(Signup.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                }else{
                    //When permission is granted

                    selectImage();
                }
            }
        });
        txtCompanyLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if permission to read storage
                if(ContextCompat.checkSelfPermission(Signup.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    //When permission not granted , Request
                    ActivityCompat.requestPermissions(Signup.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                }else{
                    //When permission is granted

                    selectImage();
                }
            }
        });

        //reference = FirebaseDatabase.getInstance().getReference().child("Students");
    }

    private void selectImage() {
        //Clear previous data
        imgProfile.setImageBitmap(null);
        //initialise intent
        Intent intent= new Intent(Intent.ACTION_GET_CONTENT);

        //Set type
        intent.setType("image/*");


        startActivityForResult(Intent.createChooser(intent,"Select Image"),
                100);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Check condition
        if(requestCode==100 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //when permission granted
            selectImage();
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check condition
        if(requestCode==100&&resultCode==RESULT_OK&&data!=null){
            //when result is ok ,initialize uri
            Uri uri =data.getData();



            try {
                //initialize bitmap
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                //set image view to the selected image
                imgProfile.setImageBitmap(bitmap);
                //initialize byte array stream
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                //compress bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                //initialize byte array
                byte [] bytes = stream.toByteArray();

                //get base 64 encoded String

                sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
                //Set Encoded String to textView
                //Toast.makeText(AddObject.this, sImage, Toast.LENGTH_SHORT).show();



            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }



    //Funtion to decode string back to image

    private void decode(){
        //initialise byte array from encoded string
        byte [] bytes=Base64.decode(sImage,Base64.DEFAULT);

        //Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        //Set bitmap on image view
        imgProfile.setImageBitmap(bitmap);
    }



    private void addBloodGroupsToDropDown() {
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("AB");
        arrayList.add("O");


    }
    private void addBloodSignsToDropDown() {
        arrayList1.add("-");
        arrayList1.add("+");


    }
}