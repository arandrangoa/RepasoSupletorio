package com.edu.uce.pw.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.uce.pw.api.service.IProductoService;
import com.edu.uce.pw.api.service.to.ProductoCompletoTO;
import com.edu.uce.pw.api.service.to.ProductoTO;

@Controller
@CrossOrigin
@RequestMapping(path = "/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService iProductoService;
	
	//http://localhost:8082/API/v1.0/Inventario/productos
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoCompletoTO> guardar(@RequestBody ProductoCompletoTO producto){
		this.iProductoService.guardar(producto);
		return ResponseEntity.status(HttpStatus.OK).body(producto);
	}
	
	//http://localhost:8082/API/v1.0/Inventario/productos/{}
	@GetMapping(path = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoCompletoTO> buscarPorCodigo(@PathVariable String codigo){
		ProductoCompletoTO p=this.iProductoService.buscarPorCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductoTO>> buscarTodos(){
		List<ProductoTO> lista=this.iProductoService.buscarTodos();
		for(ProductoTO p:lista) {
			Link link=linkTo(methodOn(ProductoController.class)
					.buscarPorCodigo(p.getCodigoBarras()))
					.withSelfRel();
			
			p.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@DeleteMapping(path = "/{codigoBarras}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrar(@PathVariable String codigoBarras){
		this.iProductoService.borrar(codigoBarras);	
		return ResponseEntity.status(HttpStatus.OK).body("BORRADO");
	}
	
	@PutMapping(path = "/{codigoBarras}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoCompletoTO> actualizar(@RequestBody ProductoCompletoTO producto, @PathVariable String codigoBarras){
		producto.setCodigoBarras(codigoBarras);
		this.iProductoService.modificar(producto);
		return ResponseEntity.status(HttpStatus.OK).body(producto);
	}
	
}
