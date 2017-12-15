/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ghassan
 */
@Entity
public class Inscription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Candidat candidat = new Candidat();
    @ManyToOne
    private Filiere filiere = new Filiere();
    @ManyToOne
    private AnneeUniversitaire anneeUniversitaire = new AnneeUniversitaire();
    @OneToMany(mappedBy = "inscription")
    private List<InscriptionItem> inscriptionItems;
    private int etat; //1 complete 0 incomplete
    
    public Inscription() {
    }
    
    public Inscription(Long id) {
        this.id = id;
    }

    public Inscription(Long id, int etat) {
        this.id = id;
        this.etat = etat;
    }
    
    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public AnneeUniversitaire getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(AnneeUniversitaire anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public List<InscriptionItem> getInscriptionItems() {
        return inscriptionItems;
    }

    public void setInscriptionItems(List<InscriptionItem> inscriptionItems) {
        this.inscriptionItems = inscriptionItems;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscription)) {
            return false;
        }
        Inscription other = (Inscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", candidat=" + candidat + ", filiere=" + filiere + ", anneeUniversitaire=" + anneeUniversitaire + ", etat=" + etat + '}';
    }

    

    
    
}
