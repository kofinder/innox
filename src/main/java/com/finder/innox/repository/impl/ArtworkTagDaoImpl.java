package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ArtworkTag;
import com.finder.innox.repository.ArtworkTagDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ArtworkTagDaoImpl extends GenericDaoImpl<ArtworkTag, Long> implements ArtworkTagDao {

	@Override
	public List<ArtworkTag> getArtworkTagByCategoryId(long categoryId) {
		Criteria c = this.getCurrentSession().createCriteria(ArtworkTag.class);
		c.add(Restrictions.eq("artworkCategory.seq", categoryId));
		return c.list();
	}

}
