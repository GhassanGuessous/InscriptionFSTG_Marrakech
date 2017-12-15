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

/**
 *
 * @author Ghassan
 */
@Entity
public class PieceJointe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String nom;
    private int nombre;
    @ManyToOne
    private Filiere filiere = new Filiere();
    @OneToMany(mappedBy = "pieceJointe")
    private List<InscriptionItem> inscriptionItems;
    
    public PieceJointe() {
    }

    public PieceJointe(Long id) {
        this.id = id;
    }

    public PieceJointe(Long id, String nom, int nombre) {
        this.id = id;
        this.nom = nom;
        this.nombre = nombre;
    }
    
    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<InscriptionItem> getInscriptionItems() {
        return inscriptionItems;
    }

    public void setInscriptionItems(List<InscriptionItem> inscriptionItems) {
        this.inscriptionItems = inscriptionItems;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof PieceJointe)) {
            return false;
        }
        PieceJointe other = (PieceJointe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }

    
    
}
