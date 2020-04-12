package com.devthion.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.modelos.Local;

import java.util.List;

public class AdaptadorRecyclerViewLocalesSortedBy extends RecyclerView.Adapter<AdaptadorRecyclerViewLocalesSortedBy.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textV_nombre_local, textV_descripcion;

        public ViewHolder(View itemView){
            super(itemView);
            textV_nombre_local = itemView.findViewById(R.id.textV_nombre_local);
            textV_descripcion = itemView.findViewById(R.id.textV_descripcion);
        }
    }

    public List<Local> locals;

    public AdaptadorRecyclerViewLocalesSortedBy(List<Local> locals) {
        this.locals = locals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylcer_locales_sorted_by, parent, false);

        //TODO CREO QUE ACA IRIA EL ONCLICKLISTENER
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textV_descripcion.setText(locals.get(position).getDescripcion());
        holder.textV_nombre_local.setText(locals.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return locals.size();
    }
}