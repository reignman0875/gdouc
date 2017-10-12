package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl;

public class PeopleSoftBean {
	
//	DIR_NOMBRE
	private String dirNombre;

	//	DIR_APELLIDO
	private String dirApellido;

	private String nombrePosicionDirector;
	//PUESTO
	private String nombrePosicion;
	
	//OPRID
	private String perfil;
	//NOMBRE
	private String nombre;
	//APELLIDO SEGUNDO_APELLIDO
	private String apellidos;
	//PUESTO
	private String puesto;
	//AREA
	private String departamento;
	//DIRECCION
	private String area;
	
	public PeopleSoftBean() {
		nombrePosicion=perfil=nombre=apellidos=puesto=
				departamento=area=new String();
		nombrePosicionDirector = "Director";
	}
	
	public String getNombrePosicion() {
		return nombrePosicion;
	}
	public void setNombrePosicion(String nombrePosicion) {
		this.nombrePosicion = nombrePosicion;
	}
	
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	 
	public String getDirNombre() {
		return dirNombre;
	}

	public void setDirNombre(String dirNombre) {
		this.dirNombre = dirNombre;
	}

	public String getDirApellido() {
		return dirApellido;
	}

	public void setDirApellido(String dirApellido) {
		this.dirApellido = dirApellido;
	}


	public String getNombrePosicionDirector() {
		return nombrePosicionDirector;
	}

	public void setNombrePosicionDirector(String nombrePosicionDirector) {
		this.nombrePosicionDirector = nombrePosicionDirector;
	}
	
	
}
