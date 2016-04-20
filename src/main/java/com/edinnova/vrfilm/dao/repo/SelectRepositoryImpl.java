package com.edinnova.vrfilm.dao.repo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.transform.Transformers;
import org.hibernate.type.AbstractStandardBasicType;
import org.hibernate.type.AbstractType;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.EnumType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

public class SelectRepositoryImpl extends AbstractRepository implements
		SelectRepository {
	private static Logger logger = Logger.getLogger(SelectRepositoryImpl.class);
	
	/** The session factory. */
	private SessionFactory selectSessionFactory;

	public SelectRepositoryImpl(SessionFactory selectSessionFactory) {
		this.selectSessionFactory = selectSessionFactory;
	}

	
	public <T> T getEntityById(Class<T> clazz, Serializable id)
			throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			return clazz.cast(session.get(clazz, id));
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> T getEntityByHQL(String hql, List<Object> params)
			throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			addParameters(query, params);
			query.setCacheable(true);
			query.setMaxResults(1);
			return (T) query.uniqueResult();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> T getEntityBySQL(Class<T> clazz, String sql, List<Object> params)
			throws Exception {
		return getEntityBySQL(clazz, sql, params, null);
	}

	
	public <T> T getEntityBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addSynchronizedClass(query, synchronizedClass);
			query.setCacheable(true);
			query.addEntity(clazz);
			query.setMaxResults(1);
			return clazz.cast(query.uniqueResult());
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}
	
	
	public <T> T getEntityByCondition(Class<T> clazz, List<Criterion> listCondition) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(clazz);
			if(listCondition != null && !listCondition.isEmpty()) {
				for(Criterion c : listCondition) {
					criteria.add(c);
				}
			}
			criteria.setCacheable(true);
			criteria.setMaxResults(1);
			return clazz.cast(criteria.uniqueResult());
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> getListByHQL(String hql, List<Object> params)
			throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			addParameters(query, params);
			query.setCacheable(true);
			return query.list();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	
	public <T> List<T> getListByHQLPaginated(String selectHql, String countHql,
			List<Object> selectParams, List<Object> countParams,
			KPaging<T> paging) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query selectQuery = session.createQuery(selectHql);
			addParameters(selectQuery, selectParams);
			selectQuery.setCacheable(true);
			if(paging == null) {
				return selectQuery.list();
			} else {
				selectQuery.setFirstResult(paging.getPage() * paging.getPageSize());
				selectQuery.setMaxResults(paging.getPageSize());
				paging.setList(selectQuery.list());
				int totalRows = countByHQL(countHql, countParams);
				paging.setTotalRows(totalRows);
				return paging.getList();
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> getListByHQLPaginated(String hql, List<Object> params,
			KPaging<T> paging) throws Exception {
		try {
			String countHql = hql.toLowerCase().trim();
			if (countHql.startsWith("select")) {
				countHql = hql.substring(countHql.indexOf("from"));
			} else {
				countHql = hql;
			}
			countHql = "select count(*) " + countHql;
			return getListByHQLPaginated(hql, countHql, params, params, paging);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params) throws Exception {
		return getListBySQL(clazz, sql, params, null);
	}

	
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, int maxResult) throws Exception {
		return getListBySQL(clazz, sql, params, null, maxResult);
	}

	
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws Exception {
		return getListBySQL(clazz, sql, params, synchronizedClass, null);
	}
	

	
	
	public <T> List<T> getListBySQL(Class<T> clazz, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addSynchronizedClass(query, synchronizedClass);
			query.setCacheable(true);
			query.addEntity(clazz);
			if (maxResult != null && maxResult > 0) {
				query.setMaxResults(maxResult);
			}
			return query.list();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}
	
	
	public <T> List<T> getListByCondition(Class<T> clazz, List<Criterion> listCondition, Order order) throws Exception {
		return getListByCondition(clazz, listCondition, order, 0);
	}
	
	
	public <T> List<T> getListByCondition(Class<T> clazz, List<Criterion> listCondition, Order order, Integer maxResult) throws Exception {
		Session session = selectSessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		if(listCondition != null && !listCondition.isEmpty()) {
			for(Criterion c : listCondition) {
				criteria.add(c);
			}
		}
		criteria.setCacheable(true);
		if(maxResult != null && maxResult > 0) {
			criteria.setMaxResults(maxResult);
		}
		if(order != null) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}
	
	
	public <T> List<T> getListByCondition(Class<T> clazz, List<Criterion> listCondition, Order order, KPaging<T> paging) throws Exception {
		Session session = selectSessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		Criteria __criteria = session.createCriteria(clazz);
		if(listCondition != null && !listCondition.isEmpty()) {
			for(Criterion c : listCondition) {
				criteria.add(c);
				__criteria.add(c);
			}
		}
		criteria.setCacheable(true);
		if(order != null) {
			criteria.addOrder(order);
		}
		if(paging != null) {
			criteria.setFirstResult(paging.getPage() * paging.getPageSize());
			criteria.setMaxResults(paging.getPageSize());
			paging.setList(criteria.list());
			Integer totalRows = ((Number)__criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			paging.setTotalRows(totalRows);
			return paging.getList();
		} else {
			return criteria.list();
		}
	}
	
	
	public <T> Long countObject(Class<T> clazz, List<Criterion> listCondition) {
		Session session = selectSessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		if(listCondition != null && !listCondition.isEmpty()) {
			for(Criterion c : listCondition) {
				criteria.add(c);
			}
		}
		long totalRows = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		return totalRows;
	}

	
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging) throws Exception {
		return getListBySQLPaginated(clazz, selectSql, countSql, selectParams,
				countParams, paging, null);
	}

	
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery selectQuery = session.createSQLQuery(selectSql);
			addParameters(selectQuery, selectParams);
			addSynchronizedClass(selectQuery, synchronizedClass);
			selectQuery.setCacheable(true);
			selectQuery.addEntity(clazz);
			if(paging == null) {
				return selectQuery.list();
			} else {
				selectQuery.setFirstResult(paging.getPage() * paging.getPageSize());
				selectQuery.setMaxResults(paging.getPageSize());
				paging.setList(selectQuery.list());
				int totalRows = countBySQL(countSql, countParams, synchronizedClass);
				paging.setTotalRows(totalRows);
				return paging.getList();
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}
	

	
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging) throws Exception {
		return getListBySQLPaginated(clazz, sql, params, paging, null);
	}

	
	public <T> List<T> getListBySQLPaginated(Class<T> clazz, String sql,
			List<Object> params, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception {
		try {
			String countSql = sql.toLowerCase().trim();
			if (countSql.startsWith("select")) {
				countSql = "select count(*) AS count "
						+ sql.substring(countSql.indexOf("from"));
			} else if (countSql.startsWith("with")) {
				countSql = "select count(*) AS count from (" + sql + ")";
			} else {
				countSql = "select count(*) AS count " + sql;
			}
			return getListBySQLPaginated(clazz, sql, countSql, params, params,
					paging, synchronizedClass);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params) throws Exception {
		return getListByQueryAndScalar(clazz, fieldNames, fieldTypes, sql,
				params, null, null);
	}

	
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, Integer maxResult) throws Exception {
		return getListByQueryAndScalar(clazz, fieldNames, fieldTypes, sql,
				params, null, maxResult);
	}

	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass)
			throws Exception {
		return getListByQueryAndScalar(clazz, fieldNames, fieldTypes, sql,
				params, synchronizedClass, null);
	}

	
	public <T> List<T> getListByQueryAndScalar(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String sql,
			List<Object> params, List<Class<?>> synchronizedClass,
			Integer maxResult) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addScalar(query, fieldNames, fieldTypes);
			addSynchronizedClass(query, synchronizedClass);
			query.setResultTransformer(Transformers.aliasToBean(clazz));
			if (maxResult != null && maxResult > 0) {
				query.setMaxResults(maxResult);
			}
			return query.list();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging) throws Exception {
		return getListByQueryAndScalarPaginated(clazz, fieldNames, fieldTypes,
				selectSql, countSql, selectParams, countParams, paging, null);
	}

	
	public <T> List<T> getListByQueryAndScalarPaginated(Class<T> clazz,
			String[] fieldNames, Type[] fieldTypes, String selectSql,
			String countSql, List<Object> selectParams,
			List<Object> countParams, KPaging<T> paging,
			List<Class<?>> synchronizedClass) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery selectQuery = session.createSQLQuery(selectSql);
			addParameters(selectQuery, selectParams);
			addScalar(selectQuery, fieldNames, fieldTypes);
			addSynchronizedClass(selectQuery, synchronizedClass);
			selectQuery.setResultTransformer(Transformers.aliasToBean(clazz));
			if(paging == null) {
				return selectQuery.list();
			} else {
				selectQuery.setFirstResult(paging.getPage() * paging.getPageSize());
				selectQuery.setMaxResults(paging.getPageSize());
				paging.setList(selectQuery.list());
				int totalRows = countBySQL(countSql, countParams, synchronizedClass);
				paging.setTotalRows(totalRows);
				return paging.getList();
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public int countByHQL(String countHql, List<Object> countParams)
			throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query countQuery = session.createQuery(countHql);
			addParameters(countQuery, countParams);
			countQuery.setCacheable(true);
			return ((Number) countQuery.uniqueResult()).intValue();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public int countBySQL(String countSql, List<Object> countParams)
			throws Exception {
		return countBySQL(countSql, countParams, null);
	}

	
	public int countBySQL(String countSql, List<Object> countParams,
			List<Class<?>> synchronizedClass) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery countQuery = session.createSQLQuery(countSql);
			addParameters(countQuery, countParams);
			addSynchronizedClass(countQuery, synchronizedClass);
			countQuery.addScalar("count", StandardBasicTypes.BIG_DECIMAL);
			countQuery.setCacheable(true);
			return ((Number) countQuery.uniqueResult()).intValue();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	
	public <T> List<T> getListByNamedQuery(String namedQuerySQL,
			List<Object> params) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			Query namedQuery = session.getNamedQuery(namedQuerySQL);
			addParameters(namedQuery, params);
			namedQuery.setCacheable(true);
			return namedQuery.list();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> getListByNamedQuery(final Class<T> clazz,
			final String namedQuerySQL, final List<Object> params)
			throws Exception {
		try {
			final Session session = selectSessionFactory.getCurrentSession();
			final Query namedQuery = session.getNamedQuery(namedQuerySQL);
			// for (int i = 0; i < fieldNames.length; i++) {
			// qSelect.addScalar(fieldNames[i], fieldTypes[i]);
			// }
			namedQuery.setResultTransformer(Transformers.aliasToBean(clazz));
			// SQLQuery sqlQuery = (SQLQuery) namedQuery;
			// sqlQuery.addScalar("theGeom",
			// Hibernate.custom(SDOGeometryType.class));
			// try {
			// CommonUtility.addScalar(namedQuery, PromotionBasicInfo.class);
			// } catch (Exception e) {
			// throw new Exception(e);
			// }
			for (int i = 0; i < params.size(); i += 3) {
				namedQuery.setParameter(params.get(i).toString(),
						params.get(i + 1),
						(AbstractStandardBasicType) params.get(i + 2));
			}
			return namedQuery.list();
		} catch (final HibernateException e) {
			logger.error(e);
			throw new Exception("Failed to getTopByNamedNativeQuery: " + e.getMessage(), e);
		}
	}
	
	public Object getObjectByQuery(Type objectType, String sql, List<Object> params)
			throws Exception {
		try {
			Session sess = selectSessionFactory.getCurrentSession();
			SQLQuery qSelect = sess.createSQLQuery(sql);
			addParameters(qSelect, params);
			qSelect.setMaxResults(1);
			qSelect.addScalar("object", objectType);
			qSelect.setCacheable(false);
			return qSelect.uniqueResult();
		} catch (final HibernateException e) {
			logger.error(e);
			throw new Exception("Failed to getObjectByQuery: " + e.getMessage(), e);
		}
	}

	public SessionFactory getSessionFactory() {
		return selectSessionFactory;
	}

	
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params)
			throws Exception {
		return getFirstBySQL(clazz, sql, params, null);
	}

	
	public <T> T getFirstBySQL(Class<T> clazz, String sql, List<Object> params,
			List<Class<?>> synchronizedClass) throws Exception {
		try {
			Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			addSynchronizedClass(query, synchronizedClass);
			query.setCacheable(true);
			query.addEntity(clazz);
			query.setMaxResults(1);
			
			List<T> list = query.list();
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public List<Boolean> checkExistBySQL(List<String> lstSql,
			List<List<Object>> lstParams) throws Exception {
		List<Boolean> rs = new ArrayList<Boolean>();
		Session session = selectSessionFactory.getCurrentSession();
		for (int i = 0; i < lstSql.size(); i++) {
			String sql = lstSql.get(i);
			sql = "select 1 as count " + sql;
			List<Object> params = lstParams.get(i);
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			query.setCacheable(true);
			query.addScalar("count", StandardBasicTypes.BIG_DECIMAL);
			Object c = query.uniqueResult();
			rs.add(c == null ? false : true);
		}
		return rs;
	}

	
	public List<Object> getDataToListPaginated(final String sql,
			final List<Object> params, final int fetchSize,
			final int firstResult) throws Exception {
		try {
			final Session session = selectSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			if (fetchSize > 0) {
				query.setMaxResults(fetchSize);
			}
			if (firstResult > 0) {
				query.setFirstResult(firstResult);
			}
			List<Object> data = query.list();
			return data;
		} catch (Exception ex) {
			logger.error(ex);
			throw new Exception(ex);
		}
	}

	
	public BigDecimal countBySQLReturnBigDecimal(String countSql,
			List<Object> countParams) throws Exception {
		Session session = selectSessionFactory.getCurrentSession();
		SQLQuery countQuery = session.createSQLQuery(countSql);
		addParameters(countQuery, countParams);
		addSynchronizedClass(countQuery, null);
		countQuery.addScalar("count", StandardBasicTypes.BIG_DECIMAL);
		countQuery.setCacheable(true);
		return (BigDecimal) countQuery.uniqueResult();
	}
	
	
	public void callStoreProcedure(String spName, List<Object> params, List<Object> outs) {
		
	}
}