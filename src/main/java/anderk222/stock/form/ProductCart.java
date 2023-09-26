package anderk222.stock.form;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart {
 
        private long id;
        private String img;
        private String name;
        private BigDecimal price;
        private double total;
        private int count;
    
    

}
