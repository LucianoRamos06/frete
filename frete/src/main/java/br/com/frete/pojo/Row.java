
package br.com.frete.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author RAMOS
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Row implements Serializable{
    private Element[] elements;

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }


    
}
