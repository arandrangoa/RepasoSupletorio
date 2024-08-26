package com.edu.uce.pw.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.uce.pw.api.repository.IProductoRepository;
import com.edu.uce.pw.api.repository.modelo.Producto;
import com.edu.uce.pw.api.service.to.ProductoCompletoTO;
import com.edu.uce.pw.api.service.to.ProductoTO;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoRepository iProductoRepository;
	
	@Override
	public void guardar(ProductoCompletoTO producto) {
		// TODO Auto-generated method stub
		this.iProductoRepository.insertar(this.convertirCompletoTOAProducto(producto));
		
	}
	
	private ProductoCompletoTO convertirAVehiculoCompletoTO(Producto producto) {
		ProductoCompletoTO p=new ProductoCompletoTO();
		p.setCodigoBarras(producto.getCodigoBarras());
		p.setNombre(producto.getNombre());
		p.setId(producto.getId());
		p.setFechaCaducidad(producto.getFechaCaducidad());
		return p;	
	}
	
	private Producto convertirCompletoTOAProducto(ProductoCompletoTO producto) {
		Producto p=new Producto();
		p.setId(producto.getId());
		p.setCodigoBarras(producto.getCodigoBarras());
		p.setNombre(producto.getNombre());
		p.setFechaCaducidad(producto.getFechaCaducidad());
		return p;
	}
	
	private ProductoTO convertirAProductoTO(Producto producto) {
		ProductoTO p=new ProductoTO();
		p.setCodigoBarras(producto.getCodigoBarras());
		p.setNombre(producto.getNombre());
		
		return p;
	}

	@Override
	public List<ProductoTO> buscarTodos() {
		// TODO Auto-generated method stub
		List<Producto> lista=this.iProductoRepository.seleccionarTodos();
		List<ProductoTO> listaTO=new ArrayList<>();
		for(Producto p:lista) {
			listaTO.add(this.convertirAProductoTO(p));
		}
		return listaTO;
	}

	@Override
	public ProductoCompletoTO buscarPorCodigo(String codigoBaras) {
		// TODO Auto-generated method stub
		Producto p=this.iProductoRepository.seleccionarPorCodigo(codigoBaras);
		return this.convertirAVehiculoCompletoTO(p);
	}

	@Override
	public void borrar(String codigoBarras) {
		// TODO Auto-generated method stub
		this.iProductoRepository.eliminar(codigoBarras);
	}

	@Override
	public void modificar(ProductoCompletoTO producto) {
		// TODO Auto-generated method stub
		this.iProductoRepository.actualizar(this.convertirCompletoTOAProducto(producto));
	}

}
