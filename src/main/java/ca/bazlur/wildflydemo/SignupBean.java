package ca.bazlur.wildflydemo;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;


@ManagedBean
@Named
@RequestScoped
public class SignupBean implements Serializable {

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty
  private String email;

  @NotEmpty
  private String password;

  private boolean acceptTerms;

  @Inject
  private SignupService signupService;

  public void action() {
    signupService.save(this);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public boolean isAcceptTerms() {
    return acceptTerms;
  }

  public void setAcceptTerms(final boolean acceptTerms) {
    this.acceptTerms = acceptTerms;
  }
}
