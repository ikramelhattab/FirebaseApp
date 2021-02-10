package com.example.firebaseapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextInputEditText ednom,edmp;
    Button btnconn;
    TextView tvnew;
    String nom,mp;
     FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth =FirebaseAuth.getInstance();
        //Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       ednom =findViewById(R.id.ednom_auth);
       edmp =findViewById(R.id.edpwd_auth);
       btnconn =findViewById(R.id.btnconnect_auth);
       tvnew =findViewById(R.id.tv_new_auth);


       tvnew.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //creation d'un compte
               nom=ednom.getText().toString();
               mp=edmp.getText().toString();


               if(! nomIsValide()){
                   ednom.setError("Email non valide");
               }
               else {
                   if(! mpIsValide()){
                       ednom.setError("mot de passe non valide");

                   }
                   else {
                       mauth.createUserWithEmailAndPassword(nom,mp)
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()){
                            Intent i =new Intent(MainActivity.this,Acceuil.class);
                            startActivity(i);
                                  }
                                  else {
                                      Toast.makeText(MainActivity.this,"Erreur creation utilisateur",Toast.LENGTH_SHORT).show();
                                  }
                                   }
                               });


                   }
               }

           }
       });
        btnconn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //sign in
        nom=ednom.getText().toString();
        mp=edmp.getText().toString();


      if(! nomIsValide()){
    ednom.setError("Email non valide");
      }
      else {
    if(! mpIsValide()){
        ednom.setError("mot de passe non valide");

    }
    else {
        mauth.signInWithEmailAndPassword(nom,mp)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i =new Intent(MainActivity.this,Acceuil.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Erreur Connexion Utilisateur",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}

    }







        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AjoutProfil.class));

            }
        });
    }

    private boolean mpIsValide() {
        return mp.length()>=6;
    }

    private boolean nomIsValide() {

        return  nom.contains("@") &&
                nom.contains(".") &&
                nom.length()>5;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}