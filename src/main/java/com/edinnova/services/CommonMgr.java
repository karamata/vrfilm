package com.edinnova.services;

import java.util.List;

import com.edinnova.entities.Category;
import com.edinnova.entities.Movie;

public interface CommonMgr {

	<T> T getEntity(Class<T> clazz, Long id) throws Exception;

	<T> T createEntity(T entity) throws Exception;

	<T> void updateEntity(T entity) throws Exception;

	Category getCategoryByName(String name) throws Exception;

	List<Category> getListCategory() throws Exception;

	List<Movie> getListMovies(Long catId, Long exceptId) throws Exception;

	<T> void deleteEntity(T entity) throws Exception;

}
