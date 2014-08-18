package com.lucene.demo;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class CompassTest extends TestCase {

	private CompassLogic compassLogic = new CompassLogic();

	@Test
	public void testBuildIndex() {
		compassLogic.buildIndex();
	}

	@Test
	public void testDeleteIndex() {
		compassLogic.deleteIndex();
	}

	@Test
	public void testUpdateIndex() {
		compassLogic.updateIndex();
	}

	@Test
	public void testSearch() {
		List<Product> products = compassLogic.search("瑜伽球", 0, 10);
		for (Product product : products) {
			System.out.println("================");
			System.out.println("product id: " + product.getId());
			System.out.println("product name: " + product.getName());
			System.out.println("product content:" + product.getContent());
			System.out.println("=================");
		}
	}

	@Test
	public void testSearchKeywordProductId() {
		List<Product> products = compassLogic.search("瑜伽球", 1);
		for (Product product : products) {
			System.out.println("================");
			System.out.println("product id: " + product.getId());
			System.out.println("product name: " + product.getName());
			System.out.println("product content:" + product.getContent());
			System.out.println("=================");
		}
	}
}
