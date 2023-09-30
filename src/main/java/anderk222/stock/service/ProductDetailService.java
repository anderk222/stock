/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.service;

import anderk222.stock.exception.ResourceNotFoundException;
// import anderk222.stock.model.Pagination;
import anderk222.stock.model.ProductDetail;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // @PreAuthorize("hasAuthority('PRODUCTDEATIL_READ')")
    // public Pagination<ProductDetail> search(int page, int size, String value) {

    // Pageable pageable = PageRequest.of(page, size);

    // Page<ProductDetail> data = repository
    // .findByNameContainingIgnoreCase(value, pageable);

    // Pagination<ProductDetail> res = new Pagination(page, size,
    // data.getContent());

    // res.setTotalPages(data.getTotalPages());
    // res.setTotaltems(data.getTotalElements());

    // return res;
    // }

    @PreAuthorize("hasAuthority('PRODUCTDEATIL_READ')")
    public ProductDetail findByid(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "productDetail"));

    }

    // @PreAuthorize("hasAuthority('PRODUCTDEATIL_READ')")
    // public Pagination<ProductDetail> findByCategoryId(Long id, int page, int
    // size) {
    //
    // Pageable pageable = PageRequest.of(page, size);
    //
    // Page<ProductDetail> data = repository
    // .findByProductDetailDetailCategoryId(id,pageable);
    //
    // Pagination<ProductDetail> res = new Pagination(page, size,
    // data.getContent());
    //
    // res.setTotalPages(data.getTotalPages());
    // res.setTotaltems(data.getTotalElements());
    //
    // return res;
    //
    // }

    @PreAuthorize("hasAuthority('PRODUCTDEATIL_WRITE')")
    public ProductDetail save(ProductDetail productDetail) {

        productDetail.setId(Long.MIN_VALUE);

        return repository.save(productDetail);

    }

    @PreAuthorize("hasAuthority('PRODUCTDEATIL_WRITE')")
    public ProductDetail update(Long id, ProductDetail productDetail) {
        productDetail.setId(id);

        return repository.save(productDetail);
    }

    @PreAuthorize("hasAuthority('PRODUCTDEATIL_WRITE')")
    public ProductDetail delete(Long id) {

        ProductDetail productDetail = findByid(id);

        repository.deleteById(id);

        return productDetail;

    }

}
