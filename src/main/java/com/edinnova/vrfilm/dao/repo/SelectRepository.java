package com.edinnova.vrfilm.dao.repo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.type.Type;

public interface SelectRepository {
	<T> T getEntityById(Class<T> clazz, Serializable id) throws Exception;

	<T> T getEntityByHQL(String hql, List<Object> params) throws Exception;

	<T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params)
			throws Exception;

	<T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws Exception;

	<T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params)
			throws Exception;

	<T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws Exception;

	<T> List<T> getListByHQL(String hql, List<Object> params) throws Exception;

	<T> List<T> getListByHQLPaginated(String selectHql, String countHql,
			List<Object> selectParams, List<Object> countParams,
			KPaging<T> paging) throws Exception;

	<T> List<T> getListByHQLPaginated(String hql, List<Object> params,
			KPaging<T> paging) throws Exception;

	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params)
			throws Exception;

	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws Exception;

	<T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging) throws Exception;

	<T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception;

	<T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging) throws Exception;

	<T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception;

	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params)
			throws Exception;

	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws Exception;

	// <T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
	// Type[] fieldTypes, String sql, List<Object> params,
	// CacheConfig cacheConfig);
	<T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging) throws Exception;

	<T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception;

	// <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
	// String[] fieldNames, Type[] fieldTypes, String selectSql,
	// String countSql, List<Object> selectParams,
	// List<Object> countParams, KPaging<T> paging, CacheConfig cacheConfig);
	int countByHQL(String countHql, List<Object> countParams) throws Exception;

	int countBySQL(String countSql, List<Object> countParams) throws Exception;

	int countBySQL(String countSql, List<Object> countParams,
			List<Class<?>> synchronizedClass) throws Exception;

	BigDecimal countBySQLReturnBigDecimal(String countSql,
			List<Object> countParams) throws Exception;

	<T> List<T> getListByNamedQuery(String namedQuerySQL, List<Object> params)
			throws Exception;

	Object getObjectByQuery(Type objectType, String sql, List<Object> params) throws Exception;

	SessionFactory getSessionFactory();

	<T> List<T> getListByNamedQuery(Class<T> clazz, String namedQuerySQL,
			List<Object> params) throws Exception;

	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params,
			int maxResult) throws Exception;

	<T> List<T> getListBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass, Integer maxResult)
			throws Exception;

	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params,
			List<Class<?>> synchronizedClass, Integer maxResult)
			throws Exception;

	<T> List<T> getListByQueryAndScalar(Class<T> clazz, String[] fieldNames,
			Type[] fieldTypes, String sql, List<Object> params,
			Integer maxResult) throws Exception;

	List<Boolean> checkExistBySQL(List<String> lstSql,
			List<List<Object>> lstParams) throws Exception;

	List<Object> getDataToListPaginated(String sql, List<Object> params,
			int fetchSize, int firstResult) throws Exception;

	void callStoreProcedure(String spName, List<Object> params,
			List<Object> outs);

	<T> T getEntityByCondition(Class<T> clazz, List<Criterion> listCondition) throws Exception;

	<T> List<T> getListByCondition(Class<T> clazz, List<Criterion> listCondition, Order order, Integer maxResult) throws Exception;

	<T> List<T> getListByCondition(Class<T> clazz, List<Criterion> listCondition, Order order) throws Exception;

	<T> List<T> getListByCondition(Class<T> clazz, List<Criterion> listCondition, Order order, KPaging<T> paging) throws Exception;

	<T> Long countObject(Class<T> clazz, List<Criterion> listCondition);
}