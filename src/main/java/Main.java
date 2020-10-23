import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Connection;
import java.util.Iterator;

public class Main {

    public static void main(String args[])  {

      //LocalDate date= LocalDate.now();
      Connection conexion;
      String url="jdbc:mysql://localhost:3306/Facturacion";
      String usuario="root";
      String clave="";
      String consulta="insert into Articulo (descripcion, precio) values('Batata', 5.9 )";

      String consulta2 ="select * from Articulo";



      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(url, usuario, clave);
        Statement sentencia = conexion.createStatement();

        sentencia.executeUpdate(consulta, Statement.RETURN_GENERATED_KEYS );
        ResultSet rs = sentencia.getGeneratedKeys();


        int key = 0;
        if(rs.next()){

          key = rs.getInt(1);
        }



       /* while (rs.next()) {
          Cliente cli = new Cliente(rs.getString("Nombre"), rs.getString("Apellido"));
        }
        rs= null;

        rs = sentencia.executeQuery(consulta2);

        Factura factura = new Factura();


        while(rs.next()){

          Articulo articulo = new Articulo();

          articulo.setDescripcion(rs.getString("descripcion"));
          articulo.setIdArticulo(rs.getInt("id"));
          articulo.setPrecio(rs.getFloat("precio"));

          FacturaItem item = new FacturaItem();

          item.setArticulo(articulo);
          item.setCantidad((short)3);

          factura.addItem(item);


        }


        Iterator<FacturaItem> it = factura.getItems().iterator();

         while (it.hasNext()){

           FacturaItem item = it.next();

           String query = "insert into FacturaItem values(cantidad,idArticulo,idFactura)" + item.getCantidad();

         }*/









        conexion.close();


      }catch (Exception ex){

        ex.printStackTrace();
      }












    }
}
