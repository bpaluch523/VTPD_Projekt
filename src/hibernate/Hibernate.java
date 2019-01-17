package hibernate;

import hibernate.domain.Mark;
import hibernate.domain.Model;
import hibernate.domain.Parts;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bartek
 */
public class Hibernate {
    public static void main(String[] args) {
       
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mojabaza");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        Mark mark1 = new Mark();
        mark1.setName("Audi");
        
        Mark mark2 = new Mark();
        mark2.setName("Seat");
        
        Mark mark3 = new Mark();
        mark3.setName("Skoda");
        
        Mark mark4 = new Mark();
        mark4.setName("Volkswagen");
        
        
        Model mod1 = new Model();
        mod1.setName_mod("A4");
        mod1.setMark_id(mark1);
        
        Model mod2 = new Model();
        mod2.setName_mod("A6");
        mod2.setMark_id(mark1);
        
        Model mod3 = new Model();
        mod3.setName_mod("Leon");
        mod3.setMark_id(mark2);
        
        Model mod4 = new Model();
        mod4.setName_mod("Ibiza");
        mod4.setMark_id(mark2);
        
        Model mod5 = new Model();
        mod5.setName_mod("Fabia");
        mod5.setMark_id(mark3);
        
        Model mod6 = new Model();
        mod6.setName_mod("Octavia");
        mod6.setMark_id(mark3);
        
        Model mod7 = new Model();
        mod7.setName_mod("Golf");
        mod7.setMark_id(mark4);
        
        Model mod8 = new Model();
        mod8.setName_mod("Passat");
        mod8.setMark_id(mark4);


        Parts p1=new Parts();
        p1.setAmount(35);
        p1.setName_p("Turbosprężarka K04 Hybryda");
        p1.setMark_id(mark1);
        p1.setModel_id(mod1);
        
        Parts p2=new Parts();
        p2.setAmount(63);
        p2.setName_p("Wtryskiwacze BOSCH 630cc");
        p2.setMark_id(mark1);
        p2.setModel_id(mod1);
        
        Parts p3=new Parts();
        p3.setAmount(0);
        p3.setName_p("Kute tłoki WISECO 8,5:1");
        p3.setMark_id(mark1);
        p3.setModel_id(mod2);
        
        Parts p4=new Parts();
        p4.setAmount(3);
        p4.setName_p("Zawór upustowy HKS");
        p4.setMark_id(mark1);
        p4.setModel_id(mod2);
        
        Parts p5=new Parts();
        p5.setAmount(120);
        p5.setName_p("Intercooler przedni 80x35x6");
        p5.setMark_id(mark2);
        p5.setModel_id(mod3);
        
        Parts p6=new Parts();
        p6.setAmount(5);
        p6.setName_p("Kute korbowody PRISM 20mm");
        p6.setMark_id(mark2);
        p6.setModel_id(mod3);
        
        Parts p7=new Parts();
        p7.setAmount(36);
        p7.setName_p("Tytanowe zawory SUPERTECH");
        p7.setMark_id(mark2);
        p7.setModel_id(mod4);
        
        Parts p8=new Parts();
        p8.setAmount(80);
        p8.setName_p("Turbosprężarka K03RS2");
        p8.setMark_id(mark2);
        p8.setModel_id(mod4);
        
        Parts p9=new Parts();
        p9.setAmount(56);
        p9.setName_p("Sztywny dolot TIP 3cale");
        p9.setMark_id(mark3);
        p9.setModel_id(mod5);
        
        Parts p10=new Parts();
        p10.setAmount(31);
        p10.setName_p("Zawór upustowy blow-off FORGE007");
        p10.setMark_id(mark3);
        p10.setModel_id(mod5);
        
        Parts p11=new Parts();
        p11.setAmount(3);
        p11.setName_p("Stożkowy filtr powietrza K&N");
        p11.setMark_id(mark3);
        p11.setModel_id(mod6);
        
        Parts p12=new Parts();
        p12.setAmount(1);
        p12.setName_p("Wtryskiwacze BOSCH 525cc");
        p12.setMark_id(mark3);
        p12.setModel_id(mod6);
        
        Parts p13=new Parts();
        p13.setAmount(39);
        p13.setName_p("Kolektor wydechowy HI-FLOW");
        p13.setMark_id(mark4);
        p13.setModel_id(mod7);
        
        Parts p14=new Parts();
        p14.setAmount(53);
        p14.setName_p("Turbosprężarka K03RS Hybryda");
        p14.setMark_id(mark4);
        p14.setModel_id(mod7);
        
        Parts p15=new Parts();
        p15.setAmount(9);
        p15.setName_p("Wtryskiwacze BOSCH 480cc");
        p15.setMark_id(mark4);
        p15.setModel_id(mod8);
        
        Parts p16=new Parts();
        p16.setAmount(6);
        p16.setName_p("Kute tłoki WISECO 6,5:1");
        p16.setMark_id(mark4);
        p16.setModel_id(mod8);
        
        
        
        entityManager.getTransaction().begin();
        entityManager.persist(mark1);
        entityManager.persist(mark2);
        entityManager.persist(mark3);
        entityManager.persist(mark4);
        entityManager.persist(mod1);
        entityManager.persist(mod2);
        entityManager.persist(mod3);
        entityManager.persist(mod4);
        entityManager.persist(mod5);
        entityManager.persist(mod6);
        entityManager.persist(mod7);
        entityManager.persist(mod8);
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(p3);
        entityManager.persist(p4);
        entityManager.persist(p5);
        entityManager.persist(p6);
        entityManager.persist(p7);
        entityManager.persist(p8);
        entityManager.persist(p9);
        entityManager.persist(p10);
        entityManager.persist(p11);
        entityManager.persist(p12);
        entityManager.persist(p13);
        entityManager.persist(p14);
        entityManager.persist(p15);
        entityManager.persist(p16);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }
}
