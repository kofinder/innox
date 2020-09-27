package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ProductArtwork;
import com.finder.innox.repository.ProductArtworkDao;

@Repository
public class ProductArtworkDaoImpl extends GenericDaoImpl<ProductArtwork, Long> implements ProductArtworkDao {

}
