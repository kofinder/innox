package com.finder.innox.repository.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.CustomItemSize;
import com.finder.innox.repository.CustomItemSizeDao;

@SuppressWarnings({ "deprecation", "rawtypes" })
@Repository
public class CustomItemSizeDaoImpl extends GenericDaoImpl<CustomItemSize, Long> implements CustomItemSizeDao {

	@Override
	public void deleteDataByCustomItemId(long customIemId) {
		String sqlStr = "DELETE FROM custom_item_size WHERE custom_item_id = " + customIemId;
		Query query = this.getCurrentSession().createSQLQuery(sqlStr);
		query.executeUpdate();
	}

}
