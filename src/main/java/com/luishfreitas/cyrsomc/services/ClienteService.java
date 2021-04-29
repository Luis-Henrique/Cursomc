package com.luishfreitas.cyrsomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luishfreitas.cyrsomc.domain.Cliente;
import com.luishfreitas.cyrsomc.domain.Cliente;
import com.luishfreitas.cyrsomc.dto.ClienteDTO;
import com.luishfreitas.cyrsomc.repositories.ClienteRepository;
import com.luishfreitas.cyrsomc.services.exceptions.DateIntegrityException;
import com.luishfreitas.cyrsomc.services.exceptions.ObjectNotFoundException;

//Classe de servico que oferece operações de categorias
@Service
public class ClienteService {

	// Anotação que instancia o repositorio
	@Autowired
	ClienteRepository repo;

	public Optional<Cliente> find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);

		return Optional.ofNullable(obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado:" + id + " Tipo:" + Cliente.class.getName())));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Optional<Cliente> newObj = find(obj.getId());
		updateData(newObj.get(), obj);
		return repo.save(newObj.get());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException("nao é possível remover uma cliente com produtos");
		}
	}

	public List<Cliente> findAll() {

		return repo.findAll();

	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
