package francocompany.fruitword2.Models;

/**
 * Created by PC on 30/05/2017.
 */

public class frutas {
    private String nombre;
    private String descripcion;
    private int imgFruta;
    private int cantidad;
    private int fondoFruta;

    public static final int limite_cantidad=10;
    public static final int resetear_cantidad=0;

    public frutas(){

    }
    public frutas (String nombre,String descripcion,int imgFruta,int fondoFruta,int cantidad){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imgFruta=imgFruta;
        this.cantidad=cantidad;
        this.fondoFruta=fondoFruta;
    }


    public int getCantidad() {
        return cantidad;
    }
    //Añadir cantidad
    public void añadirCantidad(int cantidad) {
        if(this.cantidad<limite_cantidad){
            this.cantidad=cantidad+1;
        }
    }
    public void resetearCantidad( ) {
        this.cantidad = resetear_cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getFondoFruta() {
        return fondoFruta;
    }

    public void setFondoFruta(int fondoFruta) {
        this.fondoFruta = fondoFruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgFruta() {
        return imgFruta;
    }

    public void setImgFruta(int imgFruta) {
        this.imgFruta = imgFruta;
    }


}
