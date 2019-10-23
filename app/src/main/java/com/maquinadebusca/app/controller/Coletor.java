package com.maquinadebusca.app.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.maquinadebusca.app.model.Documento;
import com.maquinadebusca.app.model.Link;
import com.maquinadebusca.app.model.service.ColetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/coletor") // URL: http://localhost:8080/coletor
public class Coletor {
	@Autowired
	ColetorService cs;

	// URL: http://localhost:8080/coletor/iniciar
	@GetMapping(value = "/iniciar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Documento> iniciar() {
		List<Documento> documentos = cs.executar();
		return documentos;
	}

	// URL: http://localhost:8080/coletor/documento
	@GetMapping(value = "/documento", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Documento> listarDocumentos() {
		return cs.getDocumentos();
	}

	// Request for: http://localhost:8080/coletor/documento/{id}
	@GetMapping(value = "/documento/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Documento listarDocumentos(@PathVariable(value = "id") long id) {
		return cs.getDocumento(id);
	}

	// URL: http://localhost:8080/coletor/link
	@GetMapping(value = "/link", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Link> listaLink() {
		return cs.getLinks();
	}

	@GetMapping(value = "/link/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Link listaLink(@PathVariable(value = "id") long id) {
		return cs.getLink(id);
	}

}