package pkg42.view.execute;

import javafx.scene.control.ListCell;
import pkg42.view.execute.item.ItemController;

public class CustomListCell extends ListCell<ItemController> {

    @Override
    protected void updateItem(ItemController item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setGraphic(item.pane);
        } else {
            setGraphic(null);
        }
    }
}
