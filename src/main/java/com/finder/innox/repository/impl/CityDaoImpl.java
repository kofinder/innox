package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.City;
import com.finder.innox.repository.CityDao;

@Repository
public class CityDaoImpl extends GenericDaoImpl<City, Long> implements CityDao{

}
