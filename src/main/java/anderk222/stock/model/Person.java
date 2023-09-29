/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.model;

import anderk222.stock.form.PersonForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tanki
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String names;
    private String lastnames;
    private String dni;
    private String email;

    public Person(Long id) {

        this.id = id;

    }

    public static Person fromPersonForm(PersonForm pf) {

        Person person = new Person(
                pf.getId(), pf.getNames(), pf.getLastnames(), pf.getDni(), pf.getEmail());

        return person;

    }

    public String getFullName() {

        return this.names + ' ' + this.lastnames;
    }

}
