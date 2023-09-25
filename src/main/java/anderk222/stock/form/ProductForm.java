/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author tanki
 */
@Data
public class ProductForm {

    private Long id;
    
    private String name;
    private BigDecimal price = BigDecimal.ZERO;
    private String img;
    private Integer stock = 0;
    private ProductDetailForm productDetail;

}
