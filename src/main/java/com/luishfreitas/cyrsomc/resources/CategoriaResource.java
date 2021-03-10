package com.luishfreitas.cyrsomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luishfreitas.cyrsomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	//expoe com verbo http
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		List<Categoria> categorias = new ArrayList<>();
		
		Categoria cat1 = new Categoria(1, "Infomática");
		Categoria cat2 = new Categoria(2, "Cama e Banho");
		
		categorias.add(cat1);
		categorias.add(cat2);
		
		return categorias;
		
	}

}
