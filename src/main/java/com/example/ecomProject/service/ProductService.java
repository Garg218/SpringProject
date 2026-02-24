package com.example.ecomProject.service;

import com.example.ecomProject.DTO.PaginatedResponse;
import com.example.ecomProject.DTO.ProductRequest;
import com.example.ecomProject.DTO.ProductResponse;
import com.example.ecomProject.controller.ProductNotFoundException;
import com.example.ecomProject.model.Product;
import com.example.ecomProject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;


    public List<ProductResponse> getAllProducts()
    {
        return repo.findAll().stream().map(this::mapResponse).toList();
    }

    public Page<ProductResponse> getAllProductsUsingPagination(int page, int size,String sortBy,String order)
    {
        Sort sort = order.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size,sort);
         return repo.findAll(pageable).map(this::mapResponse);

    }


    public PaginatedResponse<ProductResponse> getAllProductsUsingPaginationSortingFiltering(String category, Pageable pageable)
    {

        System.out.println("CATEGORY RECEIVED: " + category);

         Page<ProductResponse> dtoPage=repo.findByCategory(category,pageable).map(this::mapResponse);

         return new PaginatedResponse<>(
        dtoPage.getContent(),
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
                dtoPage.getTotalPages(),
                dtoPage.isLast()
         );

    }
    private Product getById(int id)
    {
        return repo.findById(id).orElseThrow(() -> new ProductNotFoundException("abra"));
    }

    public ProductResponse getProductById(int id)
    {
        Product p =  getById(id);

        return  mapResponse(p);
    }

    private Product ProductrequestToProduct(Product p,ProductRequest product)
    {
        p.setName(product.getName());
        p.setAvailable(product.isAvailable());
        p.setBrand(product.getBrand());
        p.setCategory(product.getCategory());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setQuantity(product.getQuantity());
        p.setReleaseDate(product.getReleaseDate());
//        p.setImageName(imageFile.getOriginalFilename());
//        p.setImageType(imageFile.getContentType());
//        p.setImageDesc(imageFile.getBytes());
        return p;
    }

    public ProductResponse addProduct(ProductRequest product)  {

        Product p = new Product();
       p= ProductrequestToProduct(p,product);
         repo.save(p);
         return mapResponse(p);
    }

    private ProductResponse  mapResponse(Product p)
    {
        ProductResponse response = new ProductResponse();
        response.setId(p.getId());
        response.setName(p.getName());
        response.setBrand(p.getBrand());
        response.setDescription(p.getDescription());
        response.setCategory(p.getCategory());
        response.setAvailable(p.getAvailable());
        response.setPrice(p.getPrice());
        response.setQuantity(p.getQuantity());
        response.setReleaseDate(p.getReleaseDate());
        return response;
    }


    public ProductResponse updateProduct(int id,ProductRequest request)  {
        Product p = getById(id);
        p= ProductrequestToProduct(p,request);
        p.setId(id);
         repo.save(p);
        return mapResponse(p);


    }

    public void deleteProduct(int id) {

        Product p = getById(id);
        repo.deleteById(id);
    }


    public Page<ProductResponse> searchProducts(
            String category,
            Boolean available,
            Pageable pageable
    ) {

        Specification<Product> spec =
                ProductSpecification.filter(category, available);

        return repo.findAll(spec, pageable)
                .map(this::mapResponse);
    }

}



