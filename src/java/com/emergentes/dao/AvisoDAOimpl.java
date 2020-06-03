package com.emergentes.dao;

import com.emergentes.modelo.Aviso;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AvisoDAOimpl extends ConexionDB implements AvisoDAO {

    @Override
    public void insert(Aviso aviso) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into producto (descripcion,stock) values(?,?)");
            ps.setString(1, aviso.getDescripcion());
            ps.setInt(2, aviso.getStock());
           
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Aviso aviso) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE producto set descripcion =?, stock =? where id=?");
            ps.setString(1, aviso.getDescripcion());
            ps.setInt(2, aviso.getStock());
            ps.setInt(3, aviso.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM producto where id=?");
            ps.setInt(1, id);
            System.out.println("Registro eliminado");
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Aviso getById(int id) throws Exception {
        Aviso avi = new Aviso();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM producto WHERE id=? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                avi.setId(rs.getInt("id"));
                avi.setDescripcion(rs.getString("descripcion"));
                avi.setStock(rs.getInt("stock"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return avi;
    }

    @Override
    public List<Aviso> getAll() throws Exception {
       List<Aviso> lista=null;
       try{
           this.conectar();
           PreparedStatement ps=this.conn.prepareStatement("SELECT * FROM producto");
           ResultSet rs= ps.executeQuery();
           lista=new ArrayList<Aviso>();
           while(rs.next()){
               Aviso avi=new Aviso();
               avi.setId(rs.getInt("id"));
               avi.setDescripcion(rs.getString("descripcion"));
                avi.setStock(rs.getInt("stock"));
               lista.add(avi);
           }
           rs.close();
           ps.close();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
       }
       return lista;
        
    }

}
