package com.edu.uce.pw.api.service;

import java.util.List;

import com.edu.uce.pw.api.repository.modelo.Producto;
import com.edu.uce.pw.api.service.to.ProductoCompletoTO;
import com.edu.uce.pw.api.service.to.ProductoTO;

public interface IProductoService {
	
	public void guardar(ProductoCompletoTO producto);
	
	public List<ProductoTO> buscarTodos();
	
	public ProductoCompletoTO buscarPorCodigo(String codigoBaras);
	
	public void borrar(String codigoBarras);
	
	public void modificar(ProductoCompletoTO producto);

}
