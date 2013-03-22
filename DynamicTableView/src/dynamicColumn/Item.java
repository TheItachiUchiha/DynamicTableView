package dynamicColumn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
 
public class Item {
  private final StringProperty name ;
  private final ReadOnlyObjectProperty<Category> category ;
  private final Map<Type, DoubleProperty> prices ;
  
  public Item(String name, Category category, Map<Type, Double> prices) {
    this.name = new SimpleStringProperty(name);
    this.category = new SimpleObjectProperty<Category>(category);
    this.prices = new HashMap<Type, DoubleProperty>();
    for (Type type : prices.keySet()) {
      validateType(type);
      this.prices.put(type, new SimpleDoubleProperty(prices.get(type)));
    }
  }
  
  public String getName() {
    return name.get();
  }
  public Category getCategory() {
    return category.get();
  }
  public double getPrice(Type type) {
    validateType(type);
    return prices.get(type).get();
  }
  public void setName(String name) {
    this.name.set(name);
  }
  public void setPrice(Type type, double price) {
    validateType(type);
    prices.get(type).set(price);
  }
  public StringProperty nameProperty() {
    return name ;
  }
  public ReadOnlyObjectProperty<Category> categoryProperty() {
    return category ;
  }
  public DoubleProperty priceProperty(Type type) {
    return prices.get(type);
  }
  
  private void validateType(Type type) {
    final List<Type> allowedTypes = category.get().getTypes();
    if (! allowedTypes.contains(type)) {
      throw new IllegalArgumentException("Cannot set a price for "+type.getName()+": it is not a type for the category "+category.getName());
    }
  }
}




