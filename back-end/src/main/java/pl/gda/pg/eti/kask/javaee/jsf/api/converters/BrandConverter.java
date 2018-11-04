package pl.gda.pg.eti.kask.javaee.jsf.api.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeShopService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;

import javax.ws.rs.ext.Provider;

@Provider
public class BrandConverter extends AbstractEntityConverter<Brand> {

  public BrandConverter() {
    super(Brand.class, Brand::getId, ShoeShopService::findBrand);
  }
}
