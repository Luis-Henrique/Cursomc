package com.luishfreitas.cyrsomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luishfreitas.cyrsomc.domain.Endereco;

//Anotacao para acessar o banco de dados
//JpaRepository para acessar os dados com o tipo passado com o atributo identificador 
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	
	

}
