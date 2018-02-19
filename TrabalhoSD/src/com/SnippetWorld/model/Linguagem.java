package com.SnippetWorld.model;

public class Linguagem {
	public String nomeLinguagem;
	public String extensao;
	
	public String getNomeLinguagem() {
		return nomeLinguagem;
	}
	public void setNomeLinguagem(String nomeLinguagem) {
		this.nomeLinguagem = nomeLinguagem;
	}
	public String getExtensao() {
		return extensao;
	}
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	
	public Linguagem(String nomeLinguagem, String extensao) {
		super();
		this.nomeLinguagem = nomeLinguagem;
		this.extensao = extensao;
	}
	
	public Linguagem() {
		super();
	}
	
	
	
	
	
	

}
