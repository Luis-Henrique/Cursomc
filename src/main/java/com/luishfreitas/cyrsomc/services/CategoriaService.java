package com.luishfreitas.cyrsomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luishfreitas.cyrsomc.domain.Categoria;
import com.luishfreitas.cyrsomc.repositories.CategoriaRepository;

//Classe de servico que oferece operações de categorias
@Service
public class CategoriaService {
	
	//Anotação que instancia o repositorio
	@Autowired
	CategoriaRepository repo;
	
	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj;
	}

}
