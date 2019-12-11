package model;

import java.util.Arrays;

public class Imagenes {

	private int codigo;
	private String nombre;
	private byte[] imagen;
	
	// CONSTRUCTOR
	public Imagenes() {
		
	}
	
	
	// TO STRING
	@Override
	public String toString() {
		return "Imagenes [codigo=" + codigo + ", nombre=" + nombre + ", imagen=" + Arrays.toString(imagen)
				+ ", getCodigo()=" + getCodigo() + ", getNombre()=" + getNombre() + ", getImagen()="
				+ Arrays.toString(getImagen()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	// GETTER AND SETTER
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    /**
     * @return the foto
     */
	public byte[] getImagen() {
		return imagen;
	}
    /**
     * @param foto the foto to set
     */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
}
