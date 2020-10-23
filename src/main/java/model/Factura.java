package model;

import java.util.*;

public class Factura {

    private Integer idFactura;
    private Date fechaFactura;
    private Cliente cliente;

    private Set<FacturaItem> items;



   public Factura(){

       this.items = new HashSet<FacturaItem>();
   }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<FacturaItem> getItems() {
        return items;
    }

    public void setItems(Set<FacturaItem> items) {
        this.items = items;
    }

    public void addItem(FacturaItem item){
        this.items.add(item);
    }


    public void calcularFactura(){

       Iterator<FacturaItem> it = items.iterator();
       Float sumaTotal = 0F;

       while (it.hasNext()){

           FacturaItem fac = it.next();


           Float sumaSubTotal=totalCantItem(fac);
           sumaTotal += sumaSubTotal;

         System.out.println("Articulo : " + fac.getArticulo().getDescripcion() +
                            "Precio Unidad " + fac.getArticulo().getPrecio() +
                            "Cantidad " + fac.getCantidad() +
                            "Sub Total" + sumaSubTotal );
       }

        System.out.println("\n Total : " + sumaTotal);

    }


   private Float totalCantItem(FacturaItem item){

       Float subTotal;

       subTotal = (item.getArticulo().getPrecio() * item.getCantidad());

       return subTotal;
   }

}
