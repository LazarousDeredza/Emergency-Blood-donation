package com.example.emergencyblooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView textView;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    Animation fadein, rotatefromright,rotatefromleft;
    ImageView itext, ilogo1,ilogo2;
    EditText txtUsername, txtPassword;
    Button btnLogin;
    TextView register, forgotPassword, r;
    String userLevel;




    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        actionBar = getSupportActionBar();
        actionBar.hide();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users1");

        forgotPassword = findViewById(R.id.forgotPassword);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        register = findViewById(R.id.register);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);
            }
        });


//        txtUsername.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txtUsername.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//            }
//        });


//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        reference.child("new").setValue("testing");
//                        Toast.makeText(Login.this, "Done", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });


    }


    @Override
    public void onStart() {
        super.onStart();
        itext = (ImageView) findViewById(R.id.imageView3);
        ilogo1 = (ImageView) findViewById(R.id.imageView2);
        ilogo2 = (ImageView) findViewById(R.id.imageView2);
        fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        rotatefromright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotrightin);

        itext.startAnimation(fadein);
        ilogo1.startAnimation(rotatefromright);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);


    }
}