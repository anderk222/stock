/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anderk222.stock.dto;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author tanki
 */
public interface SalesProjection {

    Long getId();

    @Value("#{target.invoice}")
    String getInvoice();

    @Value("#{target.names+' '+ target.lastnames}")
    String getFullName();

    @Value("#{target.person_id}")
    String getIdPerson();

    @Value("#{target.created_at}")
    Instant getCreatedAt();
}
