package it.partec.catalysttestcontainer.controller;

import it.partec.catalysttestcontainer.configuration.AbstractContainerBaseTest;
import it.partec.catalysttestcontainer.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTests extends AbstractContainerBaseTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("GET ALL Item Test")
  void getAllItems() throws Exception {
    assertEquals(5, restTemplate.getForObject("http://localhost:" + port + "/item", Item[].class).length);
  }

  @Test
  @DisplayName("CREATE Item Test")
  void createItem() {
    Item item = Item.builder()
            .serialNumber("85164726")
            .description("T-SHIRT FAMILY PROJECT ROCK")
            .build();

    Long id = restTemplate.postForObject("http://localhost:" + port + "/item", item, Long.class);

    assertEquals("T-SHIRT FAMILY PROJECT ROCK", restTemplate.getForObject("http://localhost:" + port + "/item/" + id, Item.class)
            .getDescription());

    restTemplate.delete("http://localhost:" + port + "/item/" + 6);
  }

  @Test
  @DisplayName("GET Item Test")
  void getItem() {
    assertEquals("T-SHIRT TECH 2.0", restTemplate.getForObject("http://localhost:" + port + "/item/" + 1, Item.class)
            .getDescription());
  }

  @Test
  @DisplayName("PUT Item Test")
  void updateItem() {
    Item itemOriginal = restTemplate.getForObject("http://localhost:" + port + "/item/" + 1, Item.class);
    Item itemUpdate = new Item();
    itemUpdate.setSerialNumber("85164726");
    itemUpdate.setDescription("T-SHIRT FAMILY PROJECT ROCK");

    restTemplate.put("http://localhost:" + port + "/item/" + 1, itemUpdate);

    assertEquals(itemUpdate.getDescription(), restTemplate.getForObject("http://localhost:" + port + "/item/" + 1, Item.class).getDescription());

    restTemplate.put("http://localhost:" + port + "/item/" + 1, itemOriginal);
  }

  @Test
  @DisplayName("DELETE Item Test")
  void deleteItem() {
    Item itemOriginal = restTemplate.getForObject("http://localhost:" + port + "/item/" + 1, Item.class);

    restTemplate.delete("http://localhost:" + port + "/item/" + 1);

    assertNull(restTemplate.getForObject("http://localhost:" + port + "/item/" + 1, Void.class));

    restTemplate.postForObject("http://localhost:" + port + "/item", itemOriginal, Long.class);
  }
}
