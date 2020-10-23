package daos.impl;

import daos.ClienteDAO;
import model.Cliente;

import java.sql.*;

public class ClienteDAOImp implements ClienteDAO {

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
     * @param cliente
     */

    public void insert(Cliente cliente) {
        Connection conn = this.getConnection();
        try{
            // Chequear nombres de la base de datos
            String consulta="insert into Cliente (id, nombre, apellido) values(" +
                    cliente.id() + "," +
                    cliente.getNombre() + "," +
                    cliente.getApellido() + ")";

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
     * @param cliente
     */

    public void update(Cliente cliente) {

        Connection conn = this.getConnection();

        try{
            String consulta = "update Cliente = " +
                    "set nombre =  " + cliente.getNombre() +
                    ", apellido =  " + cliente.getApellido() +
                    " where id = " + cliente.getIdCliente();

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
            String consulta = "delete from Cliente where id = " + cliente.getIdCliente();
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
     * @return Cliente
     */

    public Cliente queryId(Integer id) {

        Connection conn = this.getConnection();
        Cliente cliente = null;

        try{
            String consulta=  "select * from Cliente  where id = " + id ;

            Statement sentencia = conn.createStatement();
            sentencia.execute(consulta);

            ResultSet rs = sentencia.getResultSet();

            if(rs.next()){
                cliente.setIdCliente(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(conn);
            return cliente;
        }
    }
}
