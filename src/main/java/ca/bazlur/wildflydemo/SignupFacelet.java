package ca.bazlur.wildflydemo;

import static jakarta.faces.application.StateManager.IS_BUILDING_INITIAL_STATE;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.html.HtmlBody;
import jakarta.faces.component.html.HtmlCommandButton;
import jakarta.faces.component.html.HtmlForm;
import jakarta.faces.component.html.HtmlInputSecret;
import jakarta.faces.component.html.HtmlInputText;
import jakarta.faces.component.html.HtmlOutputLabel;
import jakarta.faces.component.html.HtmlPanelGrid;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.facelets.Facelet;
import java.io.IOException;

@View("/signup.xhtml")
@ApplicationScoped
public class SignupFacelet extends Facelet {

  @Override
  public void apply(final FacesContext context, final UIComponent root) throws IOException {
    if (!context.getAttributes().containsKey(IS_BUILDING_INITIAL_STATE)) {
      return;
    }

    var elContext = context.getELContext();
    var expressionFactory = context.getApplication().getExpressionFactory();

    var components = new ComponentBuilder(context);
    final var children = root.getChildren();
    var output = new UIOutput();
    output.setValue("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    children.add(output);

    HtmlBody body = components.create(HtmlBody.COMPONENT_TYPE);
    children.add(body);

    HtmlForm form = components.create(HtmlForm.COMPONENT_TYPE);
    form.setId("signup");

    body.getChildren().add(form);

    final HtmlPanelGrid grid = components.create(HtmlPanelGrid.COMPONENT_TYPE);

    form.getChildren().add(grid);

    HtmlOutputLabel firstNameLabel = components.create(HtmlOutputLabel.COMPONENT_TYPE);
    firstNameLabel.setValue("Enter your first name");
    grid.getChildren().add(firstNameLabel);

    HtmlInputText firstNameInputText = components.create(HtmlInputText.COMPONENT_TYPE);
    firstNameInputText.setId("firstName");
    firstNameInputText.setValueExpression("value",
        expressionFactory.createValueExpression(elContext, "#{signupBean.firstName}",
            String.class));
    grid.getChildren().add(firstNameInputText);

    HtmlOutputLabel lastNameLabel = components.create(HtmlOutputLabel.COMPONENT_TYPE);
    lastNameLabel.setValue("Enter your last name");
    grid.getChildren().add(lastNameLabel);

    HtmlInputText lastNameInputText = components.create(HtmlInputText.COMPONENT_TYPE);
    lastNameInputText.setValueExpression("value",
        expressionFactory.createValueExpression(elContext, "#{signupBean.lastName}", String.class));
    grid.getChildren().add(lastNameInputText);

    HtmlOutputLabel emailLabel = components.create(HtmlOutputLabel.COMPONENT_TYPE);
    emailLabel.setValue("Enter your Email address");
    grid.getChildren().add(emailLabel);

    HtmlInputText emailInputText = components.create(HtmlInputText.COMPONENT_TYPE);
    emailInputText.setValueExpression("value",
        expressionFactory.createValueExpression(elContext, "#{signupBean.email}", String.class));
    grid.getChildren().add(emailInputText);

    HtmlOutputLabel passwordLabel = components.create(HtmlOutputLabel.COMPONENT_TYPE);
    passwordLabel.setValue("Enter your password");
    grid.getChildren().add(passwordLabel);

    HtmlInputSecret passwordInputText = components.create(HtmlInputSecret.COMPONENT_TYPE);
    passwordInputText.setValueExpression("value",
        expressionFactory.createValueExpression(elContext, "#{signupBean.password}", String.class));
    grid.getChildren().add(passwordInputText);

    HtmlInputText acceptTerms = components.create(HtmlInputText.COMPONENT_TYPE);
    acceptTerms.setType("checkbox");
    passwordInputText.setValueExpression("value",
        expressionFactory.createValueExpression(elContext, "#{signupBean.acceptTerms}",
            String.class));
    grid.getChildren().add(acceptTerms);

    HtmlOutputLabel acceptTermsLabel = components.create(HtmlOutputLabel.COMPONENT_TYPE);
    acceptTermsLabel.setValue("I agree to the terms and conditions");
    grid.getChildren().add(acceptTermsLabel);

    var sigupUpButton = expressionFactory.createMethodExpression(elContext,
        "#{signupBean.action}", Void.class, new Class[0]);
    HtmlCommandButton actionButton = components.create(HtmlCommandButton.COMPONENT_TYPE);
    actionButton.setId("button");
    actionButton.setValue("Signup");
    actionButton.setActionExpression(sigupUpButton);

    grid.getChildren().add(actionButton);

    output = new UIOutput();
    output.setValue("</html>");
    children.add(output);
  }
}
