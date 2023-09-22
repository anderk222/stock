/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 *
 * @author tanki
 */

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    private Object id;
    private String field;
    private  String resource;

    public ResourceNotFoundException(Object id, String field, String resource) {
    
        super(resource +" not found with "+field+ " "+field);
        
        this.id=id;
        this.field=field;
        this.resource =resource;
        
    }
    
    
}
