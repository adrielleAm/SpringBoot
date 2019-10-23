package com.maquinadebusca.app.model.service;

import com.maquinadebusca.app.model.Documento;
import com.maquinadebusca.app.model.Link;

import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maquinadebusca.app.model.repository.DocumentoRepository;
import com.maquinadebusca.app.model.repository.LinkRepository;

@Service
public class ColetorService {
	@Autowired
	private DocumentoRepository dr;
	@Autowired
	private LinkRepository lr;

	public List<Documento> executar() {
		List<Documento> documentos = new LinkedList<>();
		List<String> sementes = new LinkedList<>();
		try {
			sementes.add("https://www.youtube.com/");
			sementes.add("https://www.facebook.com/");
			sementes.add("https://www.twitter.com/");
			for (String url : sementes) {
				documentos.add(this.coletar(url));
			}
		} catch (Exception e) {
			System.out.println("Erro ao executar o serviço de coleta!");
			e.printStackTrace();
		}
		return documentos;
	}

	public Documento coletar(String urlDocumento) {
		Documento documento = new Documento();
		try {
			Document d = Jsoup.connect(urlDocumento).get();
			Elements urls = d.select("a[href]");
			documento.setUrl(urlDocumento);
			documento.setTexto(d.html());
			documento.setVisao(d.text());
			for (Element url : urls) {
				String u = url.attr("abs:href");
				// valida o conteudo da url (não esta vazio ou "")
				if ((!u.equals("")) && (u != null)) {
					Link link = new Link();
					link.setUrl(u);
					lr.save(link);
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao coletar a página.");
			e.printStackTrace();
		}
		documento = dr.save(documento);
		return documento;
	}

	public List<Documento> getDocumentos() {
		Iterable<Documento> documentos = dr.findAll();
		List<Documento> resposta = new LinkedList<>();
		for (Documento documento : documentos) {
			resposta.add(documento);
		}
		return resposta;
	}

	public Documento getDocumento(long id) {
		return dr.findById(id);

	}

	public List<Link> getLinks() {	
		return lr.findAll();
	}

	public Link getLink(long id) {
		return lr.findById(id);
	}
}
