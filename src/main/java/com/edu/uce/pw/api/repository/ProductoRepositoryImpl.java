package com.edu.uce.pw.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.uce.pw.api.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	public List<Producto> seleccionarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Producto> query=this.entityManager.createQuery("select p from Producto p",Producto.class);
		return query.getResultList();
	}

	@Override
	public Producto seleccionarPorCodigo(String codigoBaras) {
		// TODO Auto-generated method stub
		TypedQuery<Producto> query=this.entityManager.createQuery("select p from Producto p where p.codigoBarras= :datoCodigo",Producto.class);
		query.setParameter("datoCodigo", codigoBaras);
		return query.getSingleResult();
	}

	@Override
	public void eliminar(String codigoBarras) {
		// TODO Auto-generated method stub
		Producto p=this.seleccionarPorCodigo(codigoBarras);
		this.entityManager.remove(p);
		
	}

	@Override
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		Producto p=this.seleccionarPorCodigo(producto.getCodigoBarras());
		p.setNombre(producto.getNombre());
		p.setFechaCaducidad(producto.getFechaCaducidad());
		this.entityManager.merge(p);
		
	}

}
