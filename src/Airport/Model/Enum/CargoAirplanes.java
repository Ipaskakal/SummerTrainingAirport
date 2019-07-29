package Model.Enum;

public enum CargoAirplanes {

  Airplane1(3, ManufacturerEnum.Aerospace, AirplaneTypeEnum.Scramjet,  "kz124", 1990,
          100000, 3500, 25.6, 24.7, 2000,
          100000),
  Airplane2(4, ManufacturerEnum.Bombardier, AirplaneTypeEnum.Turbofan, "rr540", 2010,
          300000, 7500, 54, 35.3, 100000,
          250000);

  private int id;
  private ManufacturerEnum manufacturer;
  private AirplaneTypeEnum AirplaneType;

  private String model;
  private int year;
  private int price;
  private int maxRangeOfFlight;
  private double wingspan;
  private double fuelConsumption;
  private int fuelTankCapacity;
  private double maxCargoWeight;


  CargoAirplanes(int id, ManufacturerEnum manufacturer, AirplaneTypeEnum airplaneType, String model,
                 int year, int price, int maxRangeOfFlight, double wingspan, double fuelConsumption,
                 int fuelTankCapacity, double maxCargoWeight) {
    this.id = id;
    this.manufacturer = manufacturer;
    AirplaneType = airplaneType;
    this.model = model;
    this.year = year;
    this.price = price;
    this.maxRangeOfFlight = maxRangeOfFlight;
    this.wingspan = wingspan;
    this.fuelConsumption = fuelConsumption;
    this.fuelTankCapacity = fuelTankCapacity;
    this.maxCargoWeight = maxCargoWeight;
  }

  public int getId() {
    return id;
  }

  public ManufacturerEnum getManufacturer() {
    return manufacturer;
  }

  public AirplaneTypeEnum getAirplaneType() {
    return AirplaneType;
  }

  public String getModel() {
    return model;
  }

  public int getPrice() {
    return price;
  }

  public int getYear() {
    return year;
  }

  public int getMaxRangeOfFlight() {
    return maxRangeOfFlight;
  }

  public double getWingspan() {
    return wingspan;
  }

  public double getFuelConsumption() {
    return fuelConsumption;
  }

  public int getFuelTankCapacity() {
    return fuelTankCapacity;
  }

  public double getMaxCargoWeight() {
    return maxCargoWeight;
  }
}
