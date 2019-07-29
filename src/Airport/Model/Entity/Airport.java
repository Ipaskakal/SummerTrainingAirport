package Model.Entity;
import java.util.ArrayList;
import java.util.List;

public class Airport extends Entity {

  private String name;
  private List<Airplane> airplanes=new ArrayList<>();

  public void addAirplane(Airplane airplane){
    airplanes.add(airplane);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Airplane> getAirplanes() {
    return airplanes;
  }

  public void setAirplanes(List<Airplane> Airplanes) {
    this.airplanes = airplanes;
  }

  public static InnerBuilderAirport<? extends Airport, ?> builder() {
    return new FinalBuilderAirport();
  }

  public static class InnerBuilderAirport<T extends Airport, RetBuilder extends InnerBuilderAirport<? extends T, ?>> extends
      InnerBuilderEntity<T, RetBuilder> {

    InnerBuilderAirport(T created) {
      super(created);
    }

    public RetBuilder name(String name) {
      getNested().setName(name);
      return self();
    }

    public RetBuilder Airplanes(List<Airplane> Airplanes) {
      getNested().setAirplanes(Airplanes);
      return self();
    }

  }

  private static class FinalBuilderAirport extends InnerBuilderAirport<Airport, FinalBuilderAirport> {

    private FinalBuilderAirport() {
      super(new Airport());
      injectReturnBuilder(this);
    }
  }

}
