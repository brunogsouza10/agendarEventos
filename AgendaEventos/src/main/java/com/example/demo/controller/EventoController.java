package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Convidado;
import com.example.demo.model.Evento;
import com.example.demo.repository.ConvidadoRepository;
import com.example.demo.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ConvidadoRepository convidadoRepository;

	@GetMapping("/cadastrarEvento")
	public String form() {

		return "evento/formEvento";
	}

	@PostMapping("/cadastrarEvento")
	public String form(Evento evento) {

		eventoRepository.save(evento);

		return "redirect:/cadastrarEvento";
	}

	@GetMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("evento/index.html");
		Iterable<Evento> eventos = eventoRepository.findAll();
		mv.addObject("eventos", eventos);
		return mv;

	}

	@GetMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo")long codigo) {
    	Evento evento = eventoRepository.findByCodigo(codigo);
    	ModelAndView mv = new ModelAndView("evento/detalhesEvento.html");	
    	mv.addObject("evento", evento);
    	
    	Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
    	mv.addObject("convidados", convidados);	
    	
    			return mv;
    			
    }
	@GetMapping("/deletarEvento")
	public String deletarEvento(@RequestParam long codigo) {
	    Evento evento = eventoRepository.findByCodigo(codigo);
	    
	    if (evento != null) {
	        // Busca todos os convidados associados a este evento
	        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
	        
	        // Exclui todos os convidados
	        convidadoRepository.deleteAll(convidados);
	        
	        // Exclui o evento
	        eventoRepository.delete(evento);
	        
	        return "redirect:/eventos";
	    } else {
	        // Evento não encontrado, trata o erro adequadamente
	        return "redirect:/eventos";
	    }
	}

	@GetMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {
		
		Convidado convidado = convidadoRepository.findByRg(rg);
		convidadoRepository.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo= " " + codigoLong;
		return "redirect:/" + codigo;
		
	}

	@PostMapping("/{codigo}")
	public String adicionarConvidado(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		return "redirect:/{codigo}";
	}

}