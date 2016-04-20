package com.edinnova.vrfilm.dao.repo;

import java.io.Serializable;
import java.util.List;

public interface InsertRepository {
	<T> T create(T object) throws Exception;

	<T> T createAndFlush(T object) throws Exception;

	void update(Object object) throws Exception;

	void updateAndFlushData(Object object) throws Exception;

	int executeSQLQuery(String sql, List<Object> params) throws Exception;

	void delete(Object object) throws Exception;

	<T> T getEntityById(Class<T> clazz, Serializable id) throws Exception;

	<T> List<T> create(List<T> lstObject) throws Exception;

	<T> List<T> update(List<T> lstObject) throws Exception;

	void executeSP(String spName, List<SpParam> inParams,
			List<SpParam> outParams) throws Exception;
}