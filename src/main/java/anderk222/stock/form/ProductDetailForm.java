/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import java.io.Serializable;


import lombok.Data;

/**
 *
 * @author tanki
 */
@Data
 public class ProductDetailForm implements Serializable {

    private Long id;
    private String detail;
    private Integer origin;
    
    private CategoryForm category;
    
    private ProductForm product;
    
    private ProviderForm provider;
}
