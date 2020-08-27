package com.finder.innox.core.services.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.finder.innox.core.services.ProductSizeService;

@Service
@Transactional
public class ProductSizeServiceImpl implements ProductSizeService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
