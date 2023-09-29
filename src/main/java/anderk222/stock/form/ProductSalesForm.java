/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author tanki
 */
@Data
public class ProductSalesForm {

    private Long id;
    
    @NotEmpty(message = "Count es requerido")
    @Size(min = 1, message = "Count debe tener por lo menos 1 caracter")
    private int count=1;
    
    private ProductForm product;
    
    private SalesForm sales;

    
}
