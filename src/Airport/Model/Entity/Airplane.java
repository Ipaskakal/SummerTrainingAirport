package Model.Entity;

import Model.Enum.AirplaneTypeEnum;
import Model.Enum.ManufacturerEnum;

public class Airplane extends Entity {

  private ManufacturerEnum manufacturer;
  private AirplaneTypeEnum AirplaneType;

  private String model;
  private int year;
  private int price;
  private int maxRangeOfFlight;
  private double wingspan;
  private double fuelConsumption;
  private int fuelTankCapacity;

  public int getFuelTankCapacity() {
    return fuelTankCapacity;
  }

  void setFuelTankCapacity(int fuelTankCapacity) {
    this.fuelTankCapacity = fuelTankCapacity;
  }

  public void setWingspan(double wingspan) {
    this.wingspan = wingspan;
  }

  public void setFuelConsumption(double fuelConsumption) {
    this.fuelConsumption = fuelConsumption;
  }

  public double getWingspan() {
    return wingspan;
  }

  public double getFuelConsumption() {
    return fuelConsumption;
  }

  public String getModel() {
    return model;
  }

  void setModel(String model) {
    this.model = model;
  }

  public int getPrice() {
    return price;
  }

  void setPrice(int price) {
    this.price = price;
  }

  public int getMaxRangeOfFlight() {
    return maxRangeOfFlight;
  }

  void setMaxRangeOfFlight(int maxRangeFlight) {
    this.maxRangeOfFlight = maxRangeFlight;
  }

  public ManufacturerEnum getManufacturer() {
    return manufacturer;
  }

  public int getYear() {
    return year;
  }

  void setYear(int year) {
    this.year = year;
  }

  void setManufacturer(ManufacturerEnum manufacturer) {
    this.manufacturer = manufacturer;
  }

  public AirplaneTypeEnum getAirplaneType() {
    return AirplaneType;
  }

  void setAirplaneType(AirplaneTypeEnum AirplaneType) {
    this.AirplaneType = AirplaneType;
  }

  public static InnerBuilderAirplane<? extends Airplane, ?> builder() {
    return new FinalBuilderAirplane();
  }

  public static class InnerBuilderAirplane<T extends Airplane, RetBuilder extends InnerBuilderAirplane<? extends T, ?>> extends
      InnerBuilderEntity<T, RetBuilder> {

    InnerBuilderAirplane(T created) {
      super(created);
    }

    public RetBuilder model(String model) {
      getNested().setModel(model);
      return self();
    }

    public RetBuilder price(int price) {
      getNested().setPrice(price);
      return self();
    }

    public RetBuilder maxRangeOfFlight(int RangeOfFlight) {
      getNested().setMaxRangeOfFlight(RangeOfFlight);
      return self();
    }

    public RetBuilder AirplaneManufacturer(ManufacturerEnum manufacturer) {
      getNested().setManufacturer(manufacturer);
      return self();
    }

    public RetBuilder AirplaneType(AirplaneTypeEnum AirplaneType) {
      getNested().setAirplaneType(AirplaneType);
      return self();
    }

    public RetBuilder year(int year) {
      getNested().setYear(year);
      return self();
    }

    public RetBuilder wingspan(double wingspan){
      getNested().setWingspan(wingspan);
      return self();
    }

    public RetBuilder fuelConsumption(double fuelConsumption) {
      getNested().setFuelConsumption(fuelConsumption);
      return self();
    }

    public RetBuilder fuelTankCapacity(int fuelTankCapacity){
      getNested().setFuelTankCapacity(fuelTankCapacity);
      return self();
    }

  }

  private static class FinalBuilderAirplane extends InnerBuilderAirplane<Airplane, FinalBuilderAirplane> {

    private FinalBuilderAirplane() {
      super(new Airplane());
      injectReturnBuilder(this);
    }
  }
}
