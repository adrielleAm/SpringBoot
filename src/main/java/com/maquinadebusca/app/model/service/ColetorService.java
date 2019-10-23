package com.maquinadebusca.app.model.service;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.maquinadebusca.app.model.Documento;
import com.maquinadebusca.app.repository.DocumentoRepository;

@Service
public class ColetorService implements DocumentoRepository {

	@Autowired
	private DocumentoRepository dr;
	
	public Documento iniciar() {
		URL url;
		Documento d = new Documento();
		try {
			url = new URL("http://journals.ecs.soton.ac.uk/java/tutorial/networking/urls/readingWriting.html");
			Document doc = Jsoup.connect(url.toString()).get();
			Elements links = doc.select("a[href]");
			d.setUrl(url);
			d.setTexto(doc.html());
			d.setVisao(doc.text());
			List<String> urls = new LinkedList();
			for (Element link : links)
				if ((!link.attr("abs:href").equals("") && (link.attr("abs:href") != null)))
					urls.add(link.attr("abs:href"));
			d.setUrls(urls);
		} catch (Exception e) {
			System.out.println("Erro ao coletar a página.");
			e.printStackTrace();
		}
		return d;
	}
}
