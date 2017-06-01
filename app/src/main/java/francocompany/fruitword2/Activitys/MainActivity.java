package francocompany.fruitword2.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import francocompany.fruitword2.Adaptador.Adaptador;
import francocompany.fruitword2.Models.frutas;
import francocompany.fruitword2.R;

public class MainActivity extends AppCompatActivity {

    private List<frutas> frutas;
    private RecyclerView mReciclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int contador=0;
    private int numeroDeFrutas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frutas=this.getAllFrutas();//Relleno la lista
        mReciclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager=new LinearLayoutManager(this);//creamos el layout manager
        mAdapter = new Adaptador(frutas, R.layout.recycler_view_item,this ,new Adaptador.OnItemClickListener() {
            @Override
            public void onItemClick(frutas frutas, int position) {
                //Aca que va a hacer cunado le hago click
                frutas.añadirCantidad(frutas.getCantidad());
               mAdapter.notifyDataSetChanged();
            }
        });

        mReciclerView.setHasFixedSize(true);//mejora el rendimiento sino se modf el tamaño
        mReciclerView.setItemAnimator(new DefaultItemAnimator());//Animacion

        //Seteamos el Recylcer
        mReciclerView.setLayoutManager(mLayoutManager);
        mReciclerView.setAdapter(mAdapter);//el adaptador viene de un elemnto que definimos con el adaptador propio

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menu
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.add_fruta:
               this.addFruta(frutas.size());
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
    }

    private void addFruta(int position){
        frutas.add(new frutas("Nueva Fruta"+(++contador),"La Mejor Fruta",R.mipmap.icono_frutas,R.drawable.ic_frutas,0));
        //Notificamos la modificacion
        mAdapter.notifyItemInserted(position);
        //Para volver al top de todo o la posicion
        mLayoutManager.scrollToPosition(position);
    }


    private List<frutas> getAllFrutas(){
        return new ArrayList<frutas>(){{
            add(new frutas("Naranja","La Mejor Naranja de Cordoba",R.mipmap.icono_naranja,R.drawable.ic_naranja,0));
            add(new frutas("Banana","La mejor Banana De Tucuman",R.mipmap.icono_banana,R.drawable.ic_banana,0));
            add(new frutas("Cereza","La mejor Cereza de Brazil",R.mipmap.icono_cerezas,R.drawable.ic_cerezas,0));
            add(new frutas("Pera","La mejor Pera de Mendoza",R.mipmap.icono_pera,R.drawable.ic_pera,0));
            add(new frutas("Manzana","La mejor Manzana de Buenos Aires",R.mipmap.icono_manzana,R.drawable.ic_manzana,0));
        }};
    }

}
