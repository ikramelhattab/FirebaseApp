package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AjoutProfil extends AppCompatActivity {
    private Button btnval;
    private EditText ednom,edprenom,edadr;


    FirebaseDatabase mabase;
    DatabaseReference reference_msg,reference_personnes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_profil);

         mabase=FirebaseDatabase.getInstance();

         reference_msg=mabase.getReference("message");


        reference_msg.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String msg = dataSnapshot.getValue(String.class);
                 Toast.makeText(AjoutProfil.this,"Nouvelle modification " +msg,Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


        btnval=findViewById(R.id.btncalider_acc);
        ednom=findViewById(R.id.ednom_acc);
        edprenom=findViewById(R.id.edprenom_acc);
        edadr=findViewById(R.id.edadr_acc);

        reference_personnes=mabase.getReference("Personnes");

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = ednom.getText().toString();
                String p = edprenom.getText().toString();
                String a = edadr.getText().toString();

                String cle=reference_personnes.push().getKey();
                reference_personnes.child(cle).setValue(new Personne(n,p,a));
            }
        });
    }
}