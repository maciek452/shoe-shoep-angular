package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Arrays.asList;

@ApplicationScoped
public class ShoeShopService implements Serializable {

  private final SortedMap<Integer, Shoe> shoes = new TreeMap<>();

  private final SortedMap<Integer, ShoesCollection> shoesCollectons = new TreeMap<>();

  @PostConstruct
  public void init() {
    ShoesCollection collection = new ShoesCollection(1, "SADC56", "Winter 2015");
    ShoesCollection collection1 = new ShoesCollection(2, "JSFN96", "Summer 2015");

    Shoe shoe1 = new Shoe(1, "Addidas", 37, asList(collection));
    Shoe shoe2 = new Shoe(2, "Nike", 38, asList(collection1));
    Shoe shoe3 = new Shoe(3, "New Balance", 36, asList(collection1));

    shoes.put(shoe1.getId(), shoe1);
    shoes.put(shoe2.getId(), shoe2);
    shoes.put(shoe3.getId(), shoe3);

    shoesCollectons.put(collection.getId(), collection);
    shoesCollectons.put(collection1.getId(), collection1);
  }

  public Collection<Shoe> findAllShoes() {
    return shoes.values();
  }

  public Shoe findShoe(int id) {
    return shoes.get(id);
  }

  public void removeShoe(Shoe shoe) {
    shoes.remove(shoe.getId());
  }

  public void saveShoe(Shoe shoe) {
    if (shoe.getId() == null) {
      if (!shoes.isEmpty()) {
        shoe.setId(shoes.lastKey() + 1);
      } else {
        shoe.setId(1);
      }
    }
    shoes.put(shoe.getId(), shoe);
  }

  public Collection<ShoesCollection> findAllShoesCollections() {
    return shoesCollectons.values();
  }

  public ShoesCollection findShoeCollection(int id) {
    return shoesCollectons.get(id);
  }

  public void removeShoesCollection(ShoesCollection shoesCollection) {
    for (Shoe shoe : shoes.values()) {
      for (ShoesCollection shoesCollection1 : shoe.getShoesCollections()) {
        if (shoesCollection1.equals(shoesCollection)) {
          shoe.removeCollection(shoesCollection);
        }
      }
    }

    shoesCollectons.remove(shoesCollection.getId());
  }

  public void saveShoesCollection(ShoesCollection shoesCollection) {
    if (shoesCollection.getId() == null) {
      if (!shoesCollectons.isEmpty()) {
        shoesCollection.setId(shoesCollectons.lastKey() + 1);
      } else {
        shoesCollection.setId(1);
      }
    }
    shoesCollectons.put(shoesCollection.getId(), shoesCollection);
  }
}
