package View;

import Controller.Menu.MyLocale;
import java.util.Locale;
import java.util.ResourceBundle;

public class View {

  private static String LOCALE_SOURCE = "locale";
  public static ResourceBundle bundle = ResourceBundle.getBundle(
      LOCALE_SOURCE, new Locale(MyLocale.getLocale().getLanguage(), MyLocale.getLocale().getCountry()));


  public void findResourceLocale() {
    bundle = ResourceBundle.getBundle(
        LOCALE_SOURCE, new Locale(MyLocale.getLocale().getLanguage(), MyLocale.getLocale().getCountry()));
  }

  public void printMessage(String...messages) {
    StringBuilder outMessage = new StringBuilder();
    for (String message: messages) {
      if (bundle.containsKey(message)) {
        outMessage.append(bundle.getString(message)).append(" ");
      } else {
        outMessage.append(message).append(" ");
      }
    }
    System.out.println(outMessage);
  }

}
