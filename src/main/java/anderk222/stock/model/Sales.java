/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author tanki
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String invoice = UUID.randomUUID().toString();

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "person_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        private Person person;

        @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "sales", fetch = FetchType.LAZY)
        @OnDelete(action = OnDeleteAction.CASCADE)
        private List<ProductSales> productSalesList = new ArrayList<>();

        @CreationTimestamp
        private Instant createdAt;

        @Transient
        private BigDecimal total = BigDecimal.ZERO;

        public void addProduct(Product product,int count ) {

                ProductSales productSales = new ProductSales();

                productSales.setSales(this); 

                productSales.setProduct(product);

                productSales.setCount(count);

                this.productSalesList.add(productSales);

        }

        public Sales(Person person){

                this.person = person;
        }

}