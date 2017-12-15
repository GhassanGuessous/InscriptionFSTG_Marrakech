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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ghassan
 */
@Entity
public class InscriptionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Inscription inscription = new Inscription();
    @ManyToOne
    private PieceJointe pieceJointe = new PieceJointe();
    private int nombrePieceFournis;
    private int etat;//1 complete 0 incomplete
    
    public InscriptionItem() {
    }
    
    public InscriptionItem(Long id) {
        this.id = id;
    }

    public InscriptionItem(int nombrePieceFournis) {
        this.nombrePieceFournis = nombrePieceFournis;
    }
    
    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    public PieceJointe getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(PieceJointe pieceJointe) {
        this.pieceJointe = pieceJointe;
    }

    public int getNombrePieceFournis() {
        return nombrePieceFournis;
    }

    public void setNombrePieceFournis(int nombrePieceFournis) {
        this.nombrePieceFournis = nombrePieceFournis;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
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
        if (!(object instanceof InscriptionItem)) {
            return false;
        }
        InscriptionItem other = (InscriptionItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InscriptionItem{" + "id=" + id + ", inscription=" + inscription + ", pieceJointe=" + pieceJointe + ", nombrePieceFournis=" + nombrePieceFournis + ", etat=" + etat + '}';
    }

    
    
    
}
