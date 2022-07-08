package com.joao.ciclogo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joao.ciclogo.entidad.Rutas;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MyViewHolder> {
    private Context context;
    private List<Rutas> listaRutas = new ArrayList<>();
    public AdaptadorPersonalizado(Context context,List<Rutas> listaRutas){
        this.context = context;
        this.listaRutas = listaRutas;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View vista = inflater.inflate(R.layout.fila,parent,false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.MyViewHolder holder, int position) {
        holder.lbl_NombreRuta.setText(listaRutas.get(position).getNombre()+"");
        holder.lbl_creadorruta.setText(listaRutas.get(position).getCreador()+"");
        holder.lbl_inicio.setText("lat: " + listaRutas.get(position).getLatitud_inicio() + " lon: " + listaRutas.get(position).getLongitud_inicio() + "");
        holder.lbl_fin.setText("lat: " + listaRutas.get(position).getLatitud_final() + " lon: " + listaRutas.get(position).getLogintud_final()+"");
        if(listaRutas.get(position).getPeligrosidad().equals("Peligroso")){
            holder.img_peligrosidad.setImageResource(R.drawable.DangerZone);
        }else if(listaRutas.get(position).getPeligrosidad().equals("Regular")){
            holder.img_peligrosidad.setImageResource(R.drawable.caution_zone);
        }else {
            holder.img_peligrosidad.setImageResource(R.drawable.Segura);
        }
        holder.btnEditar.setOnClickListener(view -> {
            //Falta el editar Activity
            Intent intent = new Intent(context,EditarActivity.class);
        });
    }

    @Override
    public int getItemCount() {
        return listaRutas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView lbl_NombreRuta, lbl_creadorruta,lbl_inicio,lbl_fin;
        Button btnEditar;
        LinearLayout fila;
        ImageView img_peligrosidad;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_NombreRuta = itemView.findViewById(R.id.lbl_NombreRuta);
            lbl_creadorruta = itemView.findViewById(R.id.lbl_creadorruta);
            lbl_inicio = itemView.findViewById(R.id.lbl_inicio);
            lbl_fin = itemView.findViewById(R.id.lbl_fin);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            img_peligrosidad = itemView.findViewById(R.id.img_peligrosidad);

        }
    }
}
