/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.Flight;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andreas
 */
public class FlightsFacade {
    
    private List<Flight> flights;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    
    public List<Flight> getFlightsWithNoDest(String origin, String date, int numberOfTickets){
        EntityManager em = emf.createEntityManager();
        String[] splitDate = date.split("T");
        String newDate = splitDate[0] + "%";
        em.getTransaction().begin();
        flights = em.createQuery("SELECT f from Flight f where f.origin=:origin and f.date LIKE :date and f.numberOfSeats>=:numberOfTickets").setParameter("origin", origin).setParameter("date", newDate).setParameter("numberOfTickets", numberOfTickets).getResultList();
        em.getTransaction().commit();
        return flights;
    }
    
        public List<Flight> getFlightsWithDest(String origin, String destination, String date, int numberOfTickets)
    {
        EntityManager em = emf.createEntityManager();
        String[] splitDate = date.split("T");
        String newDate = splitDate[0] + "%";
        em.getTransaction().begin();
        flights = em.createQuery("SELECT f from Flight f where f.origin=:origin and f.destination=:destination and f.date LIKE :date and f.numberOfSeats>=:numberOfTickets").setParameter("origin", origin).setParameter("destination", destination).setParameter("date", newDate).setParameter("numberOfTickets", numberOfTickets).getResultList();
        em.getTransaction().commit();
        return flights;
    }
        
        public static void main(String[] args) {
        Flight f1 = new Flight();
        Flight f2 = new Flight();
        Flight f3 = new Flight();
        Flight f4 = new Flight();
        
        f1.setAirlineName("Flights Airline");
        f1.setFligthID("COL2216x100x2016-02-01T15:00:00.000Z");
        f1.setDate("2016-02-01T15:00:00.000Z");
        f1.setOrigin("CPH");
        f1.setDestination("MIA");
        f1.setNumberOfSeats(290);
        f1.setTraveltime(640);
        f1.setTotalPrice(2199);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(f1);
        em.getTransaction().commit();

    
    }
    
    
    
}