/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anderk222.stock.form;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author tanki
 */
public interface SalesProjection {
    
    
    long getId();
    String getInvoice();
    
   // @Value("#{person.names}+' '+#{person.lastnames}")
    String fullName();
    @Value("#{person.id}")
    String getIdPerson();
}
