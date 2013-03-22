package dynamicColumn;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class Category {
 
  private final List<Type> types ;
  private final String name ;
 
  public Category(String name, List<Type> types) {
    this.name = name ;
    this.types = new ArrayList<Type>();
    this.types.addAll(types);
  }
  
  public List<Type> getTypes() {
    return Collections.unmodifiableList(types);
  }
  
  public String getName() {
    return name ;
  }
  
  @Override
  public String toString() {
    return name ;
  }
}




