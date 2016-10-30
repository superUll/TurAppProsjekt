/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oppoggaaserver;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

/**
 *
 * @author vidar
 */
@Stateless
@Path("get")
@Produces(MediaType.APPLICATION_JSON)
public class MountainServices {
    
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Path("moutain")
    public List<Mountain> getAllMountains(){
        return em.createNamedQuery(Mountain.ALL_MOUNTAINS, Mountain.class).getResultList();
    }
    
}
