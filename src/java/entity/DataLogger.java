/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Andreas
 */
@Entity
public class DataLogger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private String travelDate;

    public DataLogger() {
    }

    public DataLogger(String origin, String destination, String travelDate) {
        this.origin = origin;
        this.destination = destination;
        this.travelDate = travelDate;
    }

    public DataLogger(String origin, String travelDate) {
        this.origin = origin;
        this.travelDate = travelDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return travelDate;
    }

    public void setDate(String travelDate) {
        this.travelDate = travelDate;
    }
}