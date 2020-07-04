package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Color;
import com.finder.ecoshop.repository.ColorDao;

@Repository
public class ColorDaoImpl extends GenericDaoImpl<Color, Long> implements ColorDao{

}
