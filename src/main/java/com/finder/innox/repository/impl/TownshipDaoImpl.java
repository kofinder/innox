package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Township;
import com.finder.innox.repository.TownshipDao;

@Repository
public class TownshipDaoImpl extends GenericDaoImpl<Township, Long> implements TownshipDao{

}
