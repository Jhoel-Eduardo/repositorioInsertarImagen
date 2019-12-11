package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion_bkp{
	
	public static Connection getConexion() {
		
		Connection con = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			//String url = "jdbc:mysql://localhost:3306/fc_ws_vida";			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/fc_ws_vida?useTimeZone=true&serverTimezone=UTC";			
			String uid = "vfps_vidasoft";
			String pwd = "VFPSteam2019@@";
			con = DriverManager.getConnection(url,uid,pwd);
		} 
		catch (ClassNotFoundException ex) {
			System.out.println("ERROR >> Driver no instalado!! " + ex.getMessage());
		}
		catch (SQLException ex) {
			System.out.println("ERROR >> de conexión con la BD : "+ ex.getMessage());
		}
		catch (Exception e) {
			System.out.println("ERROR >> " + e.getMessage());
		}
		return con;
	}
	
	public static void closeConexion(Connection con) {
		try {
			con.close();
		} 
		catch (Exception e) {
			System.out.println("No se pudo cerrar la conexión");
		}
	}
}
