package com.example.cse227_ca1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText email, password;
    EditText confirmpassword;
    Button b1_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        b1_register=findViewById(R.id.b1_register);
progressBar=findViewById(R.id.progressbar);
        firebaseAuth = FirebaseAuth.getInstance();
        b1_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString().trim();
                String password1 =password.getText().toString().trim();
                String confirmpassword1= confirmpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email1))
                {
                    Toast.makeText(Register.this,"please enter your email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password1))
                {
                    Toast.makeText(Register.this,"please enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword1))
                {
                    Toast.makeText(Register.this,"please enter your confirmpasword",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password1.length()<8){
                    Toast.makeText(Register.this,"Password too short",Toast.LENGTH_SHORT).show();
                }

               progressBar.setVisibility(View.VISIBLE);
                if(password1.equals(confirmpassword1)){
                    firebaseAuth.createUserWithEmailAndPassword(email1, password1)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(Register.this,MainActivity.class));
                           Toast.makeText(Register.this,"Regitration succesful",Toast.LENGTH_SHORT).show();
                           if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                               Toast.makeText(Register.this,"LOgin user",Toast.LENGTH_SHORT).show();
                           }
                        } else {
                            Toast.makeText(Register.this,"Authenication falied",Toast.LENGTH_SHORT).show();


                        }


                    }
                });

                }
            }
        });


    }
}