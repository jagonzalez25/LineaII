/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebawebwar.controller;

import co.edu.unicundi.pruebaejbjar.dto.AutorEditorialDto;
import co.edu.unicundi.pruebaejbjar.entity.Alumno;
import co.edu.unicundi.pruebaejbjar.entity.Autor;
import co.edu.unicundi.pruebaejbjar.entity.AutorEditorial;
import co.edu.unicundi.pruebaejbjar.exception.BussinessException;
import co.edu.unicundi.pruebaejbjar.exception.ResourceNotFoundException;
import co.edu.unicundi.pruebaejbjar.service.IAutorEditorialService;
import co.edu.unicundi.pruebaejbjar.service.IAutorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ASUS
 */
@Stateless
@Path("/autores")
public class AutorController {
    
    @EJB
    private IAutorService service;
    
    @EJB
    private IAutorEditorialService serviceAutorEditorial;
    
    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtener() {
          List<Autor> listAutores = service.listar();
          return Response.status(Response.Status.OK).entity(listAutores).build();
    } 
    
    @GET
    @Path("/obtener/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") Integer id) throws ResourceNotFoundException {
          Autor autor = service.listarPorId(id);
          return Response.status(Response.Status.OK).entity(autor).build();
    }  

    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public Response guardar(@Valid Autor autor){
        this.service.guardar(autor);
        return Response.status(Response.Status.CREATED).build();
    }       
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public Response editar(@Valid Autor autor) throws BussinessException, ResourceNotFoundException{
        this.service.editar(autor);
        return Response.status(Response.Status.OK).build();
    }      
    
     @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) throws ResourceNotFoundException {
          service.eliminar(id);
          return Response.status(Response.Status.NO_CONTENT).build();
    }     
    
    @POST
    @Path("/asociarEditorial")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public Response asociarEditorial(@Valid AutorEditorial autorEditorial){
        this.serviceAutorEditorial.guardar(autorEditorial);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("/obtenerEditorial/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEditorial(@PathParam("id") Integer id) throws ResourceNotFoundException {
          List<AutorEditorialDto> autorEditorial = this.serviceAutorEditorial.listarPorAutorId(id);
          return Response.status(Response.Status.OK).entity(autorEditorial).build();
    } 
}
