package ca.bazlur.wildflydemo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SignupService {

  public void save(SignupBean signupBean) {
    System.out.println("Saving bean");
  }
}
