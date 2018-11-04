package pl.gda.pg.eti.kask.javaee.jsf.api.controllers;

import pl.gda.pg.eti.kask.javaee.jsf.api.controllers.pagination.LinkPagination;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeShopService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/shoes")
public class ShoesController {

  @Context UriInfo uriInfo;

  @Inject ShoeShopService shoeShopService;

  @GET
  @Path("")
  public Response getAllShoes(
      @QueryParam("page") @DefaultValue("1") int page,
      @QueryParam("per_page") @DefaultValue("5") int perPage) {
    Collection<Shoe> shoes = shoeShopService.findAllShoes(page, perPage);

    LinkPagination linkPagination =
        new LinkPagination(page, shoeShopService.getTotalShoesPages(perPage));
    return Response.ok()
        .links(linkPagination.toLinks(uriInfo).toArray(Link[]::new))
        .entity(shoes)
        .build();
  }

  @POST
  public Response saveShoe(Shoe shoe) {
    shoeShopService.saveShoe(shoe);
    return created(uri(ShoesController.class, "getShoe", shoe.getId())).build();
  }

  @GET
  @Path("/{shoe}/")
  public Shoe getShoe(@PathParam("shoe") Shoe shoe) {
    return shoe;
  }

  @DELETE
  @Path("/{shoe}/")
  public Response deleteShoe(@PathParam("shoe") Shoe shoe) {
    shoeShopService.removeShoe(shoe);
    return accepted().build();
  }

  @PUT
  @Path("/{shoe}/")
  public Response updateShoe(@PathParam("shoe") Shoe originalShoe, Shoe updatedShoe) {
    if (!originalShoe.getId().equals(updatedShoe.getId())) {
      return status(Status.BAD_REQUEST).build();
    }
    shoeShopService.saveShoe(updatedShoe);
    return ok().build();
  }

  @GET
  @Path("/{shoe}/shoesCollections/")
  public Collection<ShoesCollection> getShoesCollections(@PathParam("shoe") Shoe shoe) {
    return shoe.getShoesCollections().values();
  }

  @GET
  @Path("/{shoe}/shoesCollections/{shoesCollection}")
  public ShoesCollection getShoesCollection(
      @PathParam("shoe") Shoe shoe, @PathParam("shoesCollection") ShoesCollection shoesCollection) {
    return shoe.getShoesCollections().get(shoesCollection.getId());
  }

  @DELETE
  @Path("/{shoe}/shoesCollections/{shoesCollection}")
  public Response deleteShoesCollection(
      @PathParam("shoe") Shoe shoe, @PathParam("shoesCollection") ShoesCollection shoesCollection) {
    shoe.removeCollection(shoesCollection);
    return accepted().build();
  }

  @GET
  @Path("/{shoe}/brand")
  public Brand getShoesBrand(@PathParam("shoe") Shoe shoe) {
    return shoe.getBrand();
  }

  @DELETE
  @Path("/{shoe}/brand")
  public Response deleteShoesBrand(@PathParam("shoe") Shoe shoe) {
    shoeShopService.removeBrand(shoe.getBrand());
    return accepted().build();
  }
}
