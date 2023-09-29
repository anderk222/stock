/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import anderk222.stock.form.ProductCart;
import anderk222.stock.form.ProductForm;

/**
 *
 * @author tanki
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Basic(optional = false)
    private BigDecimal price = BigDecimal.ZERO;
    @Column(columnDefinition = "text")
    private String img;
    private Integer stock = 0;
    @OneToOne(fetch = FetchType.LAZY,cascade = { CascadeType.ALL }, mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductDetail detail;

    public Product(Long id) {
        this.id = id;
    }

    public static Product fromProductCart(ProductCart pCart) {

        Product product = new Product();

        product.setId(pCart.getId());
        product.setName(pCart.getName());
        product.setImg(pCart.getImg());
        product.setPrice(pCart.getPrice());
        return product;

    }

    public static Product fromProductForm(ProductForm pf) {

        Category category = pf.getCategoryId().equals(0l)
                ? null
                : new Category(pf.getCategoryId());

        Provider provider = pf.getProviderId().equals(0l)
                ? null
                : new Provider(pf.getProviderId());

        ProductDetail detail = new ProductDetail(provider, category);

        Product product = new Product(
                pf.getId(), pf.getName(), pf.getPrice(), pf.getImg(), pf.getStock(), detail);

        return product;

    }

}
