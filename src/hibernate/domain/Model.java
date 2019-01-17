package hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Bartek
 */
@Entity
public class Model {
    
    @Id
    @GeneratedValue
    private int id_mod;
    private String name_mod;
    
    @ManyToOne
    @JoinColumn(name="mark_id")
    private Mark mark_id;

    public Mark getMark_id() {
        return mark_id;
    }

    public void setMark_id(Mark mark_id) {
        this.mark_id = mark_id;
    }
    
    
    
    public int getId_mod() {
        return id_mod;
    }

    public void setId_mod(int id_mod) {
        this.id_mod = id_mod;
    }

    public String getName_mod() {
        return name_mod;
    }

    public void setName_mod(String name_mod) {
        this.name_mod = name_mod;
    }
    
}
