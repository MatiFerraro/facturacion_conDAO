package daos.impl;

import daos.FacturaItemDAO;
import model.FacturaItem;

import java.sql.*;

public class FacturaItemDAOImp implements FacturaItemDAO {

    private Connection getConnection(){

        Connection connection = null;

        try {
            String url = "jdbc:mysql://localhost:3306/Facturacion";
            String usuario = "root";
            String clave = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, clave);

        } catch (Exception ex){

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
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param facturaItem
     */

    public void insert(FacturaItem facturaItem) {
        Connection conn = this.getConnection();
        try{
            // Chequear idFacturas de la base de datos
            String consulta = "insert into FacturaItem (id, idArticulo, cantidad) values(" +
                    facturaItem.getIdItem() + "," +
                    facturaItem.getCantidad() + "," +
                    facturaItem.getArticulo().getId() + ")";

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
     * @param facturaItem
     */

    public void update(FacturaItem facturaItem) {

        Connection conn = this.getConnection();

        try{
            String consulta = "update FacturaItem = " +
                    "set idArticulo = " + facturaItem.getArticulo().getId() +
                    ", cantidad =  " + facturaItem.getCantidad() +
                    " where id = " + facturaItem.getIdItem();

            Statement sentencia = conn.createStatement();
            sentencia.execute(consulta);

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(conn);
        }
    }

    public void delete(Integer id) {
        Connection conn = this.getConnection();
        try{
            String consulta = "delete from FacturaItem where id = " + facturaItem.getIdItem();
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
     * @param id
     * @return FacturaItem
     */

    public FacturaItem queryId(Integer id) {

        Connection conn = this.getConnection();
        FacturaItem facturaItem = null;

        try{
            String consulta = "select * from FacturaItem where id = " + facturaItem.getIdItem() ;

            Statement sentencia = conn.createStatement();
            sentencia.execute(consulta);

            ResultSet rs = sentencia.getResultSet();

            if(rs.next()){
                facturaItem.setIdItem(rs.getInt("id"));
                facturaItem.setArticulo().setIdArticulo(rs.getInt("idArticulo"));
                facturaItem.setCantidad(rs.getInt("cantidad"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(conn);
            return facturaItem;
        }
    }
}
