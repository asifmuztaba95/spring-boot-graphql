package com.practice.springbootgraphql.service.dataFetcher;

import com.practice.springbootgraphql.model.Book;
import com.practice.springbootgraphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBookDataFetcher implements DataFetcher<List<Book>> {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
		
		return bookRepository.findAll();
	}
}
