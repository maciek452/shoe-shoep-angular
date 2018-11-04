package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class ShoesGenerator {

  private static final int STARTING_INDEX = 1;
  private static final int CHARS_LIMIT = 25;
  private static final int STARTING_SHOE_SIZE = 30;
  private static final int MAX_SHOE_SIZE = 45;

  private final SortedMap<Integer, Brand> brands;
  private final SortedMap<Integer, ShoesCollection> shoesCollections;

  public ShoesGenerator(
      SortedMap<Integer, Brand> brands, SortedMap<Integer, ShoesCollection> shoesCollections) {
    this.brands = brands;
    this.shoesCollections = shoesCollections;
  }

  public ArrayList<Shoe> generate(int size) {
    ArrayList<Shoe> shoes = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      shoes.add(generateShoe(i));
    }
    return shoes;
  }

  private Shoe generateShoe(int iterator) {
    SortedMap<Integer, ShoesCollection> shoesCollectionSortedMap = new TreeMap<>();
    shoesCollectionSortedMap.put(
        1, shoesCollections.get(STARTING_INDEX + iterator % shoesCollections.size()));

    return new Shoe(
        iterator,
        generateModelName(iterator),
        brands.get(STARTING_INDEX + iterator % brands.size()),
        (STARTING_SHOE_SIZE + iterator) % MAX_SHOE_SIZE,
        shoesCollectionSortedMap);
  }

  private String generateModelName(int iterator) {
    char c = 'A';
    c += iterator % CHARS_LIMIT;
    return "" + c + c + iterator;
  }
}
