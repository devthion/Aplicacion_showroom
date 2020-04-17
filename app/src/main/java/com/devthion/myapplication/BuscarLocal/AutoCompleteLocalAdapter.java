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

public class AutoCompleteLocalAdapter extends ArrayAdapter<Local> {
    private List<Local> localListFull;

    public AutoCompleteLocalAdapter(@NonNull Context context, @NonNull ArrayList<Local> localList) {
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

        Local localItem = getItem(position);

        if(localItem!=null){
            etNombreLocal.setText(localItem.getNombre());
        }

        return convertView;
    }

    private Filter localFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence contrains) {
            FilterResults result = new FilterResults();
            ArrayList<Local> suggestion = new ArrayList<>();

            if(contrains == null || contrains.length() == 0){
                suggestion.addAll(localListFull);
            }else {
                String filterPattern = contrains.toString().toLowerCase().trim();

                for(Local item : localListFull){
                    if(item.getNombre().toLowerCase().contains(filterPattern)){
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
            return ((Local) resultValue).getNombre();
        }
    };
}
