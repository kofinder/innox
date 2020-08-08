package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Zone;
import com.finder.innox.repository.ZoneDao;

@Repository
public class ZoneDaoImpl extends GenericDaoImpl<Zone, Long> implements ZoneDao{

}
