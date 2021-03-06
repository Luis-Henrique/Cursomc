package com.luishfreitas.cyrsomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luishfreitas.cyrsomc.domain.Cliente;

//Anotacao para acessar o banco de dados
//JpaRepository para acessar os dados com o tipo passado com o atributo identificador 
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	//findby e o nome do campo para buscar esse campo do banco
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
