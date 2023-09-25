package anderk222.stock.util;

import java.util.Comparator;

import anderk222.stock.model.ProductSales;

public class ProductSalesSortByCount implements Comparator<ProductSales> {
    
    public int compare(ProductSales a, ProductSales b){

        return b.getCount() - a.getCount(); 

    }
}
