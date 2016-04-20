package com.edinnova.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.edinnova.entities.Category;
import com.edinnova.entities.Movie;
import com.edinnova.vrfilm.dao.repo.IRepository;

public class CommonMgrImpl implements CommonMgr {
	@Autowired
	IRepository repo;
	
	@Override
	public <T> T getEntity(Class<T> clazz, Long id) throws Exception {
		return repo.getEntityById(clazz, id);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public <T> T createEntity(T entity) throws Exception {
		return repo.create(entity);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public <T> void updateEntity(T entity) throws Exception {
		repo.update(entity);;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public <T> void deleteEntity(T entity) throws Exception {
		repo.delete(entity);
	}	
	
	@Override
	public Category getCategoryByName(String name) throws Exception {
		List<Criterion> listCondition = new ArrayList<Criterion>();
		listCondition.add(Restrictions.eq("catName", name));
		return repo.getEntityByCondition(Category.class, listCondition);
	}
	
	@Override
	public List<Category> getListCategory() throws Exception {
		List<Criterion> listCondition = new ArrayList<Criterion>();
		return repo.getListByCondition(Category.class, listCondition, Order.desc("catName"));
	}
	
	@Override
	public List<Movie> getListMovies() throws Exception {
		List<Criterion> listCondition = new ArrayList<Criterion>();
		return repo.getListByCondition(Movie.class, listCondition, Order.desc("movieName"));
	}
}
