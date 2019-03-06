package fr.epsi.tp.persistance.bean;

import java.time.LocalDate;
import java.util.Collection;

public class Commande extends AbstractPersistance<Long>{

  private Collection<CommandeLigne> lignes;
  
  private LocalDate dateCreation;

  public Collection<CommandeLigne> getLignes() {
    return lignes;
  }

  public void setLignes(Collection<CommandeLigne> lignes) {
    this.lignes = lignes;
  }

  public LocalDate getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(LocalDate dateCreation) {
    this.dateCreation = dateCreation;
  }
  
  
}
