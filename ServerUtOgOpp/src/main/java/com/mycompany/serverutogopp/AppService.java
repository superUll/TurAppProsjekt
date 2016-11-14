/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverutogopp;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author vidar
 */
@Stateless
@Path("content")
@Produces(MediaType.APPLICATION_JSON)
public class AppService {
    
     @PersistenceContext
    EntityManager em;
     Rating now = new Rating();
    
    @GET
    @Path("mountains")
    public List<Mountain> getAllMountains(){
        return em.createNamedQuery(Mountain.FIND_ALL_MOUNTAINS, Mountain.class).getResultList();
    }
    
    @GET
    @Path("ratings")
    public List<Rating> getAllRating(){
        return em.createNamedQuery(Rating.FIND_ALL, Rating.class).getResultList();
    }
<<<<<<< HEAD
<<<<<<< HEAD
  
=======
    
    @GET
    @Path("images")
    public List<Image> getAllImage(){
        return em.createNamedQuery(Image.FIND_ALL_IMAGES, Image.class).getResultList();
    }
>>>>>>> bilder
=======
  
>>>>>>> origin/master
       
    
}
