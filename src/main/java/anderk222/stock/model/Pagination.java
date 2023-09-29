/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderk222.stock.model;

import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tanki
 */

@Getter
@Setter
@NoArgsConstructor 
public class Pagination<T> {
    int page;
    int limit;
    int totalPages;
    Collection<T> data;
    Long totaltems;
    int next;
    int previous; 

    public Pagination(int page, int limit, Collection<T> data) {
        this.page = page;
        this.limit = limit;
        this.data = data;
    }
    
}
