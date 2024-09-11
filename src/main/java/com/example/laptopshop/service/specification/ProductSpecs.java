package com.example.laptopshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.laptopshop.domain.Product;
import com.example.laptopshop.domain.Product_;

public class ProductSpecs {
    // Search with factory
    static public Specification<Product> factoryName(List<String> factoryName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.FACTORY)).value(factoryName);
    }
    // Search with target
    static public Specification<Product> targetName(List<String> targetName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.TARGET)).value(targetName);
    }
    // Search with rangePrice
    static public Specification<Product> rangePrice(double min, double max){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Product_.PRICE), min, max);
    }
}
