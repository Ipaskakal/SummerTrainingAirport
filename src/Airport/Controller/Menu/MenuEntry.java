package Controller.Menu;

public abstract class MenuEntry {

  private String title;

  protected MenuEntry(String title) {
    this.title = title;
  }

  String getTitle() {
    return title;
  }

  public abstract void run();

}
