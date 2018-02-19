package com.SnippetWorld.model;

public class Snippet {
	public String idSnippet;
	public String nomeSnippet;
	public User autor;
	public Linguagem linguagem;
	public Atalho atalho;
	
	public String tags;

	public String getIdSnippet() {
		return idSnippet;
	}

	public void setIdSnippet(String idSnippet) {
		this.idSnippet = idSnippet;
	}

	public String getNomeSnippet() {
		return nomeSnippet;
	}

	public void setNomeSnippet(String nomeSnippet) {
		this.nomeSnippet = nomeSnippet;
	}

	public User getAutor() {
		return autor;
	}

	public void setAutor(User autor) {
		this.autor = autor;
	}

	public Linguagem getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}

	public Atalho getAtalho() {
		return atalho;
	}

	public void setAtalho(Atalho atalho) {
		this.atalho = atalho;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Snippet(String idSnippet, String nomeSnippet, User autor, Linguagem linguagem, Atalho atalho, String tags) {
		super();
		this.idSnippet = idSnippet;
		this.nomeSnippet = nomeSnippet;
		this.autor = autor;
		this.linguagem = linguagem;
		this.atalho = atalho;
		this.tags = tags;
	}

	public Snippet() {
		super();
	}
	
	

	
	
}
