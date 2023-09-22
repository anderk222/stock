/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
import anderk222.stock.model.ProductDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import anderk222.stock.repository.ProductDetailRepository;

/**
 *
 * @author tanki
 */
@Service
public class ProductDetailService {

    @Autowired
    private ProductDetailRepository repository;
//
//    public Pagination<ProductDetail> search(int page, int size, String value) {
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<ProductDetail> data = repository
//                .findByNameContainingIgnoreCase(value, pageable);
//
//        Pagination<ProductDetail> res = new Pagination(page, size, data.getContent());
//
//        res.setTotalPages(data.getTotalPages());
//        res.setTotaltems(data.getTotalElements());
//
//        return res;
//    }

    public ProductDetail findByid(long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "productDetail"));

    }

//    public Pagination<ProductDetail> findByCategoryId(long id, int page, int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<ProductDetail> data = repository
//                .findByProductDetailDetailCategoryId(id,pageable);
//
//        Pagination<ProductDetail> res = new Pagination(page, size, data.getContent());
//
//        res.setTotalPages(data.getTotalPages());
//        res.setTotaltems(data.getTotalElements());
//
//        return res;
//
//    }

    public ProductDetail save(ProductDetail productDetail) {

        productDetail.setId(Long.MIN_VALUE);

        return repository.save(productDetail);

    }

    public ProductDetail update(long id, ProductDetail productDetail) {
        productDetail.setId(id);

        return repository.save(productDetail);
    }

    public ProductDetail delete(long id) {

        ProductDetail productDetail = findByid(id);

        repository.deleteById(id);

        return productDetail;

    }

}
