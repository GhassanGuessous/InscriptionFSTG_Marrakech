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
import javax.persistence.OneToMany;

/**
 *
 * @author Ghassan
 */
@Entity
public class Filiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "filiere")
    private List<Inscription> inscriptions;
    @OneToMany(mappedBy = "filiere")
    private List<PieceJointe> pieceJointes;
    
    public Filiere() {
    }

    public Filiere(Long id) {
        this.id = id;
    }

    public Filiere(Long id, String nom, int nombre_places_dispo, int nombre_actuel_places_dispo) {
        this.id = id;
        this.nom = nom;
    }

    public Filiere(String nom) {
        this.nom = nom;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<PieceJointe> getPieceJointes() {
        return pieceJointes;
    }

    public void setPieceJointes(List<PieceJointe> pieceJointes) {
        this.pieceJointes = pieceJointes;
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
        if (!(object instanceof Filiere)) {
            return false;
        }
        Filiere other = (Filiere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom ;
    }

    
    
}
