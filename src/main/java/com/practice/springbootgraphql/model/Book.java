package com.practice.springbootgraphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@Id
	private String id;
	private String title;
	private String author;
	
}
