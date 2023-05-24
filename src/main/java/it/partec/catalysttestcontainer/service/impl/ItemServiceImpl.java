package it.partec.catalysttestcontainer.service.impl;

import it.partec.catalysttestcontainer.model.Item;
import it.partec.catalysttestcontainer.repository.ItemRepository;
import it.partec.catalysttestcontainer.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

  private ItemRepository itemRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Item> findAllItems() {
    log.debug("Ricerca tutti gli articoli");
    return itemRepository.findAll();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long createItem(Item item) {
    log.debug("Creazione articolo");
    Long id = itemRepository.save(item).getId();
    log.info("Articolo creato");
    return id;
  }

  @Override
  @Transactional(readOnly = true)
  public Item getItem(Long id) {
    log.debug("Ricerca articolo");
    return itemRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateItem(Long id, Item item) {
    log.debug("Aggiornamento articolo");
    Optional<Item> itemOptional = itemRepository.findById(id);
    item.setId(itemOptional.orElseThrow(EntityNotFoundException::new).getId());
    itemRepository.save(item);
    log.info("Articolo aggiornato");
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteItem(Long id) {
    log.debug("Cancellazione articolo");
    itemRepository.deleteById(id);
    log.info("Articolo cancellato");
  }
}
