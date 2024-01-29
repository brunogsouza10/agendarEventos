package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Evento;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long>{

	Evento findByCodigo(long codigo);



	

}
