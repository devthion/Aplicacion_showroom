package com.devthion.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecyclerViewLocalesSortedBy extends RecyclerView.Adapter<AdaptadorRecyclerViewLocalesSortedBy.ViewHolder>
implements View.OnClickListener {

    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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

        view.setOnClickListener(this);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textV_descripcion.setText(locals.get(position).getDescripcion());
        holder.textV_nombre_local.setText(locals.get(position).getNombre());


        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(, DetalleLocal.class);
                //intent.pu("unLocal", listaDeLocales.get(position));
                startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return locals.size();
    }


}
