package dynamicColumn;


 
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
 
public class ItemTable extends Application {
 
  @Override
  public void start(Stage primaryStage) {
    final DAO dao = new MockDAO();
    final ChoiceBox<Category> choiceBox = new ChoiceBox<Category>();
    choiceBox.getItems().setAll(dao.getCategories());
 
    final TableView<Item> table = new TableView<Item>();
    table.setEditable(true);
    final TableColumn<Item, String> nameCol = new TableColumn<Item, String>("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    final TableColumn<Item, Double> priceCol = new TableColumn<Item, Double>("Price");
    table.getColumns().addAll(nameCol, priceCol);
 
    choiceBox.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<Category>() {
          @Override
          public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
            table.getItems().clear();
            priceCol.getColumns().clear();
            for (final Type type : newValue.getTypes()) {
              final TableColumn<Item, Number> col = new TableColumn<Item, Number>(type.getName());
              col.setCellValueFactory(new Callback<CellDataFeatures<Item, Number>, ObservableValue<Number>>() {
                @Override
                public ObservableValue<Number> call(CellDataFeatures<Item, Number> cellData) {
                    Item item = cellData.getValue();
                    if (item == null) {
                      return null;
                    } else {
                      return item.priceProperty(type);
                    }
                  }
                });
                
                // Make column editable:
                col.setEditable(true);
                col.setCellFactory(TextFieldTableCell.<Item, Number>forTableColumn(new NumberStringConverter()));
                
                priceCol.getColumns().add(col);
              }
              table.getItems().setAll(dao.getItemsByCategory(newValue));
            }
          });
 
    BorderPane root = new BorderPane();
    root.setTop(choiceBox);
    root.setCenter(table);
 
    Scene scene = new Scene(root, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
 
  public static void main(String[] args) {
    launch(args);
  }
}

