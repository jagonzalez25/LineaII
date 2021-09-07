/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebawebwar.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author ASUS
 */
@Path("/estudiantes")
public class EstudianteController {
    
    @Path("/prueba")
    @GET
    public void prueba(){
        System.out.println("Ingres peticion");
    }
    
}