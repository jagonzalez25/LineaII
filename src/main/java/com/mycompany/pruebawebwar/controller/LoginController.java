/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebawebwar.controller;

import co.edu.unicundi.pruebaejbjar.service.IUsuarioService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ASUS
 */
@Stateless
@Path("/auth")
public class LoginController {
    
    @EJB
    private IUsuarioService service;
    
    @GET
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON) 
    //Recibir usuario y cotnrasena por el cuerpo de la peticion
    public Response login() throws Exception{
        String token = this.service.login("johans", "1234");
        //No retornar un string si no un objeto que dentro tenga el token
        return Response.status(Response.Status.OK).entity(token).build();
    }  
    
    //CerrarSesion
    //Debo ir y eliminar el token de la BD
    
}
