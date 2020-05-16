package com.devthion.myapplication.BuscarLocal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteLocalAdapter extends ArrayAdapter<CadenaPorLocal> implements View.OnClickListener {
    private List<CadenaPorLocal> localListFull;
    private View.OnClickListener listener;

    public AutoCompleteLocalAdapter(@NonNull Context context, @NonNull List<CadenaPorLocal> localList) {
        super(context,0, localList);
        localListFull = new ArrayList<>(localList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return localFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null ){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.local_autocomplete_row,parent,false);
        }

        TextView etNombreLocal = convertView.findViewById(R.id.etNombreBusquedaLocal);
        TextView etDireccion = convertView.findViewById(R.id.etDireccionLocal);
        TextView etNumero = convertView.findViewById(R.id.etNuevoLocal);



        CadenaPorLocal localItem = getItem(position);

        if(localItem!=null){
            etNombreLocal.setText(localItem.getNombreLocal());
            etDireccion.setText(localItem.getDireccion().getCalle());
            //etNumero.setText(localItem.getDireccion().getCalle());

        }

        return convertView;
    }

    private Filter localFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence contrains) {
            FilterResults result = new FilterResults();
            List<CadenaPorLocal> suggestion = new ArrayList<>();

            if(contrains == null || contrains.length() == 0){
                suggestion.addAll(localListFull);
            }else {
                String filterPattern = contrains.toString().toLowerCase().trim();

                for(CadenaPorLocal item : localListFull){
                    if(item.getCadenaBusqueda().toLowerCase().contains(filterPattern)){
                        suggestion.add(item);
                    }
                }

            }

            result.values = suggestion;
            result.count = suggestion.size();

            return result;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {

            return ((CadenaPorLocal) resultValue).getNombreLocal();
        }
    };


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
}
