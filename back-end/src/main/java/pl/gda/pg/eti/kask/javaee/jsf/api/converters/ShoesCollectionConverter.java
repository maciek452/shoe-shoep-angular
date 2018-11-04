package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeShopService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.ws.rs.ext.Provider;

@Provider
public class ShoesCollectionConverter extends AbstractEntityConverter<ShoesCollection> {

  public ShoesCollectionConverter() {
    super(ShoesCollection.class, ShoesCollection::getId, ShoeShopService::findShoeCollection);
  }
}
