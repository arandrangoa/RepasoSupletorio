package com.edu.uce.pw.api.service.to;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class ProductoTO extends RepresentationModel<ProductoTO> implements Serializable{

	private static final long serialVersionUID = -706137684488762221L;
	
	private String codigoBarras;
	private String nombre;
	
	//SET Y GET
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
