package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class ShoeShopService implements Serializable {

  private final int PER_PAGE_MAX = 50;
  private final int INDEXING_DIFFERENCE = 1;

  private final SortedMap<Integer, Shoe> shoes = new TreeMap<>();
  private final SortedMap<Integer, ShoesCollection> shoesCollections = new TreeMap<>();
  private final SortedMap<Integer, Brand> brands = new TreeMap<>();

  @PostConstruct
  public void init() {
    ShoesCollection collection = new ShoesCollection(1, "SADC56", "Winter 2015");
    ShoesCollection collection1 = new ShoesCollection(2, "JSFN96", "Summer 2015");

    Brand brand1 = new Brand(1, "Adidas");
    Brand brand2 = new Brand(2, "Nike");
    Brand brand3 = new Brand(3, "New Balance");

    brands.put(brand1.getId(), brand1);
    brands.put(brand2.getId(), brand2);
    brands.put(brand3.getId(), brand3);

    shoesCollections.put(collection.getId(), collection);
    shoesCollections.put(collection1.getId(), collection1);

    new ShoesGenerator(brands, shoesCollections)
        .generate(100)
        .forEach(shoe -> shoes.put(shoe.getId(), shoe));
  }
  public int getTotalShoesPages(int perPage){
    return (int) Math.ceil(shoes.size() / (double)perPage);
  }

  public Collection<Shoe> findAllShoes(int page, int perPage) {
    if (page < 1 || perPage < 1) {
      return new ArrayList<>();
    }
    page -= INDEXING_DIFFERENCE;
    if (perPage > PER_PAGE_MAX) {
      perPage = PER_PAGE_MAX;
    }
    int startingIndex = page * perPage;
    if (startingIndex <= shoes.size()) {
      return shoes
          .values()
          .stream()
          .filter(shoe -> shoe.getId() > startingIndex)
          .limit(perPage)
          .collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public Shoe findShoe(int id) {
    return shoes.get(id);
  }

  public void removeShoe(Shoe shoe) {
    shoes.remove(shoe.getId());
  }

  public void saveShoe(Shoe shoe) {
    if (shoe.getId() == null) {
      shoe.setId(chooseId(shoes));
    }
    shoes.put(shoe.getId(), shoe);
  }

  private int chooseId(SortedMap<Integer, ?> map) {
    if (!map.isEmpty()) {
      return map.lastKey() + 1;
    } else {
      return 1;
    }
  }

  public Collection<ShoesCollection> findAllShoesCollections() {
    return shoesCollections.values();
  }

  public ShoesCollection findShoeCollection(int id) {
    return shoesCollections.get(id);
  }

  public void removeShoesCollection(ShoesCollection shoesCollection) {
    shoes.values().stream().forEach(shoe -> shoe.removeCollection(shoesCollection));
    shoesCollections.remove(shoesCollection.getId());
  }

  public void saveShoesCollection(ShoesCollection shoesCollection) {
    if (shoesCollection.getId() == null) {
      shoesCollection.setId(chooseId(shoesCollections));
    }
    shoesCollections.put(shoesCollection.getId(), shoesCollection);
    shoes.values().stream().forEach(shoe -> shoe.updateCollection(shoesCollection));
  }

  public Collection<Brand> findAllBrands() {
    return brands.values();
  }

  public Brand findBrand(int id) {
    return brands.get(id);
  }

  public void removeBrand(Brand brand) {
    shoes.values().removeIf(shoe -> shoe.getBrand().equals(brand));
    brands.remove(brand.getId());
  }

  public void saveBrand(Brand brand) {
    if (brand.getId() == null) {
      brand.setId(chooseId(brands));
    }
    brands.put(brand.getId(), brand);
    shoes
        .values()
        .stream()
        .filter(shoe -> shoe.getBrand().equals(brand))
        .forEach(shoe -> shoe.setBrand(brand));
  }
}
