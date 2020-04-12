package com.devthion.myapplication.BuscarCupon;

import android.app.UiAutomation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Cupon;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCupones extends RecyclerView.Adapter<AdapterCupones.viewholdercupones> implements View.OnClickListener {

    List<Cupon> cuponesList ;
    private View.OnClickListener listener;

    //SE CREA EL ADAPTER CON UNA LISTA DE CUPONES
    public AdapterCupones(List<Cupon> cuponesList) {
        this.cuponesList = cuponesList;
    }

    @NonNull
    @Override
    public viewholdercupones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_moldear_cupon,parent,false);
        viewholdercupones holder = new viewholdercupones(v);

        v.setOnClickListener(this);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdercupones holder, int position) {
        Cupon cup = cuponesList.get(position);
        String descuento = String.valueOf(cup.getDescuento());
        String puntos = String.valueOf(cup.getPuntosNecesarios());

        holder.etCodigo.setText(cup.getIdCupon());
        holder.etLocal.setText(cup.getIdLocal());
        holder.eteDescuento.setText(puntos);
        holder.puntosNecesarios.setText(descuento);
    }

    @Override
    public int getItemCount() {
        return cuponesList.size();
    }

    //PARA PODER REALIZAR UN EVENTO CUANDO SE HACE CLICK
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    //PARA PODER REALIZAR UN EVENTO CUANDO SE HACE CLICK
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class viewholdercupones extends RecyclerView.ViewHolder {

        TextView etLocal,etCodigo, eteDescuento,puntosNecesarios;

        public viewholdercupones(@NonNull View itemView) {
            super(itemView);

            etLocal = itemView.findViewById(R.id.etLocal);
            etCodigo = itemView.findViewById(R.id.etCodigo);
            eteDescuento = itemView.findViewById(R.id.eteDescuento);
            puntosNecesarios = itemView.findViewById(R.id.puntosNecesarios);


        }
    }
}
