package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils;
import pl.gda.pg.eti.kask.javaee.jsf.api.controllers.ShoesController;

import javax.ws.rs.HttpMethod;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shoe implements Serializable {

  private Integer id;

  private String model;

  private Brand brand;

  private int size;

  @JsonProperty("shoes_collections")
  private SortedMap<Integer, ShoesCollection> shoesCollections = new TreeMap<>();

  @JsonProperty("_links")
  private ArrayList<Link> getLinks() {
    return new ArrayList<>(
        Arrays.asList(
            new Link(
                UriUtils.uri(ShoesController.class, "getShoesBrand", id), HttpMethod.GET, "brand"),
            new Link(
                UriUtils.uri(ShoesController.class, "getShoesCollections", id),
                HttpMethod.GET,
                "shoes_collections")));
  }

  @JsonGetter("brand")
  public URI brand() {
    return UriUtils.uri(ShoesController.class, "getShoesBrand", id);
  }

  @JsonGetter("shoes_collections")
  public URI shoesCollections() {
    return UriUtils.uri(ShoesController.class, "getShoesCollections", id);
  }

  public void removeCollection(ShoesCollection shoesCollection) {
    shoesCollections.remove(shoesCollection.getId());
  }

  public void updateCollection(ShoesCollection shoesCollection) {
    if (shoesCollections.values().contains(shoesCollection)) {
      shoesCollections.put(shoesCollection.getId(), shoesCollection);
    }
  }
}
