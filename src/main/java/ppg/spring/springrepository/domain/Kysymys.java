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
    private Long kysymysId;

    // attribuutit
    private String kysymysTeksti;

    @ManyToOne
    @JoinColumn(name = "kyselyId")
    private Kysely kysely;

    // konstruktorit
    public Kysymys(String kysymysTeksti, Kysely kysely) {
        this.kysymysTeksti = kysymysTeksti;
        this.kysely = kysely;
    }

    public Kysymys() {
        this.kysymysTeksti = null;
        this.kysely = null;
    }

    // getit ja setit
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

    public Kysely getKysely() {
        return kysely;
    }

    public void setKysely(Kysely kysely) {
        this.kysely = kysely;
    }

    @Override
    public String toString() {
        return "Kysymys [kysymysId=" + kysymysId + ", kysymysTeksti=" + kysymysTeksti + ", kysely=" + kysely + "]";
    }

}
