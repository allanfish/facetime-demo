package com.lucene.demo;

import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassHits;
import org.compass.core.CompassQuery.SortDirection;
import org.compass.core.CompassQuery.SortPropertyType;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;

public class CompassLogic {

	public void buildIndex() {
		CompassSession session = null;
		session = CompassUtil.getCompassSession();
		try {
			CompassUtil.beginTransaction();
			Product p1 = new Product(1, "a瑜伽球", "南阳瑜伽球");
			Product p2 = new Product(2, "a瑜伽球b", "南阳瑜伽球");
			Product p3 = new Product(3, "瑜伽球c", "南阳瑜伽球");
			session.save(p1);
			session.save(p2);
			session.save(p3);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			CompassUtil.closeCompassSession();
		}
	}

	public List<Product> search(String keyword, int first, int max) {
		List<Product> result = new ArrayList<Product>();
		CompassSession session = CompassUtil.getCompassSession();
		CompassHits hits = session.find(keyword);
		int length = first + max;
		if (length > hits.length()) {
			length = hits.length();
		}
		for (int i = first; i < length; i++) {
			Product product = (Product) hits.data(i);
			product.setContent(hits.highlighter(i).fragment("content"));
			result.add(product);
		}
		hits.close();
		session.close();
		return result;
	}

	public List<Product> search(String keyword, Integer productId) {
		List<Product> result = new ArrayList<Product>();
		CompassSession session = CompassUtil.getCompassSession();
		CompassQueryBuilder queryBuilder = session.queryBuilder();
		CompassHits hits = queryBuilder.bool().addMust(queryBuilder.spanEq("id", productId))
				.addMust(queryBuilder.queryString(keyword).toQuery()).toQuery()
				.addSort("id", SortPropertyType.INT, SortDirection.REVERSE).hits();
		for (int i = 0; i < hits.length(); i++) {
			Product product = (Product) hits.data(i);
			product.setContent(hits.highlighter(i).fragment("content"));
			result.add(product);
		}
		hits.close();
		session.close();
		return result;
	}

	public void deleteIndex() {
		CompassSession session = CompassUtil.getCompassSession();
		session.beginTransaction();
		Product product = new Product();
		product.setId(1);
		session.delete(product);
		session.commit();
		session.close();
	}

	public void updateIndex() {
		CompassSession session = CompassUtil.getCompassSession();
		session.beginTransaction();

		Product product = new Product();
		product.setId(2);
		product.setContent("新的瑜伽球");
		session.delete(product);
		product.setName("新的瑜伽球");
		session.save(product);

		session.commit();
		session.close();
	}
}
