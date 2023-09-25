/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *
 * @author tanki
 */
@Data
public class PersonForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String names;
    private String lastnames;
    private String dni;
    private String email;

    public PersonForm(long id){

        this.id = id;

    }

}
