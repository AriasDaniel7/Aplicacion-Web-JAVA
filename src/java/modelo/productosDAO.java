package modelo;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Daniel Arias
 */
public class productosDAO {
    Connection conexion;
    
    public productosDAO(){
        conexion con = new conexion();
        this.conexion = con.getConexion();
    }
    
    public ArrayList<productos> listarProductos(){
        PreparedStatement ps;
        ResultSet rs;
        
        ArrayList<productos> lista = new ArrayList<>();
        
        try {
            ps = conexion.prepareStatement("SELECT id,codigo,nombre,precio,existencia FROM productos");
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo").intern();
                String nombre = rs.getString("nombre").intern();
                Double precio = rs.getDouble("precio");
                int existencia = rs.getInt("existencia");
                
                productos producto = new productos(id, codigo, nombre, precio, existencia);
                lista.add(producto);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public productos mostrarProducto(int _id){
        PreparedStatement ps;
        ResultSet rs;
        productos producto = null;
        
        try {
            ps = conexion.prepareStatement("SELECT id,codigo,nombre,precio,existencia FROM productos WHERE id=?");
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo").intern();
                String nombre = rs.getString("nombre").intern();
                Double precio = rs.getDouble("precio");
                int existencia = rs.getInt("existencia");
                
                producto = new productos(id, codigo, nombre, precio, existencia);
            }
            return producto;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public boolean insertar(productos producto){
        PreparedStatement ps;
        
        try {
            ps = conexion.prepareStatement("INSERT INTO productos(codigo,nombre,precio,existencia) VALUES (?,?,?,?)");
            ps.setString(1,producto.getCodigo());
            ps.setString(2,producto.getNombre());
            ps.setDouble(3,producto.getPrecio());
            ps.setInt(4,producto.getExistencia());
            ps.execute();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean actualizar(productos producto){
        PreparedStatement ps;
        
        try {
            ps = conexion.prepareStatement("UPDATE productos SET codigo=?,nombre=?,precio=?,existencia=? WHERE id=?");
            
            ps.setString(1,producto.getCodigo());
            ps.setString(2,producto.getNombre());
            ps.setDouble(3,producto.getPrecio());
            ps.setInt(4,producto.getExistencia());
            ps.setInt(5,producto.getId());
            ps.execute();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean eliminar(int _id){
        PreparedStatement ps;
        
        try {
            ps = conexion.prepareStatement("DELETE FROM productos WHERE id=?");
            ps.setInt(1,_id);
            ps.execute();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    
}
