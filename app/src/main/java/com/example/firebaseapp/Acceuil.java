package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Acceuil extends AppCompatActivity {

ArrayList<Personne> data = new ArrayList<Personne>();
ListView lv;

DatabaseReference ref_personne;
FirebaseDatabase mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        mbase=FirebaseDatabase.getInstance();
        ref_personne=mbase.getReference("Personnes");
         lv= findViewById(R.id.lv);

         ref_personne.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 data.clear();
                 for (DataSnapshot ligne:snapshot.getChildren())
                 {
                     Personne p= ligne.getValue(Personne.class);
                     data.add(p);

                 }
                 lv.setAdapter(new ArrayAdapter(Acceuil.this,
                         android.R.layout.simple_list_item_1,
                         data));
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
    }
}