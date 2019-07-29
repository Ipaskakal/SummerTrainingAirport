package Main;

import Controller.Controller;
import Model.Model;
import View.View;

public class Main {

  public static void main(String[] args) {
    new Controller(new Model(), new View()).run();
  }
}
