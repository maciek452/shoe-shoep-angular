package pl.gda.pg.eti.kask.javaee.jsf.api;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeShopService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;
import static pl.gda.pg.eti.kask.javaee.jsf.api.UriUtils.uri;

@Path("/shoes")
public class ShoesController {

    @Inject
    ShoeShopService shoeShopService;

    @GET
    public Collection<Shoe> getAllBooks() {
        return shoeShopService.findAllShoes();
    }

    @POST
    public Response saveShoe(Shoe shoe) {
        shoeShopService.saveShoe(shoe);
        return created(uri(ShoesController.class, "getShoe", shoe.getId())).build();
    }

    @GET
    @Path("/{shoe}")
    public Shoe getShoe(@PathParam("shoe") Shoe shoe) {
        return shoe;
    }

    @DELETE
    @Path("/{shoe}")
    public Response deleteShoe(@PathParam("shoe") Shoe shoe) {
        shoeShopService.removeShoe(shoe);
        return accepted().build();
    }

    @PUT
    @Path("/{shoe}")
    public Response updateBook(@PathParam("shoe") Shoe originalShoe, Shoe updatedShoe) {
        if (!originalShoe.getId().equals(updatedShoe.getId())) {
            return status(Status.BAD_REQUEST).build();
        }

        shoeShopService.saveShoe(updatedShoe);
        return ok().build();
    }
}
