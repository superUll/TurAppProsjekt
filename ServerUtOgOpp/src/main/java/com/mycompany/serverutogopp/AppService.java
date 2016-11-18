/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverutogopp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

   
    @GET
    @Path("images")
    public List<Image> getAllImage(){
        return em.createNamedQuery(Image.FIND_ALL_IMAGES, Image.class).getResultList();
    }
    
    @POST
    @Path("setrating")
    @Consumes(MediaType.APPLICATION_JSON)   
    public void setRating(Rating rating){
        em.persist(rating);
    }
    
    /*@POST
    @Path("setrating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setRating(InputStream incomingData){
       StringBuilder builder = new StringBuilder();
       try{
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(incomingData));
           String line = null;
           while((line= bufferedReader.readLine()) != null){
           builder.append(line);
       }
           
       }catch (Exception e) {
           System.out.println("Error Parsing: - ");
        }
        System.out.println("Data Received: " + builder.toString());
        
        //return HTTP response 200 in case of success
        
        
        return Response.status(200).entity(builder.toString()).build();
    }*/
   
}
