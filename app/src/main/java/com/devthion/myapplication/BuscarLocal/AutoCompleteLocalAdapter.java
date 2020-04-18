package com.devthion.myapplication.BuscarLocal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteLocalAdapter extends ArrayAdapter<CadenaPorLocal> {
    private List<CadenaPorLocal> localListFull;

    public AutoCompleteLocalAdapter(@NonNull Context context, @NonNull ArrayList<CadenaPorLocal> localList) {
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

        CadenaPorLocal localItem = getItem(position);

        if(localItem!=null){
            etNombreLocal.setText(localItem.getNombreLocal());
        }

        return convertView;
    }

    private Filter localFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence contrains) {
            FilterResults result = new FilterResults();
            ArrayList<CadenaPorLocal> suggestion = new ArrayList<>();

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
            addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((CadenaPorLocal) resultValue).getNombreLocal();
        }
    };
}
