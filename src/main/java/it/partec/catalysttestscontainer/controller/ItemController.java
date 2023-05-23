package it.partec.catalysttestscontainer.controller;

import it.partec.catalysttestscontainer.model.Item;
import it.partec.catalysttestscontainer.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
@Slf4j
public class ItemController {

  private ItemService itemService;

  @GetMapping
  public ResponseEntity<List<Item>> getAllItems() {
    log.debug("/item GET");
    List<Item> itemList = itemService.findAllItems();
    return ResponseEntity.ok(itemList);
  }

  @PostMapping
  public ResponseEntity<Long> createItem(@RequestBody Item item) {
    log.debug("/item POST {}", item);
    Long id = itemService.createItem(item);
    return ResponseEntity.ok(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getItem(@PathVariable("id") Long id) {
    log.debug("/item GET {}", id);
    Item item = itemService.getItem(id);
    return ResponseEntity.ok(item);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
    log.debug("/item PUT {}", id);
    itemService.updateItem(id, item);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> updateItem(@PathVariable("id") Long id) {
    log.debug("/item DELETE {}", id);
    itemService.deleteItem(id);
    return ResponseEntity.ok().build();
  }
}
