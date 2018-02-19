package com.SnippetWorld.data;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.SnippetWorld.model.Snippet;
import com.SnippetWorld.model.User;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SnippetData {

	static SnippetData snippetData = null;
	static MongoCollection<Snippet> colSnippets;

	
	public static SnippetData getInstance() {
		if (snippetData == null) {
			snippetData = new SnippetData();

			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost",
					MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbSnippets = mongoClient.getDatabase("db");
			colSnippets = dbSnippets.getCollection("Snippets", Snippet.class);
		}
		return snippetData;
	}
	
	public List<Snippet> getSnippets() {
		final List<Snippet> snippets = new ArrayList<Snippet>();
		Block<Snippet> printBlock = new Block<Snippet>() {
			public void apply(final Snippet snippet) {
				snippets.add(snippet);
			}
		};
		colSnippets.find().forEach(printBlock);
		return snippets;
	}

	public void createSnippet(Snippet snippet) {

		if (colSnippets.find(eq("nomeSnippet", snippet.getNomeSnippet())).first() == null) {
			colSnippets.insertOne(snippet);
		}
		
	}

	
	// GET A SPECIFIC USER
		public List<Snippet> getSnippet(String nomeLinguagem) {
			final List<Snippet> snippets = new ArrayList<Snippet>();
			Block<Snippet> printBlock = new Block<Snippet>() {
				public void apply(final Snippet snippet) {
					snippets.add(snippet);
				}
			};
			colSnippets.find(eq("linguagem.nomeLinguagem", nomeLinguagem)).forEach(printBlock);
			System.out.println("dsfadfasd");
			return snippets;
		}

		public Object removeExercise(String nomeSnippet) {
			colSnippets.deleteOne(eq("nomeSnippet", nomeSnippet));
			return null;
		}

		public void createExercise(Snippet sn) {
			if (colSnippets.find(eq("exerciseID", sn.getNomeSnippet())).first() == null) {
				colSnippets.insertOne(sn);
			}
		}
		
		
		

}
