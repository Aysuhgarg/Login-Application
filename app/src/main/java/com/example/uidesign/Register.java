package com.example.uidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.jar.Attributes;

public class Register extends AppCompatActivity {

    EditText inputEmail,inputPassword,etname,Phone;
    Button btnLogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth=FirebaseAuth.getInstance();
        inputEmail=findViewById(R.id.inputEmail);
        etname=findViewById(R.id.etname);
        Phone =findViewById(R.id.Phone);
        inputPassword=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString();
                String pwd=inputPassword.getText().toString();
                String name= etname.getText().toString();
                String num=Phone.getText().toString();

                if(email.isEmpty())
                {
                    inputEmail.setError("Please Enter Email Id");
                    inputEmail.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    inputPassword.setError("Please Enter Email Id");
                    inputPassword.requestFocus();
                }
                else if(num.isEmpty())
                {
                    Phone.setError("Please Enter phone Number");
                    Phone.requestFocus();
                }
                else if(name.isEmpty())
                {
                    etname.setError("Please Enter User Name");
                    etname.requestFocus();
                }
                else
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Register.this, "SignUp fail", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(Register.this,Home.class));
                                Toast.makeText(Register.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });


    }
}
