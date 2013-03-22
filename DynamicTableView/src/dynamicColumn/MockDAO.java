package dynamicColumn;

 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class MockDAO implements DAO {
  
  private final List<Category> categories ;
  private final Map<Category, List<Item>> itemLookup ;
  
  public MockDAO() {
    final Type spreadType100g = new Type("100g");
    final Type spreadType200g = new Type("200g");
    final Type drinkType200ml = new Type("200ml");
    final Type drinkType500ml = new Type("500ml");
    final Type drinkType1000ml = new Type("1000ml");
    final Category spreads = new Category(
        "Spreads",
        Arrays.asList(spreadType100g, spreadType200g)
    );
    final Category drinks = new Category(
        "Drinks",
        Arrays.asList(drinkType200ml, drinkType500ml, drinkType1000ml)
    );
    final Map<Type, Double> waterPrices = new HashMap<Type, Double>();
    waterPrices.put(drinkType200ml, 10.0);
    waterPrices.put(drinkType500ml, 20.0);
    waterPrices.put(drinkType1000ml, 30.0);
    final Map<Type, Double> pepsiPrices = new HashMap<Type, Double>();
    pepsiPrices.put(drinkType200ml, 25.0);
    pepsiPrices.put(drinkType500ml, 45.0);
    pepsiPrices.put(drinkType1000ml, 75.0);
    final Map<Type, Double> butterPrices = new HashMap<Type, Double>();
    butterPrices.put(spreadType100g, 15.0);
    butterPrices.put(spreadType200g, 25.0);
    final Map<Type, Double> margarinePrices = new HashMap<Type, Double>();
    margarinePrices.put(spreadType100g, 12.0);
    margarinePrices.put(spreadType200g, 20.0);
    final Map<Type, Double> mayoPrices = new HashMap<Type, Double>();
    mayoPrices.put(spreadType100g, 20.0);
    mayoPrices.put(spreadType200g, 35.0);
    final Item water = new Item("Water", drinks, waterPrices);
    final Item pepsi = new Item("Pepsi", drinks, pepsiPrices);
    final Item butter = new Item("Butter", spreads, butterPrices);
    final Item margarine = new Item("Margarine", spreads, margarinePrices);
    final Item mayonnaise = new Item("Mayonnaise", spreads, mayoPrices);
    
    this.categories = Arrays.asList(drinks, spreads);
    this.itemLookup = new HashMap<Category, List<Item>>();
    itemLookup.put(drinks, Arrays.asList(water, pepsi));
    itemLookup.put(spreads, Arrays.asList(butter, margarine, mayonnaise));
  }
 
  @Override
  public List<Category> getCategories() {
    return categories ;
  }
 
  @Override
  public List<Item> getItemsByCategory(Category category) {
    return itemLookup.get(category);
  }
 
}



