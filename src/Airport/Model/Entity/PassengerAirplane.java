package Model.Entity;

public class PassengerAirplane extends Airplane {

  private int minSeatsNumber;
  private int maxSeatsNumber;

  public int getMinSeatsNumber() {
    return minSeatsNumber;
  }

  public void setMinSeatsNumber(int seatsNumber) {
    this.minSeatsNumber = seatsNumber;
  }

  public int getMaxSeatsNumber() {
    return maxSeatsNumber;
  }

  public void setMaxSeatsNumber(int seatsNumber) {
    this.maxSeatsNumber = seatsNumber;
  }

  public static InnerBuilderPassengerAirplane<? extends PassengerAirplane, ?> builder() {
    return new FinalBuilderPassengerAirplane();
  }

  public static class InnerBuilderPassengerAirplane<T extends PassengerAirplane, RetBuilder extends InnerBuilderPassengerAirplane<? extends T, ?>> extends
      InnerBuilderAirplane<T, RetBuilder> {

    InnerBuilderPassengerAirplane(T created) {
      super(created);
    }

    public RetBuilder minSeatsNumber(int minSeatsNumber) {
      getNested().setMinSeatsNumber(minSeatsNumber);
      return self();
    }

    public RetBuilder maxSeatsNumber(int maxSeatsNumber) {
      getNested().setMaxSeatsNumber(maxSeatsNumber);
      return self();
    }

  }

  private static class FinalBuilderPassengerAirplane extends
      InnerBuilderPassengerAirplane<PassengerAirplane, FinalBuilderPassengerAirplane> {

    private FinalBuilderPassengerAirplane() {
      super(new PassengerAirplane());
      injectReturnBuilder(this);
    }
  }
}
