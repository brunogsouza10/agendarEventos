package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Convidado;
import com.example.demo.model.Evento;

@Repository

public interface ConvidadoRepository extends CrudRepository <Convidado, String>{

Iterable<Convidado> findByEvento(Evento evento);
Convidado findByRg(String rg);


}
