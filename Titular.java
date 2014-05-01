package com.example.purebabd;



public class Titular {
	
	String nombre, disponible, enlacertmp;
	int imgdisponible, imagen;
	
	
	public Titular(String nombre, String disponible, int imgdisponible, int imagen, String enlacertmp){
		super();
		this.nombre = nombre;
		this.disponible = disponible;
		this.imgdisponible = imgdisponible;
		this.imagen = imagen;
		this.enlacertmp = enlacertmp;
		
		System.out.println(nombre);
	}
	
	

	public String getNombre(){
		return nombre;	
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;	
	}
	
	public String getDisponible(){
		return disponible;	
	}
	
	public void setDisponible(String disponible){
		this.disponible = disponible;	
	}
	
	public int getImgdisponible(){
		return imgdisponible;	
	}
	
	public void setImgdisponible(int imgdisponible){
		this.imgdisponible = imgdisponible;	
	}
	
	public int getImagen(){
		return imagen;	
	}
	
	public void setImagen(int imagen){
		this.imagen = imagen;	
	}
	
	public String getEnlaceRtmp(){
		return enlacertmp;	
	}
	
	public void setEnlaceRtmp(String enlacertmp){
		this.enlacertmp = enlacertmp;	
	}
	
	@Override
	public String toString(){
		return this.nombre+this.disponible+this.imagen+this.imgdisponible;	
	}

}
