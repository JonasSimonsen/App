/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Flight;
import facades.FlightsFacade;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Andreas
 */
@Path("flightinfo/")
public class FlightsRest {

    FlightsFacade daf = new FlightsFacade();
    Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{origin}/{date}/{numTickets}")
    public String searchFlightsToAll(@PathParam("origin") String airport, @PathParam("date") String date, @PathParam("numTickets") int numberOfTickets) {
        List<Flight> flights = daf.getFlightsWithNoDest(airport, date, numberOfTickets);
        JsonArray ja = new JsonArray();
        JsonObject flightcol = new JsonObject();
        flightcol.addProperty("Airline", "Data Airlines");
        for (Flight f : flights) {
            JsonObject jo = new JsonObject();
            jo.addProperty("date", f.getDate());
            jo.addProperty("numberOfSeats", f.getNumberOfSeats());
            jo.addProperty("totalPrice", f.getTotalPrice());
            jo.addProperty("flightID", f.getFligthID());
            jo.addProperty("traveltime", f.getTraveltime());
            jo.addProperty("destination", f.getDestination());
            jo.addProperty("origin", f.getOrigin());
            ja.add(jo);
        }
        flightcol.add("flights", ja);
        String jsonStr = gson.toJson(flightcol);
        return jsonStr;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{origin}/{destination}/{date}/{numberOfTickets}")
    public String getInfoRequestToAndFrom(@PathParam("origin") String airport, @PathParam("destination") String destination, @PathParam("date") String date, @PathParam("numberOfTickets") int numberOfTickets) {
        List<Flight> flights = daf.getFlightsWithDest(airport, destination, date, numberOfTickets);
        JsonArray ja = new JsonArray();
        for (Flight f : flights) {
            JsonObject jo = new JsonObject();
            jo.addProperty("airline", f.getAirlineName());
            jo.addProperty("destination", f.getDestination());
            jo.addProperty("date", f.getDate());
            jo.addProperty("numberOfSeats", f.getNumberOfSeats());
            jo.addProperty("totalPrice", f.getTotalPrice());
            jo.addProperty("flightID", f.getFligthID());
            jo.addProperty("traveltime", f.getTraveltime());
            jo.addProperty("origin", f.getOrigin());
            ja.add(jo);
        }
        String jsonStr = gson.toJson(ja);
        return jsonStr;
    }

}
