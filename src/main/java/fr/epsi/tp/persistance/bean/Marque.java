package fr.epsi.tp.persistance.bean;

public class Marque extends AbstractPersistance<Long>{

  private String libelle;
  
  private Integer ordre;

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public Integer getOrdre() {
    return ordre;
  }

  public void setOrdre(Integer ordre) {
    this.ordre = ordre;
  }
}
