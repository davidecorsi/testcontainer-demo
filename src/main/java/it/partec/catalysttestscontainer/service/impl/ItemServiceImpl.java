package it.partec.catalysttestscontainer.service.impl;

import it.partec.catalysttestscontainer.model.Item;
import it.partec.catalysttestscontainer.repository.ItemRepository;
import it.partec.catalysttestscontainer.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

  private ItemRepository itemRepository;

  @Override
  public List<Item> findAllItems() {
    log.debug("Ricerca tutti gli articoli");
    return itemRepository.findAll();
  }

  @Override
  public Long createItem(Item item) {
    log.debug("Creazione articolo");
    Long id = itemRepository.save(item).getId();
    log.info("Articolo creato");
    return id;
  }

  @Override
  public Item getItem(Long id) {
    log.debug("Ricerca articolo");
    return itemRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public void updateItem(Long id, Item item) {
    log.debug("Aggiornamento articolo");
    Optional<Item> itemOptional = itemRepository.findById(id);
    item.setId(itemOptional.orElseThrow(EntityNotFoundException::new).getId());
    itemRepository.save(item);
    log.info("Articolo aggiornato");
  }

  @Override
  public void deleteItem(Long id) {
    log.debug("Cancellazione articolo");
    itemRepository.deleteById(id);
    log.info("Articolo cancellato");
  }
}
