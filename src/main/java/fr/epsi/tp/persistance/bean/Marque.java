package fr.epsi.tp.persistance.bean;

public class Marque extends AbstractPersistance<Long>{

  private String libelle;

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

}
