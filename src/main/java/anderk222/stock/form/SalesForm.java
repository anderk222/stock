/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author tanki
 */
@Data
public class SalesForm {

        private long id;
        private String invoice;

        private PersonForm person;

        private List<ProductSalesForm> productSalesList = new ArrayList<>();

        @Transient
        private BigDecimal total = BigDecimal.ZERO;

        public void addProduct(ProductForm product) {

                ProductSalesForm productSales = new ProductSalesForm();

                productSales.setProduct(product);

                this.productSalesList.add(productSales);

        }

}