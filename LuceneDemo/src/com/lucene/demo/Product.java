package com.lucene.demo;

import java.io.Serializable;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@Searchable
public class Product implements Serializable {

	private static final long serialVersionUID = -6479698827776552341L;

	@SearchableId(name = "id")
	private Integer id;
	@SearchableProperty(index = Index.NO, store = Store.YES)
	private String name;
	@SearchableProperty(boost = 2, index = Index.ANALYZED, store = Store.YES)
	private String content;

	public Product() {
		super();
	}

	public Product(Integer id, String name, String content) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
