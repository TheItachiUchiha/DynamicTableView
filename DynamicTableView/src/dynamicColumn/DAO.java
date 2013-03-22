package dynamicColumn;


import java.util.List;
 
public interface DAO {
  public List<Category> getCategories();
  public List<Item> getItemsByCategory(Category category);
}




