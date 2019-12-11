package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import interfaces.InterfaceImagen;
import model.Imagenes;
import utils.MySQLConexion;

public class GestionImagen implements InterfaceImagen{

	@Override
	public int InsertarImagen(Imagenes imagenes) {
						
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		
		try {			
			con = MySQLConexion.getConexion();
			String query = "INSERT INTO SheldonPrueba (nombre,imagen) VALUES(?,?)";
			pst = con.prepareStatement(query);
			
			pst.setString(1, imagenes.getNombre());
			pst.setBytes(2, imagenes.getImagen());
			
			rs = pst.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Error en la sentencia de insertar:" + e.getMessage());
		}
		finally {
			MySQLConexion.closeConexion(con);
		}
		return rs;
	}

	@Override
	public ArrayList<Imagenes> listado() {
		// TODO Auto-generated method stub
		return null;
	}

}
