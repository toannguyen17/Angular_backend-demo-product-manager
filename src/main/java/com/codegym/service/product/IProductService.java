package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.service.IServiceGeneral;

public interface IProductService extends IServiceGeneral<Product> {
	boolean existsById(Long id);
}
