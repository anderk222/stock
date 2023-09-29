/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import java.math.BigDecimal;

import anderk222.stock.model.Product;
import anderk222.stock.model.ProductDetail;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tanki
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {

    private Long id;
    @Size(min = 2, max = 150, message = "Nombre debe tener de 2 a 150 caracteres")
    private String name;
    @Min(0)
    private BigDecimal price = BigDecimal.ZERO;
    @NotEmpty(message = "Imagen es requerida")
    private String img;
    
    @Min(value = 1,message = "Stock es requerido")
    private int stock = 1;
    @NotEmpty(message = "Detalle es requerido")
    @Size(min = 5, message = "Detalle debe tener como minimo 4 caracteres")
    private String detail;
    private Long categoryId = 0l;
    private String categoryName;
    @Min(value =  1l, message = "Proveedor es requerido")
    private Long providerId = 0l;
    private String providerName;

    public ProductForm(Product product) {

        ProductDetail productDetail = product.getDetail();

        boolean hasDetail = productDetail != null;
        boolean hasProvider = hasDetail ? productDetail.getProvider() != null : false;
        boolean hasCategory = hasDetail ? productDetail.getCategory() != null : false;

        this.id = product.getId();
        this.name = product.getName();
        this.img = product.getImg();
        this.stock = product.getStock();
        this.detail = hasDetail ? product.getDetail().getDetail() : "";
        this.price = product.getPrice();
        this.providerId = hasProvider ? productDetail.getProvider().getId() : 0l;
        this.providerName = hasProvider ? productDetail.getProvider().getPerson().getFullName() : null;
        this.categoryName = hasCategory ? productDetail.getCategory().getName() : null;
        this.categoryId = hasCategory ? productDetail.getCategory().getId() : 0l;

    }

}
