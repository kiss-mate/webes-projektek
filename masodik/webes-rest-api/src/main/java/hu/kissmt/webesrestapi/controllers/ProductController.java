package hu.kissmt.webesrestapi.controllers;

import hu.kissmt.webesrestapi.dataaccess.IProductDAO;
import hu.kissmt.webesrestapi.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin
public class ProductController {
    private final IProductDAO _productRepo;

    public ProductController(IProductDAO productRepo) {
        _productRepo = productRepo;
    }

    @GetMapping("all")
    public ResponseEntity<List<Product>> getAllProducts() {
        var products = _productRepo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
