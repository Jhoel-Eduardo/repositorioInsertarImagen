package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
	
	public static Connection getConexion() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/vfps_vidasoft";
			String usr = "root";
			String psw = "VFPSteam2019@@";
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error >> Driver no Instalado!!");
		} catch (SQLException ex) {
			System.out.println("Error >> de conexión con la BD");
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
