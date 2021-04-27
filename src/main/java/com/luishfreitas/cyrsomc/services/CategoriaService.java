package com.luishfreitas.cyrsomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luishfreitas.cyrsomc.domain.Categoria;
import com.luishfreitas.cyrsomc.dto.CategoriaDTO;
import com.luishfreitas.cyrsomc.repositories.CategoriaRepository;
import com.luishfreitas.cyrsomc.services.exceptions.DateIntegrityException;
import com.luishfreitas.cyrsomc.services.exceptions.ObjectNotFoundException;

//Classe de servico que oferece operações de categorias
@Service
public class CategoriaService {

	// Anotação que instancia o repositorio
	@Autowired
	CategoriaRepository repo;

	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return Optional.ofNullable(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado:" + id + " Tipo:" + Categoria.class.getName())));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException("nao é possível remover uma categoria com produtos");
		}
	}

	public List<Categoria> findAll() {

		return repo.findAll();

	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
