/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtpd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Bartek
 */
public class PartsPOJO {
    
    
    public SimpleIntegerProperty partId = new SimpleIntegerProperty();
    public SimpleStringProperty partName = new SimpleStringProperty();
    public SimpleStringProperty markName = new SimpleStringProperty();
    public SimpleStringProperty modelName = new SimpleStringProperty();

    public Integer getPartId() {
        return partId.get();
    }
    
    public String getPartName() {
        return partName.get();
    }

    public String getMarkName() {
        return markName.get();
    }
    
    public String getModelName() {
        return modelName.get();
    }
    
}
