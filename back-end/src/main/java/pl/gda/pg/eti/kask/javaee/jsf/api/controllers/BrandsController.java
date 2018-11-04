package pl.gda.pg.eti.kask.javaee.jsf.api.controllers;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeShopService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/brands")
public class BrandsController {

  @Inject ShoeShopService shoeShopService;

  @GET
  public Collection<Brand> getAllBrands() {
    return shoeShopService.findAllBrands();
  }

  @POST
  public Response saveBrand(Brand brand) {
    shoeShopService.saveBrand(brand);
    return created(uri(BrandsController.class, "getBrand", brand.getId())).build();
  }

  @GET
  @Path("/{brand}")
  public Brand getBrand(@PathParam("brand") Brand brand) {
    return brand;
  }

  @DELETE
  @Path("/{brand}")
  public Response deleteBrand(@PathParam("brand") Brand brand) {
    shoeShopService.removeBrand(brand);
    return accepted().build();
  }

  @PUT
  @Path("/{brand}")
  public Response updateBrand(@PathParam("brand") Brand originalBrand, Brand updatedBrand) {
    if (!originalBrand.getId().equals(updatedBrand.getId())) {
      return status(Status.BAD_REQUEST).build();
    }
    shoeShopService.saveBrand(updatedBrand);
    return ok().build();
  }
}
