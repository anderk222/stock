/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import lombok.Data;

/**
 *
 * @author tanki
 */
@Data
public class ProductSalesForm {

    private long id;
    
    private int count=1;
    
    private ProductForm product;
    
    private SalesForm sales;

    
}
