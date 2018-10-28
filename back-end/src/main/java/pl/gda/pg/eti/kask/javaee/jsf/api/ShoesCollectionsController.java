package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeShopService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/shoesCollections")
public class ShoesCollectionsController {

  @Inject ShoeShopService shoeShopService;

  @GET
  public Collection<ShoesCollection> getAllShoesCollections() {
    return shoeShopService.findAllShoesCollections();
  }

  @GET
  @Path("/{shoesCollection}")
  public ShoesCollection getShoesCollection(
      @PathParam("shoesCollection") ShoesCollection shoesCollection) {
    return shoesCollection;
  }

  @POST
  public Response saveShoesCollection(ShoesCollection shoesCollection) {
    shoeShopService.saveShoesCollection(shoesCollection);
    return created(
            uri(ShoesCollectionsController.class, "getShoesCollection", shoesCollection.getId()))
        .build();
  }

  @DELETE
  @Path("/{shoesCollection}")
  public Response deleteShoe(@PathParam("shoesCollection") ShoesCollection shoesCollection) {
    shoeShopService.removeShoesCollection(shoesCollection);
    return accepted().build();
  }

  @PUT
  @Path("/{shoesCollection}")
  public Response updateBook(
      @PathParam("shoesCollection") ShoesCollection originalShoesCollection,
      ShoesCollection updatedShoesCollection) {
    if (!originalShoesCollection.getId().equals(updatedShoesCollection.getId())) {
      return status(Response.Status.BAD_REQUEST).build();
    }
    shoeShopService.removeShoesCollection(updatedShoesCollection);
    return ok().build();
  }
}
