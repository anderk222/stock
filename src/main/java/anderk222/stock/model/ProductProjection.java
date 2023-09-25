package anderk222.stock.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductProjection {

    private Long id;

    private String name;
    private String img;
    private Integer stock = 0;
    private BigDecimal price = BigDecimal.ZERO;

    private ProductDetail detail;

    public ProductProjection(ProductSales ps) {

        this.id = ps.getProduct().getId();
        this.name = ps.getProduct().getName();

        this.img = ps.getProduct().getImg();
        this.stock = ps.getProduct().getStock();
        this.price = ps.getProduct().getPrice();

    }

}
