/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.form;

import anderk222.stock.model.Person;
import jakarta.validation.constraints.Email;
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
public class PersonForm {

    private Long id;

    @NotEmpty(message = "Nombre es requerido")
    @Size(min = 3, max = 40, message = "Nombre debe tener entre 3 a 40 caracteres")
    private String names;
    @NotEmpty(message = "Apellido es requerido")
    @Size(min = 3, max = 40, message = "Apellido debe tener entre 3 a 40 caracteres")
    private String lastnames;

    @NotEmpty(message = "Identicficacion es requerida")
    @Size(min = 10, max = 10, message = "Identificaion debe tener 10 caracteres")
    private String dni;
    
    @Email(message = "Deber ser una direccion de correo electronico")
    @NotEmpty(message = "Email is required")
    private String email;

    public PersonForm(Long id) {

        this.id = id;

    }

    public PersonForm(Person person){

        this.id =person.getId();
        this.dni = person.getDni();
        this.lastnames = person.getLastnames();
        this.names = person.getNames();
        this.email = person.getEmail();

    }

}
