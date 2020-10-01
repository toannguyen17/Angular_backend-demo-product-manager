package com.codegym.controller.api.v1;

import com.codegym.metadata.BaseResponse;
import com.codegym.model.Product;
import com.codegym.metadata.ProductCreateRequest;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v1/product")
public class ProductRestController {
	@Autowired
	private IProductService productService;

	/*
	 * Lấy ra danh sách sản phẩm
	 */
	@GetMapping("list")
	public ResponseEntity<BaseResponse<Iterable<Product>>> apiGetListProducts() {
		BaseResponse<Iterable<Product>> response = new BaseResponse<>();
		response.setData(productService.findAll());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * Lấy ra sản phẩm theo ID
	 */
	@GetMapping("{id}")
	public ResponseEntity<BaseResponse<Product>> apiGetProduct(@PathVariable("id") Long id) {
		BaseResponse<Product> response = new BaseResponse<>();

		Optional<Product> optionalProduct = productService.findById(id);
		if (optionalProduct.isPresent()) {
			response.setData(optionalProduct.get());
		} else {
			response.setStatus(44);
			response.setMsg("Sản phẩm không tồn tại.");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * Cập nhật sản phẩm theo ID
	 */
	@PutMapping("update")
	public ResponseEntity<BaseResponse<Product>> apiPutUpdateProduct(@RequestBody Product product) {
		BaseResponse<Product> response = new BaseResponse<>();
		response.setData(product);

		boolean exists = productService.existsById(product.getId());
		if (exists) {
			productService.save(product);
		} else {
			response.setStatus(44);
			response.setMsg("Sản phẩm không tồn tại.");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * Tạo mới sản phẩm
	 */
	@PostMapping("create")
	public ResponseEntity<BaseResponse<Product>> apiPostCreateProduct(@RequestBody ProductCreateRequest productCreateRequest) {
		BaseResponse<Product> response = new BaseResponse<>();
		Product               product  = new Product();

		product.setName(productCreateRequest.getName());
		product.setDescription(productCreateRequest.getDescription());
		product.setPrice(productCreateRequest.getPrice());

		productService.save(product);

		response.setData(product);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * Cập nhật sản phẩm theo ID
	 */
	@DeleteMapping("delete/{id}")
	public ResponseEntity<BaseResponse<Product>> apiPutUpdateProduct(@PathVariable("id") Long id) {
		BaseResponse<Product> response = new BaseResponse<>();

		Optional<Product> optionalProduct = productService.findById(id);
		if (optionalProduct.isPresent()) {
			response.setData(optionalProduct.get());
			productService.deleteById(id);
		}else{
			response.setStatus(44);
			response.setMsg("Sản phẩm không tồn tại.");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
