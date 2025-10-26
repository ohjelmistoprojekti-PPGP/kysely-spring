package ppg.spring.springrepository.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity

public class Kysymys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

     //attribuutit
    private Long kysymysId;
    private String kysymysTeksti;

    @ManyToOne
    @JoinColumn(name = "kyselyId")
    private Kysely kysely;

    //konstruktorit

    public Kysymys(String kysymysTeksti) {
        this.kysymysTeksti = kysymysTeksti;
    }

    public Kysymys() {
        this.kysymysTeksti = null;
    }


    //getit ja setit
    
    public Long getKysymysId() {
        return kysymysId;
    }

    public void setKysymysId(Long kysymysId) {
        this.kysymysId = kysymysId;
    }

    public String getKysymysTeksti() {
        return kysymysTeksti;
    }

    public void setKysymysTeksti(String kysymysTeksti) {
        this.kysymysTeksti = kysymysTeksti;
    }
  

    //toString

   @Override
    public String toString() {
        return "Kysymys [kysymysId=" + kysymysId + ", kysymysTeksti=" + kysymysTeksti + "]";
    }

}
