package com.devthion.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.modelos.Calificacion;

import java.util.List;

public class ComentariosRecyclerViewAdaptador extends RecyclerView.Adapter<ComentariosRecyclerViewAdaptador.ViewHolder> {
    public List<Calificacion> calificaciones;

    public ComentariosRecyclerViewAdaptador(List<Calificacion> calificaciones){
        this.calificaciones=calificaciones;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textV_usuario_id, textV_calificacion;
        private RatingBar ratingBarCalificacionUsuario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textV_usuario_id = itemView.findViewById(R.id.textV_usuario_id);
            this.textV_calificacion = itemView.findViewById(R.id.textV_calificacion);
            this.ratingBarCalificacionUsuario = itemView.findViewById(R.id.ratingBarCalificacionUsuario);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calificacion, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textV_calificacion.setText(calificaciones.get(position).getCalificacion());
        holder.ratingBarCalificacionUsuario.setRating(calificaciones.get(position).getEstrellas());
        holder.textV_usuario_id.setText(calificaciones.get(position).getUsuario_id());
    }

    @Override
    public int getItemCount() {
        return calificaciones.size();
    }
}
