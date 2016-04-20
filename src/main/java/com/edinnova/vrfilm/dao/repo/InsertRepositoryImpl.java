package com.edinnova.vrfilm.dao.repo;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;

public class InsertRepositoryImpl extends AbstractRepository implements InsertRepository {
	private static Logger logger = Logger.getLogger(InsertRepositoryImpl.class);
	/** The session factory. */
	private SessionFactory insertSessionFactory;

	public InsertRepositoryImpl(SessionFactory insertSessionFactory) {
		this.insertSessionFactory = insertSessionFactory;
	}

	
	public <T> T create(T object) throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.persist(object);
			return (T) object;
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> create(List<T> lstObject) throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			for (T obj : lstObject) {
				if (obj != null)
					session.persist(obj);
			}
			return lstObject;
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> List<T> update(List<T> lstObject) throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			int i = 0;
			for (T obj : lstObject) {
				i++;
				session.update(obj);
				if (i % 50 == 0) {
					session.flush();
					session.clear();
				}
			}
			return lstObject;
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public void update(Object object) throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.merge(object);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public void updateAndFlushData(Object object) throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.merge(object);
			session.flush();
			session.refresh(object);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public int executeSQLQuery(String sql, List<Object> params)
			throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sql);
			addParameters(query, params);
			return query.executeUpdate();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public void delete(Object object) throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.delete(object);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public <T> T getEntityById(Class<T> clazz, Serializable id)
			throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			return clazz.cast(session.get(clazz, id));
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	
	public void executeSP(final String spName, final List<SpParam> inParams,
			final List<SpParam> outParams) throws Exception {
		try {
			int numParam = inParams.size() + outParams.size();
			final StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("{call ");
			stringBuilder.append(spName);
			if (numParam > 0) {
				stringBuilder.append("(?");
				for (int i = 1; i < numParam; i++) {
					stringBuilder.append(", ?");
				}
				stringBuilder.append(")");
			}
			stringBuilder.append("}");
			insertSessionFactory.getCurrentSession().doReturningWork(
					new ReturningWork<Boolean>() {
						public Boolean execute(Connection con)
								throws SQLException {
							try {
								CallableStatement st = con
										.prepareCall(stringBuilder.toString());
								if (inParams.size() > 0) {
									for (SpParam param : inParams) {
										setParam(st, param.getParamIndex(),
												param.getSqlType(),
												param.getValue());
									}
								}
								if (outParams.size() > 0) {
									for (SpParam param : outParams) {
										st.registerOutParameter(
												param.getParamIndex(),
												param.getSqlType());
									}
								}
								st.executeUpdate();
								for (SpParam outParam : outParams) {
									outParam.setValue(getValue(st,
											outParam.getParamIndex(),
											outParam.getSqlType()));
								}
							} catch (Exception e) {
								logger.error(e);
								return false;
							}
							return true;
						}
					});
		} catch (HibernateException e) {
			logger.error(e);
			throw new Exception(e);
		}
	}

	private void setParam(CallableStatement st, int index, int sqlType,
			Object value) throws NumberFormatException, SQLException {
		switch (sqlType) {
		case Types.INTEGER:
			st.setInt(index, (Integer) value);
			break;
		case Types.VARCHAR:
			st.setString(index, value.toString());
			break;
		case Types.NUMERIC:
			if ("Long".equals(value.getClass().getSimpleName()))
				st.setLong(index, (Long) value);
			else
				st.setInt(index, (Integer) value);
			break;
		default:
			st.setString(index, value.toString());
			break;
		}
	}

	private Object getValue(CallableStatement st, int index, int sqlType)
			throws NumberFormatException, SQLException {
		Object result;
		switch (sqlType) {
		case Types.INTEGER:
			result = st.getInt(index);
			break;
		case Types.VARCHAR:
			result = st.getString(index);
			break;
		case Types.NUMERIC:
			result = st.getInt(index);
			break;
		default:
			result = st.getString(index);
			break;
		}
		return result;
	}

	
	public <T> T createAndFlush(T object) throws Exception {
		try {
			Session session = insertSessionFactory.getCurrentSession();
			session.persist(object);
			session.flush();
			session.refresh(object);
			return (T) object;
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
	}
}