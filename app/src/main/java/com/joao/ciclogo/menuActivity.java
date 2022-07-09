package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joao.ciclogo.entidad.Rutas;

import java.util.ArrayList;
import java.util.List;
public class menuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView rvRutas;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Rutas> listaRutas = new ArrayList<>();
    AdaptadorPersonalizado adaptadorPersonalizado;
    private FirebaseAuth mAuth;
    boolean toMap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navview);
        toolbar = findViewById(R.id.toolbar);
        mAuth = FirebaseAuth.getInstance();
        navigationView.setNavigationItemSelectedListener(this);
        setToolBar();
        setSupportActionBar(toolbar);
        asignarReferencias();
        inicializarFirebase();
        mostrarRutas();

    }

    private void mostrarRutas() {
        databaseReference.child("Rutas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaRutas.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    Rutas ruta = item.getValue(Rutas.class);
                    listaRutas.add(ruta);
                }
                adaptadorPersonalizado = new AdaptadorPersonalizado(menuActivity.this, listaRutas);
                rvRutas.setAdapter(adaptadorPersonalizado);
                rvRutas.setLayoutManager(new LinearLayoutManager(menuActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void asignarReferencias() {

        rvRutas = findViewById(R.id.rvRutas);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                String id = listaRutas.get(pos).getId();
                listaRutas.remove(pos);
                adaptadorPersonalizado.notifyDataSetChanged();
                databaseReference.child("Rutas").child(id).removeValue();
            }

        }).attachToRecyclerView(rvRutas);
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()) {
            case R.id.menu_nueva_rutas:
                Intent intent = new Intent(menuActivity.this, CrearMapaActivity.class);
                startActivity(intent);
                break;
        }

        switch (menuItem.getItemId()) {
            case R.id.menu_mapa:
                Intent intent = new Intent(menuActivity.this, MapityActivity.class);
                intent.putExtra("latitud","-12.06085");
                intent.putExtra("longitud","-77.0464019");
                intent.putExtra("titulo","CICLO GO");
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}