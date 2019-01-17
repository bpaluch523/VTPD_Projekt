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
public class Parts {
    
    @Id
    @GeneratedValue
    private int id_p;
    private String name_p;
    private int amount;

    
    @ManyToOne
    @JoinColumn(name="model_id")
    private Model model_id;


    public Model getModel_id() {
        return model_id;
    }

    public void setModel_id(Model model_id) {
        this.model_id = model_id;
    }
    
    
    @ManyToOne
    @JoinColumn(name="mark_id")
    private Mark mark_id;

    public Mark getMark_id() {
        return mark_id;
    }

    public void setMark_id(Mark mark_id) {
        this.mark_id = mark_id;
    }
    
    
    
    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getName_p() {
        return name_p;
    }

    public void setName_p(String name_p) {
        this.name_p = name_p;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
