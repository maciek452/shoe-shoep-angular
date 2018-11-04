package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeShopService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;

import javax.ws.rs.ext.Provider;

@Provider
public class ShoeConverter extends AbstractEntityConverter<Shoe> {

  public ShoeConverter() {
    super(Shoe.class, Shoe::getId, ShoeShopService::findShoe);
  }
}
