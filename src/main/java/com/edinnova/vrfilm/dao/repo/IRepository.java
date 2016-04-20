package com.edinnova.vrfilm.dao.repo;

import java.io.Serializable;

public interface IRepository extends SelectRepository, InsertRepository {
	<T> T getEntityById(Class<T> clazz, Serializable id, boolean oneSession)
			throws Exception;
}