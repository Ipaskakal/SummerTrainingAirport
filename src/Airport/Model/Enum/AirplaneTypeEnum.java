package Model.Enum;

import View.View;

public enum AirplaneTypeEnum {

  Rocket("ROCKET"),
  Scramjet("SCRAMJET"),
  Ramjet("RAMJET"),
  Turbofan("TURBOFAN"),
  Jet("JET");


  private String type;

  AirplaneTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return View.bundle.getString(this.type);
  }
}
