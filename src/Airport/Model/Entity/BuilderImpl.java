package Model.Entity;

public class BuilderImpl<T, RetBuilder>  {
  protected T nested;
  RetBuilder returnBuilder;

  protected BuilderImpl(T child) {
    nested = child;
  }

  protected T getNested() {
    return nested;
  }

  protected void injectReturnBuilder(RetBuilder builder) {
    returnBuilder = builder;
  }

  protected RetBuilder self() {
    return returnBuilder;
  }

  public T build() {
    return nested;
  }
}
