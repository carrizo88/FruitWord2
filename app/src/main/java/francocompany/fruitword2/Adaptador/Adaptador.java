package francocompany.fruitword2.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import francocompany.fruitword2.Models.frutas;
import francocompany.fruitword2.R;

/**
 * Created by PC on 30/05/2017.
 */
//Creo la clase y la extiendo, luego implemento los metodos y despues creo la clase ViewHolder y le impremento el constructor
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {


    private List<frutas> frutas;
    private int layout;
    private OnItemClickListener listener;
    private Activity activity;

    public Adaptador(List<frutas> frutas,int layout,Activity activity,OnItemClickListener listener){
        this.frutas=frutas;
        this.layout=layout;
        this.activity=activity;
        this.listener=listener;
    }

    @Override//Inflo la vista y se la paso
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh= new ViewHolder(v);//creo el VH a utilizar
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(frutas.get(position),listener);//le pasamos el objeto con el que tenemos que trabajar
    }

    @Override//tamaño de la lista de frutas
    public int getItemCount() {
        return frutas.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView Nombre;
        public TextView Descripcion;
        public ImageView FondoFruta;
        public TextView Cantidad;

        public ViewHolder(View itemView) {
            super(itemView);
            this.Nombre=(TextView)itemView.findViewById(R.id.tvNombreFruta);
            this.Descripcion=(TextView)itemView.findViewById(R.id.tvDescripcionFruta);
            this.FondoFruta=(ImageView)itemView.findViewById(R.id.imgViewFruta);
            this.Cantidad=(TextView)itemView.findViewById(R.id.tvCantidad);
            itemView.setOnCreateContextMenuListener(this);

        }

        public void bind(final frutas Frutas,final OnItemClickListener listener){
            Nombre.setText(Frutas.getNombre());//Le paso la Fruta (se tiene que llamar igual que el nombre que le pongo no importa como se llame la clase de la que viene
            Descripcion.setText(Frutas.getDescripcion());
            Picasso.with(activity).load(Frutas.getFondoFruta()).fit().into(FondoFruta);
            Cantidad.setText(""+Frutas.getCantidad());
            if(Frutas.getCantidad()==Frutas.limite_cantidad){
                Cantidad.setTextColor(ContextCompat.getColor(activity,R.color.colorFull));
                Cantidad.setTypeface(null, Typeface.BOLD);
            }else{
                Cantidad.setTextColor(ContextCompat.getColor(activity,R.color.colorPrimary));
                Cantidad.setTypeface(null, Typeface.NORMAL);
            }

            this.FondoFruta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(Frutas,getAdapterPosition());
                }
            });
                    }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // Recogemos la posicion de metodo
            frutas frutaSelecionada= frutas.get(this.getAdapterPosition());
            //Establecemos el icono y el titulo
            menu.setHeaderTitle(frutaSelecionada.getNombre());
            menu.setHeaderIcon(frutaSelecionada.getImgFruta());
            //Inflamos el Menu y necesita el activity donde lo va a inflar!
            MenuInflater inflater= activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu,menu);
            for(int i=0; i<menu.size();i++){
                menu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            //No obtenemos el objeto info, porque la posicion la podemos
            //obtener con el getAdapterPosition y luego usar los metodos propios del adaptador

            switch (item.getItemId()){
                case R.id.delete_fruta:
                    frutas.remove(getAdapterPosition());
                  notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.resetear_cantidad:
                    frutas.get(getAdapterPosition()).resetearCantidad();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(frutas frutas,int position);

    }
}
