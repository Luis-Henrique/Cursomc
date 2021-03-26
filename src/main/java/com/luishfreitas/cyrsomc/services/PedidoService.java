package com.luishfreitas.cyrsomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luishfreitas.cyrsomc.domain.Pedido;
import com.luishfreitas.cyrsomc.repositories.PedidoRepository;
import com.luishfreitas.cyrsomc.services.exceptions.ObjectNotFoundException;

//Classe de servico que oferece operações de categorias
@Service
public class PedidoService {

	// Anotação que instancia o repositorio
	@Autowired
	PedidoRepository repo;

	public Optional<Pedido> find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
			
		return Optional.ofNullable(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado:" + id + " Tipo:" + Pedido.class.getName())));
	}

}
