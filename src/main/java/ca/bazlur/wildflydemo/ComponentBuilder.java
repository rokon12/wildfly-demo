package ca.bazlur.wildflydemo;

import jakarta.faces.context.FacesContext;

public record ComponentBuilder(FacesContext facesContext) {

  @SuppressWarnings("unchecked")
  public <T> T create(String componentType) {
    return (T) facesContext.getApplication().createComponent(facesContext, componentType, null);
  }
}
