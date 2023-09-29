/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.model;

import anderk222.stock.form.ProviderForm;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tanki
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0l;
    private String ruc;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
  //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    
    
    public Provider(ProviderForm pForm){

      this.id = pForm.getId();
      this.ruc = pForm.getRuc();
      this.person = new Person(pForm.getPersonId());
      
    }


    public Provider(Long id){

      this.id = id;
    }

}
