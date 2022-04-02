package br.com.trochadev.cotacaomoedas.client;

import br.com.trochadev.cotacaomoedas.entity.Holiday;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "holiday")
@Path("/holidays")
public interface HolidayClient {

    @GET
    @Path("/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Holiday> getHolidays(@PathParam ("year")String year, @QueryParam("token") String token);

}
