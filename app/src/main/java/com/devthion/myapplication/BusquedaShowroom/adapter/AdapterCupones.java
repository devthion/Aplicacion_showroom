package com.devthion.myapplication.BusquedaShowroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Cupon;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCupones extends RecyclerView.Adapter<AdapterCupones.viewholdercupones> {

    List<Cupon> cuponesList ;

    public AdapterCupones(List<Cupon> cuponesList) {
        this.cuponesList = cuponesList;
    }

    @NonNull
    @Override
    public viewholdercupones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_moldear_cupon,parent,false);
        viewholdercupones holder = new viewholdercupones(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdercupones holder, int position) {
        Cupon ms = cuponesList.get(position);

        holder.etLocal.setText(ms.getIdLocal());
        holder.etDescuento.setText(ms.getDescuento());
        holder.etCodigo.setText(ms.getIdCupon());
    }

    @Override
    public int getItemCount() {
        return cuponesList.size();
    }

    public class viewholdercupones extends RecyclerView.ViewHolder {

        TextView etLocal,etCodigo, etDescuento;

        public viewholdercupones(@NonNull View itemView) {
            super(itemView);

            etLocal = itemView.findViewById(R.id.etLocal);
            etCodigo = itemView.findViewById(R.id.etCodigo);
            etDescuento = itemView.findViewById(R.id.etDescuento);



        }
    }
}
