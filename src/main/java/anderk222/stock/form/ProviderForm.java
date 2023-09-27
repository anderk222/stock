/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import anderk222.stock.model.Provider;
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

    private Long id;
    private String ruc;
    
    private long personId;

    private String fullName;
    
    public ProviderForm(Provider provider){

        this.id = provider.getId();
        this.fullName = provider.getPerson().getFullName();
        this.personId = provider.getPerson().getId();
        this.ruc = provider.getRuc();

    }

}
