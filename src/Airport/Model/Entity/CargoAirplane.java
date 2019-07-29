package Model.Entity;

public class CargoAirplane extends Airplane {


  private double maxCargoWeight;

  public void setMaxCargoWeight(double maxCargoWeight) {
    this.maxCargoWeight = maxCargoWeight;
  }

  public double getMaxCargoWeight() {
    return maxCargoWeight;
  }

  public static InnerBuilderCargoAirplane<? extends CargoAirplane, ?> builder() {
    return new FinalBuilderCargoAirplane();
  }

  public static class InnerBuilderCargoAirplane<T extends CargoAirplane, RetBuilder extends InnerBuilderCargoAirplane<? extends T, ?>> extends
      InnerBuilderAirplane<T, RetBuilder> {

    InnerBuilderCargoAirplane(T created) {
      super(created);
    }

    public RetBuilder maxCargoWeight(double maxCargoWeight) {
      getNested().setMaxCargoWeight(maxCargoWeight);
      return self();
    }

  }

  private static class FinalBuilderCargoAirplane extends InnerBuilderCargoAirplane<CargoAirplane, FinalBuilderCargoAirplane> {
    private FinalBuilderCargoAirplane() {
      super(new CargoAirplane());
      injectReturnBuilder(this);
    }
  }

}
