package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joao.ciclogo.entidad.Rutas;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    RecyclerView rv_Rutas;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Rutas> listaRutas = new ArrayList<>();
    AdaptadorPersonalizado adaptadorPersonalizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        asignarReferencias();
        inicializarFirebase();
        mostrarRutas();
    }
    private void mostrarRutas(){
        databaseReference.child("Ruta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaRutas.clear();
                for (DataSnapshot item: snapshot.getChildren()){
                    Rutas r = item.getValue(Rutas.class);
                    listaRutas.add(r);
                }
                adaptadorPersonalizado = new AdaptadorPersonalizado(ListarActivity.this,listaRutas);
                rv_Rutas.setAdapter(adaptadorPersonalizado);
                rv_Rutas.setLayoutManager(new LinearLayoutManager(ListarActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void asignarReferencias(){
        rv_Rutas = findViewById(R.id.rv_Rutas);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos  = viewHolder.getAdapterPosition();
                String id = listaRutas.get(pos).getId();
                listaRutas.remove(pos);
                adaptadorPersonalizado.notifyDataSetChanged();
                databaseReference.child("Ruta").child(id).removeValue();
            }
        }).attachToRecyclerView(rv_Rutas);
    }
}