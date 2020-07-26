package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.City;
import com.finder.ecoshop.repository.CityDao;

@Repository
public class CityDaoImpl extends GenericDaoImpl<City, Long> implements CityDao{

}
