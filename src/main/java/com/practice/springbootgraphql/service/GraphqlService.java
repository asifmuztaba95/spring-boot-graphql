package com.practice.springbootgraphql.service;

import com.practice.springbootgraphql.model.Book;
import com.practice.springbootgraphql.repository.BookRepository;
import com.practice.springbootgraphql.service.dataFetcher.AllBookDataFetcher;
import com.practice.springbootgraphql.service.dataFetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphqlService {
	
	@Value("classpath:books.graphql")
	Resource resource;
	
	private GraphQL graphQL;
	@Autowired
	private AllBookDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;
	@Autowired
	BookRepository bookRepository;
	
	@PostConstruct
	private void loadSchema() throws IOException {
		
		loadDataIntoHSQL();
		
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRunTimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	private RuntimeWiring buildRunTimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring ->
					typeWiring
							.dataFetcher("allBooks", allBooksDataFetcher)
							.dataFetcher("book", bookDataFetcher))
				.build();
	}
	
	public GraphQL getGraphQL() {
			return graphQL;
	}
	
	private void loadDataIntoHSQL() {
		
		Stream.of(
				new Book("123", "Book of Clouds", "Kindle Edition"),
				new Book("124", "Cloud Arch & Engineering", "Orielly"),
				new Book("125", "Java 9 Programming", "Orielly")
		).forEach(book -> bookRepository.save(book));
	}
}
