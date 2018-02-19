package com.SnippetWorld.model;

public class Atalho {
	
		public String descricao;
		public String conteudo;
		
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public String getConteudo() {
			return conteudo;
		}
		public void setConteudo(String conteudo) {
			this.conteudo = conteudo;
		}
		
		public Atalho(String descricao, String conteudo) {
			super();
			this.descricao = descricao;
			this.conteudo = conteudo;
		}
		
		public Atalho() {
			super();
		}
		
		
		
}
