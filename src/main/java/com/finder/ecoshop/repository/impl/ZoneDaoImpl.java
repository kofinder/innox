package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Zone;
import com.finder.ecoshop.repository.ZoneDao;

@Repository
public class ZoneDaoImpl extends GenericDaoImpl<Zone, Long> implements ZoneDao{

}
