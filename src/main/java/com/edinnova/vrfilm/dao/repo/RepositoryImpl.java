package com.edinnova.vrfilm.dao.repo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryImpl implements IRepository {	
	@Autowired
	private InsertRepository insertRepo;
	@Autowired
	private SelectRepository selectRepo;

	public <T> T getEntityById(Class<T> clazz, Serializable id)
			throws Exception {
		return selectRepo.getEntityById(clazz, id);
	}

	public <T> T getEntityById(Class<T> clazz, Serializable id,
			boolean oneSession) throws Exception {
		if (oneSession) {
			return insertRepo.getEntityById(clazz, id);
		} else {
			return selectRepo.getEntityById(clazz, id);
		}
	}

	public <T> T getEntityByHQL(String hql, List<Object> params)
			throws Exception {
		return selectRepo.getEntityByHQL(hql, params);
	}
	
	public <T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params)
			throws Exception {
		return selectRepo.getEntityBySQL(clazz, sql, params);
	}

	public <T> T getEntityBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws Exception {
		return selectRepo.getEntityBySQL(clazz, sql, params, synchronizedClass);
	}

	public <T> List<T> getListByHQL(String hql, List<Object> params)
			throws Exception {
		return selectRepo.getListByHQL(hql, params);
	}

	public <T> List<T> getListByHQLPaginated(String selectHql, String countHql,
			List<Object> selectParams, List<Object> countParams,
			KPaging<T> paging) throws Exception {
		return selectRepo.getListByHQLPaginated(selectHql, countHql,
				selectParams, countParams, paging);
	}

	public <T> List<T> getListByHQLPaginated(String hql, List<Object> params,
			KPaging<T> paging) throws Exception {
		return selectRepo.getListByHQLPaginated(hql, params, paging);
	}

	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params) throws Exception {
		return selectRepo.getListBySQL(clazz, sql, params);
	}

	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws Exception {
		return selectRepo.getListBySQL(clazz, sql, params, synchronizedClass);
	}

	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging) throws Exception {
		return selectRepo.getListBySQLPaginated(clazz, selectSql, countSql,
				selectParams, countParams, paging);
	}

	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception {
		return selectRepo.getListBySQLPaginated(clazz, selectSql, countSql,
				selectParams, countParams, paging, synchronizedClass);
	}

	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging) throws Exception {
		return selectRepo.getListBySQLPaginated(clazz, sql, params, paging);
	}

	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception {
		return selectRepo.getListBySQLPaginated(clazz, sql, params, paging,
				synchronizedClass);
	}

	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params) throws Exception {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params);
	}

	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws Exception {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params, synchronizedClass);
	}

	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging) throws Exception {
		return selectRepo.getListByQueryAndScalarPaginated(clazz, fieldNames,
				fieldTypes, selectSql, countSql, selectParams, countParams,
				paging);
	}

	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception {
		return selectRepo.getListByQueryAndScalarPaginated(clazz, fieldNames,
				fieldTypes, selectSql, countSql, selectParams, countParams,
				paging, synchronizedClass);
	}

	
	public int countByHQL(String countHql, List<Object> countParams)
			throws Exception {
		return selectRepo.countByHQL(countHql, countParams);
	}

	
	public int countBySQL(String countSql, List<Object> countParams)
			throws Exception {
		return selectRepo.countBySQL(countSql, countParams);
	}

	
	public int countBySQL(String countSql, List<Object> countParams,
			List<Class<?>> synchronizedClass) throws Exception {
		return selectRepo.countBySQL(countSql, countParams, synchronizedClass);
	}

	
	public <T> List<T> getListByNamedQuery(String namedQuerySQL,
			List<Object> params) throws Exception {
		return selectRepo.getListByNamedQuery(namedQuerySQL, params);
	}

	
	public <T> List<T> getListByNamedQuery(Class<T> clazz,
			String namedQuerySQL, List<Object> params) throws Exception {
		return selectRepo.getListByNamedQuery(clazz, namedQuerySQL, params);
	}

	
	public SessionFactory getSessionFactory() {
		return selectRepo.getSessionFactory();
	}

	
	public <T> T create(T object) throws Exception {
		return insertRepo.create(object);
	}

	
	public <T> T createAndFlush(T object) throws Exception {
		return insertRepo.create(object);
	}

	
	public void update(Object object) throws Exception {
		insertRepo.update(object);
	}

	
	public void updateAndFlushData(Object object) throws Exception {
		insertRepo.updateAndFlushData(object);
	}

	
	public int executeSQLQuery(String sql, List<Object> params)
			throws Exception {
		return insertRepo.executeSQLQuery(sql, params);
	}

	
	public void delete(Object object) throws Exception {
		insertRepo.delete(object);
	}

	public Object getObjectByQuery(Type objectType, String sql, List<Object> params)
			throws Exception {
		return selectRepo.getObjectByQuery(objectType, sql, params);
	}

	
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params)
			throws Exception {
		return selectRepo.getFirstBySQL(clazz, sql, params);
	}

	
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws Exception {
		return selectRepo.getFirstBySQL(clazz, sql, params, synchronizedClass);
	}

	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, int maxResult) throws Exception {
		return selectRepo.getListBySQL(clazz, sql, params, maxResult);
	}

	
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws Exception {
		return selectRepo.getListBySQL(clazz, sql, params, synchronizedClass,
				maxResult);
	}

	
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws Exception {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params, synchronizedClass, maxResult);
	}

	
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, Integer maxResult) throws Exception {
		return selectRepo.getListByQueryAndScalar(clazz, fieldNames,
				fieldTypes, sql, params, null, maxResult);
	}

	
	public <T> List<T> create(List<T> lstObject) throws Exception {
		return insertRepo.create(lstObject);
	}

	
	public List<Boolean> checkExistBySQL(List<String> lstSql,
			List<List<Object>> lstParams) throws Exception {
		return selectRepo.checkExistBySQL(lstSql, lstParams);
	}

	
	public <T> List<T> update(List<T> lstObject) throws Exception {
		return insertRepo.update(lstObject);
	}

	
	public List<Object> getDataToListPaginated(String sql, List<Object> params,
			int fetchSize, int firstResult) throws Exception {
		return selectRepo.getDataToListPaginated(sql, params, fetchSize,
				firstResult);
	}

	
	public void executeSP(String spName, List<SpParam> inParams,
			List<SpParam> outParams) throws Exception {
		insertRepo.executeSP(spName, inParams, outParams);
	}

	
	public BigDecimal countBySQLReturnBigDecimal(String countSql,
			List<Object> countParams) throws Exception {
		return selectRepo.countBySQLReturnBigDecimal(countSql, countParams);
	}

	
	public void callStoreProcedure(String spName, List<Object> params,
			List<Object> outs) {
		selectRepo.callStoreProcedure(spName, params, outs);
	}

	
	public <T> T getEntityByCondition(Class<T> clazz,
			List<Criterion> listCondition) throws Exception {
		return selectRepo.getEntityByCondition(clazz, listCondition);
	}

	
	public <T> List<T> getListByCondition(Class<T> clazz,
			List<Criterion> listCondition, Order order, Integer maxResult)
			throws Exception {
		return selectRepo.getListByCondition(clazz, listCondition, order, maxResult);
	}

	
	public <T> List<T> getListByCondition(Class<T> clazz,
			List<Criterion> listCondition, Order order) throws Exception {
		return selectRepo.getListByCondition(clazz, listCondition, order);
	}

	
	public <T> List<T> getListByCondition(Class<T> clazz,
			List<Criterion> listCondition, Order order, KPaging<T> paging)
			throws Exception {
		return selectRepo.getListByCondition(clazz, listCondition, order, paging);
	}

	
	public <T> Long countObject(Class<T> clazz,
			List<Criterion> listCondition) {
		return selectRepo.countObject(clazz, listCondition);
	}
}