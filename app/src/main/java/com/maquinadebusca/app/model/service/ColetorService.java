package com.maquinadebusca.app.model.service;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maquinadebusca.app.model.Documento;

public class ColetorService {
	
	public List<Documento> iniciar() {
	   List<String> urls_sementes = new LinkedList ();
	   List<Documento> documentos = new LinkedList ();
	   urls_sementes.add("https://www.oracle.com/index.html");
	   urls_sementes.add ("https://www.youtube.com/");
	   urls_sementes.add ("https://twitter.com/home?lang=pt");
	   urls_sementes.add ("https://pt-br.facebook.com/");
	   
	   for (String url_str : urls_sementes)
		   documentos.add(this.coletar(url_str));
	   return documentos;
	}	
	
	public Documento coletar (String url_str) {
		URL url;
		//Cria um documento
		Documento d = new Documento();
		try {
			//Cria uma nova URL
			url = new URL(url_str);
			//Faz o download da url criada
			Document doc = Jsoup.connect(url.toString()).get();
			//recuperar as partes do documento que possui a tag a[href]
			Elements links = doc.select("a[href]");
			//Atribui valor para os atributos do obj criado
			d.setUrl(url);
			d.setTexto(doc.html());
			d.setVisao(doc.text());
			//Criar uma nova lista de urls 
			List<String> urls = new LinkedList();
			//Faz um loop na lista de links 
			for (Element link : links)
				//Valida o atributos href da tag html <a> não está vazia ou nula 
				if ((!link.attr("abs:href").equals("") && (link.attr("abs:href") != null)))
					//caso o atributo estiver preenchido adiciona na list de urls
					urls.add(link.attr("abs:href"));
			//Atribui a lista de urls para a lista de urls do meu obj
			d.setUrls(urls);

			System.out.println("\n\n\n=================================================");
			System.out.println(">>> URL:");
			System.out.println("=================================================");
			System.out.println(d.getUrl());
			System.out.println("\n\n\n=================================================");
			System.out.println(">>> Página:");
			System.out.println("=================================================");
			System.out.println(d.getTexto());
			System.out.println("\n\n\n=================================================");
			System.out.println(">>> Visão:");
			System.out.println("=================================================");
			System.out.println(d.getVisao());
			System.out.println("\n\n\n=================================================");
			System.out.println(">>> URLs:");
			System.out.println("=================================================");
			urls = d.getUrls();
			for (String u : urls)
				System.out.println(u);
		} catch (Exception e) {
			System.out.println("Erro ao coletar a página.");
			e.printStackTrace();
		}
		return d;
	}
}
