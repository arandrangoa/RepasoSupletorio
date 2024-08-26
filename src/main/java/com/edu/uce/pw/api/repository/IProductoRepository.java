package com.edu.uce.pw.api.repository;

import java.util.List;

import com.edu.uce.pw.api.repository.modelo.Producto;

public interface IProductoRepository {
	
	public void insertar(Producto producto);
	
	public List<Producto> seleccionarTodos();
	
	public Producto seleccionarPorCodigo(String codigoBaras);
	
	public void eliminar(String codigoBarras);
	
	public void actualizar(Producto producto);

}
