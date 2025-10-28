package ppg.spring.springrepository.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Kysely {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kyselyId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
    @JsonIgnore

    private List<Kysymys> kysymykset = new ArrayList<>();


    // attribuutit

    private String kyselyNimi;
    private String kyselyKuvaus;
    private String luontipvm;
    private String alkamispvm;
    private String loppumispvm;

    // konstruktorit
    public Kysely(String kyselyNimi, String kyselyKuvaus, String luontipvm, String alkamispvm,
            String loppumispvm) {
        this.kyselyNimi = kyselyNimi;
        this.kyselyKuvaus = kyselyKuvaus;
        this.luontipvm = luontipvm;
        this.alkamispvm = alkamispvm;
        this.loppumispvm = loppumispvm;
    }

    public Kysely() {
        this.kyselyNimi = null;
        this.kyselyKuvaus = null;
        this.luontipvm = null;
        this.alkamispvm = null;
        this.loppumispvm = null;
    }

    // getit ja setit

    public Long getKyselyId() {
        return kyselyId;
    }

    public void setKyselyId(Long kyselyId) {
        this.kyselyId = kyselyId;
    }

    public String getKyselyNimi() {
        return kyselyNimi;
    }

    public void setKyselyNimi(String kyselyNimi) {
        this.kyselyNimi = kyselyNimi;
    }

    public String getKyselyKuvaus() {
        return kyselyKuvaus;
    }

    public void setKyselyKuvaus(String kyselyKuvaus) {
        this.kyselyKuvaus = kyselyKuvaus;
    }

    public String getLuontipvm() {
        return luontipvm;
    }

    public void setLuontipvm(String luontipvm) {
        this.luontipvm = luontipvm;
    }

    public String getAlkamispvm() {
        return alkamispvm;
    }

    public void setAlkamispvm(String alkamispvm) {
        this.alkamispvm = alkamispvm;
    }

    public String getLoppumispvm() {
        return loppumispvm;
    }

    public void setLoppumispvm(String loppumispvm) {
        this.loppumispvm = loppumispvm;
    }

    public List<Kysymys> getKysymykset() {
        return kysymykset;
    }

    public void setKysymykset(List<Kysymys> kysymykset) {
        this.kysymykset = kysymykset;
    }

    @Override
    public String toString() {
        return "Kysely [kyselyId=" + kyselyId + ", kyselyNimi=" + kyselyNimi + ", kyselyKuvaus=" + kyselyKuvaus
                + ", luontipvm=" + luontipvm + ", alkamispvm=" + alkamispvm + ", loppumispvm=" + loppumispvm + "]";
    }

}
