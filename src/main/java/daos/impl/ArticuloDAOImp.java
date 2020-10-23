package daos.impl;

import daos.ArticuloDAO;
import model.Articulo;

import java.sql.*;

public class ArticuloDAOImp implements ArticuloDAO {


    private Connection getConnection(){

        Connection connection = null;

        try {
            String url = "jdbc:mysql://localhost:3306/Facturacion";
            String usuario = "root";
            String clave = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, clave);

        }catch (Exception ex){

            ex.printStackTrace();
        }

        return connection;
    }

    /**
     *
     * @param conn
     */

    private void closeConnection(Connection conn){

        try {
            conn.close();
        }catch (Exception ex){

            ex.printStackTrace();
        }
    }

    /**
     *
     * @param articulo
     */

    public void insert(Articulo articulo) {


        Connection conn = this.getConnection();



        try{

            String consulta="insert into Articulo (descripcion, precio) values(" +
                                                                    articulo.getDescripcion() + ","  +
                                                                    articulo.getPrecio() + ")";

            Statement sentencia = conn.createStatement();
            sentencia.execute(consulta);

        }catch (Exception ex){

            ex.printStackTrace();
        }finally {

            closeConnection(conn);
        }


    }

    /**
     *
     * @param articulo
     */

    public void update(Articulo articulo) {

        Connection conn = this.getConnection();

        try{

            String consulta=  "update  Articulo set descripcion = " + articulo.getDescripcion() +
                                              "set precio =  " + articulo.getPrecio() +
                                              " where id = " + articulo.getIdArticulo() ;

            Statement sentencia = conn.createStatement();
            sentencia.execute(consulta);

        }catch (Exception ex){

            ex.printStackTrace();
        }finally {

            closeConnection(conn);
        }


    }

    public void delete(Integer id) {

    }

    /**
     *
     * @param id
     * @return Articulo
     */

    public Articulo queryId(Integer id) {

        Connection conn = this.getConnection();
        Articulo articulo = null;

        try{

            String consulta=  "select *  from Articulo  where id = " + id ;

            Statement sentencia = conn.createStatement();
            sentencia.execute(consulta);

            ResultSet rs = sentencia.getResultSet();

            if(rs.next()){

                articulo.setIdArticulo(rs.getInt("id"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setPrecio(rs.getFloat("precio"));
            }


        }catch (Exception ex){

            ex.printStackTrace();
        }finally {

            closeConnection(conn);

            return articulo;
        }

    }
}
