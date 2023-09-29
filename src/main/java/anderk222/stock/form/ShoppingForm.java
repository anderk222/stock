package anderk222.stock.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingForm {
    
    @Size(min = 1, message = "Count debe ser minimo 1")
    @NotEmpty(message = "Count es requerido")
    private int count;
    @Size(min = 1, message = "Persona es incorrecta")
    @NotEmpty(message = "Persona es requerida")
    private Long person;
    
    @NotEmpty(message = "Product es requerido")
    @Size(min = 1, message = "Procuto invalido")
    private  Long product;
    
}
