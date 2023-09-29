/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;


import anderk222.stock.model.Provider;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProviderForm {

    private Long id = 0l;

    @Size(min = 10,max=10, message = "RUC debe tener minimo 10 caracteres")
    @NotEmpty()
    private String ruc;
    
    @Min(value =  1l, message="Persona invalida")
    private Long personId = 0l;

    private String fullName;
    
    public ProviderForm(Provider provider){

        this.id = provider.getId();
        this.fullName = provider.getPerson().getFullName();
        this.personId = provider.getPerson().getId();
        this.ruc = provider.getRuc();

    }

}
