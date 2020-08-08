package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Color;
import com.finder.innox.repository.ColorDao;

@Repository
public class ColorDaoImpl extends GenericDaoImpl<Color, Long> implements ColorDao{

}
