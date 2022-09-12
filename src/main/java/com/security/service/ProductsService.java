package com.security.service;

import com.security.dto.ProductsDto;
import com.security.entity.Products; 

import java.util.List;

public interface ProductsService {
    List<Products> listAllProducts();

    Products createProducts(ProductsDto productsDto);

    int updateProducts(Long id, ProductsDto productsDto);

    int deleteProducts(Long id);

    List<Products> listProducts(int pageNum, int pageSize);

    Products getProducts(Long id);
}
