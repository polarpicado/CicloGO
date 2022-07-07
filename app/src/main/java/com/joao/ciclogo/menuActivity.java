package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;



import com.google.android.material.navigation.NavigationView;

public class menuActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView maNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setToolBar();
        drawerLayout = findViewById(R.id.drawer_layout);
        maNavigationView = findViewById(R.id.navview);
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        boolean FragmentoTrans= false;
        Fragment fragment=null;

        if (id == R.id.menu_nueva_rutas) {
            Intent intent = new Intent(menuActivity.this, SegrutaActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_comentar_ruta) {
            Intent intent = new Intent(menuActivity.this, SegrutaActivity.class);
            startActivity(intent);
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        DrawerLayout drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}