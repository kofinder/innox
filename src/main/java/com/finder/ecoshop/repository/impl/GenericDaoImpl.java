package com.finder.ecoshop.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finder.ecoshop.repository.GenericDao;

@SuppressWarnings("deprecation")
public abstract class GenericDaoImpl<T, Key extends Serializable> implements GenericDao<T, Key> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<T> daoType;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		daoType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public T get(Key id) {
		return (T) getCurrentSession().get(daoType, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return getCurrentSession().createCriteria(daoType).list();
	}

	@Override
	public Long saveWithId(T entity) {
		return (Long) getCurrentSession().save(entity);
	}

	@Override
	public void save(T entity) {
		getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public void merge(T entity) {
		getCurrentSession().merge(entity);
	}

	@Override
	public void deleteById(long uniqueId) {
		getCurrentSession().delete(uniqueId);
	}

	@Override
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public void addAll(Collection<T> entityList) {
		for (T entity : entityList) {
			getCurrentSession().save(entity);
		}
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entityList) {
		for (T entity : entityList) {
			getCurrentSession().saveOrUpdate(entity);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> hqlExecuteQuery(String queryString, Map<String, Object> singleValueParamMap,
			Map<String, Object[]> listValueParamMap) {
		Query query = getCurrentSession().createQuery(queryString);

		if (singleValueParamMap != null) {
			for (String key : singleValueParamMap.keySet()) {
				query.setParameter(key, singleValueParamMap.get(key));
			}
		}

		if (listValueParamMap != null) {
			for (String key : listValueParamMap.keySet()) {
				query.setParameterList(key, listValueParamMap.get(key));
			}
		}

		return query.list();
	}

	@Override
	public int hqlExecuteUpdate(String queryString, Map<String, Object> singleValueParamMap,
			Map<String, Object[]> listValueParamMap) {
		Query query = getCurrentSession().createQuery(queryString);

		if (singleValueParamMap != null) {
			for (String key : singleValueParamMap.keySet()) {
				query.setParameter(key, singleValueParamMap.get(key));
			}
		}

		if (listValueParamMap != null) {
			for (String key : listValueParamMap.keySet()) {
				query.setParameterList(key, listValueParamMap.get(key));
			}
		}

		return query.executeUpdate();
	}

}
