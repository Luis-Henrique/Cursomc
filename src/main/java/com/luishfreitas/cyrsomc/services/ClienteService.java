package com.luishfreitas.cyrsomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luishfreitas.cyrsomc.domain.Cliente;
import com.luishfreitas.cyrsomc.repositories.ClienteRepository;
import com.luishfreitas.cyrsomc.services.exceptions.ObjectNotFoundException;

//Classe de servico que oferece operações de categorias
@Service
public class ClienteService {

	// Anotação que instancia o repositorio
	@Autowired
	ClienteRepository repo;

	public Optional<Cliente> find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
			
		return Optional.ofNullable(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado:" + id + " Tipo:" + Cliente.class.getName())));
	}

}
