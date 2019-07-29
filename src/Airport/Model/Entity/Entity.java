package Model.Entity;

public class Entity {

  private int id = -1;

  public int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
  }

  public static InnerBuilderEntity<? extends Entity, ?> builder() {
    return new FinalBuilderEntity();
  }


  /**
   * implementation of builder abstraction.
   */
  public static class InnerBuilderEntity<T extends Entity, RetBuilder extends InnerBuilderEntity<? extends T, ?>> extends
      BuilderImpl<T, RetBuilder> {

    InnerBuilderEntity(T created) {
      super(created);
    }

    public RetBuilder id(int id) {
      getNested().setId(id);
      return self();
    }
  }

  /**
   * real builder Entity.
   */
  private static class FinalBuilderEntity extends InnerBuilderEntity<Entity, FinalBuilderEntity> {

    private FinalBuilderEntity() {
      super(new Entity());
      injectReturnBuilder(this);
    }
  }

}