package it.partec.catalysttestcontainer.service;

import it.partec.catalysttestcontainer.model.Item;

import java.util.List;

public interface ItemService {

  List<Item> findAllItems();
  Long createItem(Item item);
  Item getItem(Long id);
  void updateItem(Long id, Item item);
  void deleteItem(Long id);
}
