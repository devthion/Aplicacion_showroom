
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Cupon extends AppCompatActivity {

    protected String idCupon;
    protected String idLocal;
    protected int descuento;
    protected int puntosNecesarios;

    public Cupon(String idCupon, String idLocal, int descuento, int puntosNecesarios) {
        this.idCupon = idCupon;
        this.idLocal = idLocal;
        this.descuento = descuento;
        this.puntosNecesarios = puntosNecesarios;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public String getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(String idCupon) {
        this.idCupon = idCupon;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(int puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }
}
