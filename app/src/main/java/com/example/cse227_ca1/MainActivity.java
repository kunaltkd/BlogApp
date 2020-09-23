package com.example.cse227_ca1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
EditText email,password;
Button login;
FirebaseAuth firebaseAuth;
ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  email =findViewById(R.id.email);
  password=findViewById(R.id.password);
  login=findViewById(R.id.login);
  progressBar=findViewById(R.id.progressbar);
        firebaseAuth=FirebaseAuth.getInstance();
  login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          String email1= email.getText().toString().trim();
          String password1=password.getText().toString().trim();
          if(TextUtils.isEmpty(email1))
          {
              Toast.makeText(MainActivity.this,"please enter your email",Toast.LENGTH_SHORT).show();
              return;
          }
          if(TextUtils.isEmpty(password1))
          {
              Toast.makeText(MainActivity.this,"please enter your password",Toast.LENGTH_SHORT).show();
              return;
          }
          if(password1.length()<8){
              Toast.makeText(MainActivity.this,"Password too short",Toast.LENGTH_SHORT).show();
          }
             progressBar.setVisibility(View.VISIBLE);
          firebaseAuth.signInWithEmailAndPassword(email1, password1)
                  .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {

                          if (task.isSuccessful()) {
                              progressBar.setVisibility(View.INVISIBLE);
                              startActivity(new Intent(MainActivity.this,App.class));
                              Toast.makeText(MainActivity.this, "Login succesful", Toast.LENGTH_SHORT).show();

                          } else {

                              Toast.makeText(MainActivity.this, "User not Account", Toast.LENGTH_SHORT).show();

                          }
                          }
                  });

      }
  });
    }

    public void myclick(View view) {
        startActivity(new Intent(MainActivity.this,Register.class));
    }
}