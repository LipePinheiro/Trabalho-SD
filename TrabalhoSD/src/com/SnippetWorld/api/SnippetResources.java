package com.SnippetWorld.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.SnippetWorld.imp.SnippetManager;
import com.SnippetWorld.imp.UsersManager;
import com.SnippetWorld.model.Snippet;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Path("/snippets")
public class SnippetResources {

	// Post a new game
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response insertSnippet(@FormParam("nomeSnippet") String nomeSnippet,
			@FormParam("nomelinguagem") String nomelinguagem, @FormParam("extensao") String extensao,
			@FormParam("tags") String tags, @FormParam("descricao") String descricao,
			@FormParam("conteudo") String conteudo, @FormParam("token") String token,

			@Context UriInfo uriInfo) {

		SnippetManager snm = SnippetManager.getInstance();
		UsersManager unm = UsersManager.getInstance();

		try {
			// Verify JWT token
			Jwts.parser().setSigningKey(unm.getKey()).parseClaimsJws(token);
			// OK, we can trust this JWT

			// Get user data
			String userlogin = (String) Jwts.parser().setSigningKey(unm.getKey()).parseClaimsJws(token).getBody()
					.get("username");

			snm.createSnippet(nomeSnippet, nomelinguagem, extensao, tags, descricao, conteudo, userlogin);

			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(nomeSnippet);
			return Response.created(builder.build()).build();

		} catch (SignatureException e) {
			// don't trust the JWT!
			return Response.serverError().status(401).type("text/plain").entity("No valid token!").build();
		}

	}
	
	

	// DELETE A SPECIFIC EXERCISE
		@Path("/{nomeSnippet}")
		@DELETE
		public Response removeExercise(@PathParam("nomeSnippet") String nomeSnippet, @FormParam("token") String token) {

			UsersManager userManager = new UsersManager();
			SnippetManager sManager = SnippetManager.getInstance();

			try {
				// Verify JWT token
				Jwts.parser().setSigningKey(userManager.getKey()).parseClaimsJws(token);
				// OK, we can trust this JWT

				// Get user data
				String userIDAuthour = (String) Jwts.parser().setSigningKey(userManager.getKey()).parseClaimsJws(token)
						.getBody().get("username");

				sManager.removeExercise(nomeSnippet, userIDAuthour);
				return Response.serverError().status(200).type("text/plain").entity("Exercise Removed").build();

			} catch (SignatureException e) {
				// don't trust the JWT!
				return Response.serverError().status(401).type("text/plain").entity("You do not have permissions!").build();
			}

		}
	

	// GET all snippets
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Snippet> getSnippets() {

		SnippetManager sm = SnippetManager.getInstance();

		return sm.getSnippets();
	}

	// GET a specific snippet
	@Path("/{nomeLinguagem}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Snippet> getSnippet(@PathParam("linguagem.nomeLinguagem") String nomeLinguagem) {

		SnippetManager sm = SnippetManager.getInstance();
		return sm.getSnippet(nomeLinguagem);
	}
	
	

}
