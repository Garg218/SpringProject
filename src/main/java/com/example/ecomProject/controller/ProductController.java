package com.example.ecomProject.controller;

import com.example.ecomProject.DTO.PaginatedResponse;
import com.example.ecomProject.DTO.ProductRequest;
import com.example.ecomProject.DTO.ProductResponse;
import com.example.ecomProject.model.Product;
import com.example.ecomProject.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet()
    {
        return "Hello Khushi";
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCSRF(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }
    //get api to get all products
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
        return  ResponseEntity.ok(service.getAllProducts());
    }

    //get api implementing pagination and returning Page<Product>
    @GetMapping("/productsPaginationAndSorting")
    public Page<ProductResponse> getAllProductsUsingPagination(@RequestParam(defaultValue ="1") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String order) {
        return  service.getAllProductsUsingPagination(page-1, size,sortBy,order);
    }


    @GetMapping("/productsPaginationAndSortingAndFilter")
    public PaginatedResponse<ProductResponse> getAllProductsUsingPaginationSortingFiltering(@RequestParam String category, Pageable pageable) {
        return  service.getAllProductsUsingPaginationSortingFiltering(category,pageable);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProductBtId(@PathVariable @Positive int id)
    {

        return  ResponseEntity.ok(service.getProductById(id));


    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest)  {


            ProductResponse p = service.addProduct(productRequest);
            return new ResponseEntity<>(p,HttpStatus.CREATED);

    }

//    @GetMapping("/product/{id}/image")
//    public ResponseEntity<byte[]> getImage(@PathVariable @Positive@Positive int id)
//    {
//        ProductResponse p = service.getProductById(id);
//        byte[] image = p.getImageDesc();
//
//        return ResponseEntity.ok()
//                .body(image);
//
//    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id, @Valid @RequestBody ProductRequest product)
    {
            ProductResponse p1 = service.updateProduct(id,product);

        return new ResponseEntity<>(p1,HttpStatus.OK);
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable @Positive int id)
    {

            service.deleteProduct(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public Page<ProductResponse> search(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean available,
            Pageable pageable
    ) {
        return service.searchProducts(category, available, pageable);
    }

}
