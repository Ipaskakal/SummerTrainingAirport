package Controller.Menu;

import View.TextConstants;
import View.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

  private View view;
  private List<MenuEntry> entries = new ArrayList<>();
  private boolean isExit = false;
  private Scanner scanner;
  private String menuName;

  public Menu(View view, Scanner scanner, String menuName) {
    this.menuName = menuName;
    this.view = view;
    this.scanner = scanner;

    entries.add(new MenuEntry(TextConstants.EXIT) {
      @Override
      public void run() {
        isExit = true;
      }
    });
  }

  public void setExit(boolean exit) {
    isExit = exit;
  }

  public void run() {
    while (!isExit) {
      printMenu();
      int choice = -1;
      while (choice < 1 || choice > entries.size()) {
        view.printMessage(TextConstants.SELECT_THE_MENU_ITEM);
        while (!scanner.hasNextInt()) {
          scanner.next();
          view.printMessage(TextConstants.SELECT_THE_MENU_ITEM);
        }
        choice = scanner.nextInt();
      }
      MenuEntry entry = entries.get(choice - 1);
      entry.run();
    }
  }

  public void addEntry(MenuEntry entry) {
    int index = entries.size() - 1;
    entries.add(index, entry);
  }


  private void printMenu() {
    view.printMessage("\n", TextConstants.TABULATION, menuName);
    for (int i = 0; i < entries.size(); i++) {
      MenuEntry entry = entries.get(i);
      view.printMessage(Integer.toString(i + 1), TextConstants.DASH, entry.getTitle());
    }
    view.printMessage(TextConstants.NEW_LINE);
  }

}
