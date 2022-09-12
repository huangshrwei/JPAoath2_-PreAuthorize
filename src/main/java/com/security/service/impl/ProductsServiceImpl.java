package com.security.service.impl;

import com.security.dao.ProductsRepository;
import com.security.dto.ProductsDto;
import com.security.entity.Products;
import com.security.service.ProductsService;

import io.swagger.annotations.Example;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.annotations.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {
	
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Products> listAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    @Transactional
    public Products createProducts(ProductsDto productsDto) {
    	Products saveProducts = new Products();
    	Products saveP = new Products(); 
    	saveProducts.setCategory(productsDto.getCategory());
    	saveProducts.setImage(productsDto.getImage());
    	saveProducts.setName(productsDto.getName());
    	saveProducts.setPrice(productsDto.getPrice());
    	int i = 0;
    	try {
    		saveP = productsRepository.save(saveProducts);
    		i = 1;
    	}catch (Exception e) {
    		log.info("createProducts: " + e.getMessage());
    		saveP = null;
    		i = -1;
    	}    	
        return saveP;
    }

    @Override
    @Transactional
    public int updateProducts(Long id, ProductsDto productsDto) {
    	Products saveProducts = new Products();
    	saveProducts.setProductId(id);
    	saveProducts.setCategory(productsDto.getCategory());
    	saveProducts.setImage(productsDto.getImage());
    	saveProducts.setName(productsDto.getName());
    	saveProducts.setPrice(productsDto.getPrice());
    	int i = 0;
    	try {
    		productsRepository.save(saveProducts);    	
    		i = 1;    		
    	}catch (Exception e) {
    		log.debug("createProducts: " + e);
    		i = -1;
    	}    	  	
        return i;
    }

    @Override
    @Transactional
    public int deleteProducts(Long id) {
    	int i = 0;
    	try {
    		productsRepository.deleteById(id);
    		i = 1;
    	}catch (Exception e) {
    		i = -1;
    	}
        return i;
    }

    @Override
    public List<Products> listProducts(int pageNum, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNum-1, pageSize);    	 
    	Page<Products> page = productsRepository.findAll(pageable);
    	return page.getContent();
    }

    @Override
    public Products getProducts(Long id) {
    	Optional<Products> optProducts = productsRepository.findById(id);
    	if (!optProducts.isEmpty()) {
    		Products products = optProducts.get();
    		return products;
    	}else {
    		return null;
    	}
         
    }
}
