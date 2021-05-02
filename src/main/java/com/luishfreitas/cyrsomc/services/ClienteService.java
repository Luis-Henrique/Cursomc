package com.luishfreitas.cyrsomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luishfreitas.cyrsomc.domain.Cidade;
import com.luishfreitas.cyrsomc.domain.Cliente;
import com.luishfreitas.cyrsomc.domain.Endereco;
import com.luishfreitas.cyrsomc.domain.enums.TipoCliente;
import com.luishfreitas.cyrsomc.dto.ClienteDTO;
import com.luishfreitas.cyrsomc.dto.ClienteNewDTO;
import com.luishfreitas.cyrsomc.repositories.ClienteRepository;
import com.luishfreitas.cyrsomc.repositories.EnderecoRepository;
import com.luishfreitas.cyrsomc.services.exceptions.DateIntegrityException;
import com.luishfreitas.cyrsomc.services.exceptions.ObjectNotFoundException;

//Classe de servico que oferece operações de categorias
@Service
public class ClienteService {

	// Anotação que instancia o repositorio
	@Autowired
	ClienteRepository repo;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	public Optional<Cliente> find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);

		return Optional.ofNullable(obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado:" + id + " Tipo:" + Cliente.class.getName())));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj =  repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			throw new DateIntegrityException("nao é possível remover porque tem entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {

		return repo.findAll();

	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
