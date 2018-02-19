package com.SnippetWorld.imp;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.SnippetWorld.data.SnippetData;
import com.SnippetWorld.data.UserData;
import com.SnippetWorld.model.Atalho;
import com.SnippetWorld.model.Linguagem;
import com.SnippetWorld.model.Snippet;
import com.SnippetWorld.model.User;

public class SnippetManager {
	
	static List<Snippet> snippets = new ArrayList<Snippet>();
	static SnippetManager snippetManager = null;
	static Key key;

	public static SnippetManager getInstance() {
		if (snippetManager == null) {
			snippetManager = new SnippetManager();

		}
		return snippetManager;
	}

	// GET KEY TOKEN
	public Key getKey() {
		return key;
	}

	public void createSnippet(String nomeSnippet, String nomelinguagem, String extensao, String tags,
			String descricao, String conteudo, String token) {

		List<User> users = UsersManager.getInstance().getUser(token);
		String username = null;
		String name = null;
		for (User user : users) {
		 username = user.getUsername();
		 name = user.getName();
		}
		
		User user = new User (username,name);
		
		Linguagem linguagem = new Linguagem(nomelinguagem,extensao);
		Atalho atalho = new Atalho(descricao,conteudo);
		
		SnippetData snippetData = SnippetData.getInstance();
		

		snippetData.createSnippet(new Snippet("3",nomeSnippet,user,linguagem,atalho,tags));
	}
	
	public List<Snippet> getSnippet(String nomeLinguagem) {
		SnippetData snippetData = SnippetData.getInstance();
		return snippetData.getSnippet(nomeLinguagem);
	}

	public List<Snippet> getSnippets() {
		SnippetData snippetDa = SnippetData.getInstance();
		return snippetDa.getSnippets();
	}

	public Object removeExercise(String nomeSnippet, String userIDAuthour) {
		
		List<Snippet> sn = SnippetData.getInstance().getSnippet(nomeSnippet);
		String userEx = null;
		for (Snippet sn1 : sn) {
			userEx = sn1.autor.getUsername();
		}
		// CHECKS IF THE USER LOGGED IS THE AUTHOR OF THE EXERCISE
		if (userIDAuthour.equals(userEx)) {
			SnippetData snippetData = SnippetData.getInstance();
			System.out.println("REMOVIDO COM SUCESSO");
			return snippetData.removeExercise(nomeSnippet);
			
		} else {
			System.out.println("VOCE NAO PODE REMOVER ESTE EXERCISE PQ NAO TEM PERMISSOES");
		}
		return null;
	}

	
}
