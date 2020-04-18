package com.devthion.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.devthion.myapplication.Interfaces.InterfaceRetrieveDataFirebase;
import com.devthion.myapplication.modelos.Local;
import com.devthion.myapplication.modelos.SliderMenuPrincipal;

import java.util.ArrayList;
import java.util.List;

public class AdapterSliderMenuPrincipal extends PagerAdapter implements Adapter {

    private List<SliderMenuPrincipal> sliderMenuPrincipal;
    private LayoutInflater layoutInflater;
    private Context context;
    public BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();
    List<Local> locales = new ArrayList<>();

    public AdapterSliderMenuPrincipal(List<SliderMenuPrincipal> sliderMenuPrincipal, Context context) {
        this.sliderMenuPrincipal = sliderMenuPrincipal;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderMenuPrincipal.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_slider,container, false);

        ImageView imageView;
        final TextView title, descripcion;

        imageView = view.findViewById(R.id.imagen);
        title = view.findViewById(R.id.title);
        descripcion = view.findViewById(R.id.descripcion);

        imageView.setImageResource(sliderMenuPrincipal.get(position).getImage());
        title.setText(sliderMenuPrincipal.get(position).getTitle());
        descripcion.setText(sliderMenuPrincipal.get(position).getDescripcion());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {



                if(position==0){
                    busquedaDeLocalesFirebase.busquedaPorCategoria("Ropa Hombre", new InterfaceRetrieveDataFirebase() {
                        @Override
                        public void onCallBack(ArrayList<Local> locales) {
                             if(locales.isEmpty()){
                        Toast.makeText(context,"No se encontraron locales que cumplan con tu criterio", Toast.LENGTH_LONG).show();
                    }else {
                                 Intent intent=new Intent(context, LocalesSortedBy.class);
                                 intent.putExtra("variable", "Ropa Hombre");
                                 intent.putExtra("condicion", "categoria");
                                 v.getContext().startActivity(intent);
                    }
                        }
                    });


                }
                if (position==1){
                    busquedaDeLocalesFirebase.busquedaPorCategoria("Ropa Niños", new InterfaceRetrieveDataFirebase() {
                        @Override
                        public void onCallBack(ArrayList<Local> locales) {
                            if(locales.isEmpty()){
                                Toast.makeText(context,"No se encontraron locales que cumplan con tu criterio", Toast.LENGTH_LONG).show();
                            }else {
                                Intent intent=new Intent(context, LocalesSortedBy.class);
                                intent.putExtra("variable", "Ropa Niños");
                                intent.putExtra("condicion", "categoria");
                                v.getContext().startActivity(intent);
                            }
                        }
                    });

                }
                if (position==2){
                    busquedaDeLocalesFirebase.busquedaPorCategoria("Ropa Unisex", new InterfaceRetrieveDataFirebase() {
                        @Override
                        public void onCallBack(ArrayList<Local> locales) {
                            if(locales.isEmpty()){
                                Toast.makeText(context,"No se encontraron locales que cumplan con tu criterio", Toast.LENGTH_LONG).show();
                            }else {
                                Intent intent=new Intent(context, LocalesSortedBy.class);
                                intent.putExtra("variable", "Ropa Unisex");
                                intent.putExtra("condicion", "categoria");
                                v.getContext().startActivity(intent);
                            }
                        }
                    });
                }
                if (position == 3){

                }
                //v.getContext().startActivity(intent);

                //Toast.makeText(context,""+position, Toast.LENGTH_LONG).show();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }






}
