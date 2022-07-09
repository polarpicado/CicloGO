package com.joao.ciclogo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joao.ciclogo.entidad.Rutas;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MyViewHolder>{
    private Context context;
    private List<Rutas> listaRutas = new ArrayList<>();
    public AdaptadorPersonalizado(Context context, List<Rutas> listaRutas) {
        this.context = context;
        this.listaRutas = listaRutas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View vista = inflater.inflate(R.layout.fila, parent, false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.MyViewHolder holder, int position) {
          holder.lbl_NombreRuta.setText(listaRutas.get(position).getNombre());
          holder.lbl_creadorruta.setText(listaRutas.get(position).getCreador());
          holder.lbl_inicio.setText(listaRutas.get(position).getLatitud_inicio()+" | "+listaRutas.get(position).getLongitud_inicio());
          holder.lbl_fin.setText(listaRutas.get(position).getLatitud_final()+" | "+listaRutas.get(position).getLongitud_final());
          if ((listaRutas.get(position).getPeligrosidad()+"").equals("Peligrosa")) {
             holder.img_peligrosidad.setImageResource(R.drawable.danger_zone);
         }else if ((listaRutas.get(position).getPeligrosidad()+"").equals("Regular")){
             holder.img_peligrosidad.setImageResource(R.drawable.caution_zone);
         }else{
             holder.img_peligrosidad.setImageResource(R.drawable.safe);
         }
         holder.btnMapa.setOnClickListener(view -> {
             AlertDialog.Builder ventana = new AlertDialog.Builder(context);
             ventana.setTitle("Confirmación");
             ventana.setMessage("¿Desea ver el mapa de la ruta?");
            ventana.setNegativeButton("Cancelar", null);
            ventana.setPositiveButton("Aceptar", (dialog, which) -> {
                Intent intent = new Intent(context, MapaRutaActivity.class);
                intent.putExtra("platitudinicio", listaRutas.get(position).getLatitud_inicio()+"");
                intent.putExtra("plongitudinicio", listaRutas.get(position).getLongitud_inicio()+"");
                intent.putExtra("platitudfinal", listaRutas.get(position).getLatitud_final()+"");
                intent.putExtra("plongitudfinal", listaRutas.get(position).getLongitud_final()+"");
                context.startActivity(intent);
            });
            ventana.create().show();
        });
        holder.fila.setOnLongClickListener(view -> {
            Intent intent = new Intent(context, EditarActivity.class);
            intent.putExtra("pid", listaRutas.get(position).getId()+"");
            intent.putExtra("pnombre", listaRutas.get(position).getNombre()+"");
            intent.putExtra("pcreador", listaRutas.get(position).getCreador()+"");
            intent.putExtra("platitudinicio", listaRutas.get(position).getLatitud_inicio()+"");
            intent.putExtra("plongitudinicio", listaRutas.get(position).getLongitud_inicio()+"");
            intent.putExtra("platitudfinal", listaRutas.get(position).getLatitud_final()+"");
            intent.putExtra("plongitudfinal", listaRutas.get(position).getLongitud_final()+"");
            context.startActivity(intent);
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return listaRutas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lbl_NombreRuta, lbl_creadorruta, lbl_inicio, lbl_fin;
        ImageView  img_peligrosidad;
        ImageButton btnMapa;
        LinearLayout fila;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_NombreRuta = itemView.findViewById(R.id.lbl_NombreRuta);
            lbl_creadorruta = itemView.findViewById(R.id.lbl_creadorruta);
            lbl_inicio = itemView.findViewById(R.id.lbl_inicio);
            lbl_fin = itemView.findViewById(R.id.lbl_fin);
            img_peligrosidad = itemView.findViewById(R.id.img_peligrosidad);
            fila = itemView.findViewById(R.id.fila);
            btnMapa = itemView.findViewById(R.id.btnMapa);
        }
    }
}
