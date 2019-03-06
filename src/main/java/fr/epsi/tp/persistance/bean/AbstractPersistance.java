package fr.epsi.tp.persistance.bean;

public class AbstractPersistance<T>{
  
  private T identifier;

  public T getIdentifier() {
    return identifier;
  }

  public void setIdentifier(T identifier) {
    this.identifier = identifier;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractPersistance other = (AbstractPersistance) obj;
    if (identifier == null) {
      if (other.identifier != null)
        return false;
    } else if (!identifier.equals(other.identifier))
      return false;
    return true;
  }
  
  

}
