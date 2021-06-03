package com.practice.springbootgraphql.service.dataFetcher;

import com.practice.springbootgraphql.model.Book;
import com.practice.springbootgraphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDataFetcher implements DataFetcher<Optional<Book>> {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@Override
	public Optional<Book> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
		
		String id = dataFetchingEnvironment.getArgument("id");
		return bookRepository.findById(id);
	}
}
