package Model.Enum;

public enum PassangerAirplanes {

  Airplane1(1, ManufacturerEnum.Boeing, AirplaneTypeEnum.Rocket, "A340", 1993, 212551251,
          8000, 54.5, 34.6 ,
          200000, 100, 150),
  Airplane2(2, ManufacturerEnum.Fokker, AirplaneTypeEnum.Ramjet,  "341", 2003, 30000120,
          6000, 43.2, 27.1,
          150000, 80, 130);

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
  private int minSeatsNumber;
  private int maxSeatsNumber;;

  PassangerAirplanes(int id, ManufacturerEnum manufacturer, AirplaneTypeEnum airplaneType,
                     String model, int year, int price, int maxRangeOfFlight, double wingspan,
                     double fuelConsumption, int fuelTankCapacity,int minSeatsNumber, int maxSeatsNumber) {
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
    this.minSeatsNumber=minSeatsNumber;
    this.maxSeatsNumber = maxSeatsNumber;
  }


  public int getMaxSeatsNumber() {
    return maxSeatsNumber;
  }

  public int getMinSeatsNumber() {
    return minSeatsNumber;
  }

  public int getFuelTankCapacity() {
    return fuelTankCapacity;
  }

  public double getFuelConsumption() {
    return fuelConsumption;
  }

  public double getWingspan() {
    return wingspan;
  }

  public int getMaxRangeOfFlight() {
    return maxRangeOfFlight;
  }

  public int getPrice() {
    return price;
  }

  public int getYear() {
    return year;
  }

  public String getModel() {
    return model;
  }

  public ManufacturerEnum getManufacturer() {
    return manufacturer;
  }

  public AirplaneTypeEnum getAirplaneType() {
    return AirplaneType;
  }

  public int getId() {
    return id;
  }
}
