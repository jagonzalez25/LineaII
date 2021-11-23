/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Map;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ASUS
 */
@Provider
@PreMatching
public class Interceptor implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Entro al filtro");
        String url =  requestContext.getUriInfo().getAbsolutePath().toString();
        System.out.println(url);
        
        //Aca se coloca las urls que no necesitan token
        if(url.contains("/auth/token")) {
            return;
        }
        if(url.contains("/autores/")) {
            return;
        }
        /*if(url.contains("/contectenos/solicitud")) {
            return;
        }*/
        
        //Desde aqui si necesitan
         String token = requestContext.getHeaderString("Authorization");
         System.out.println(token);
         
         if(token == null) {
               //Enviar el objeto de error con codigo 401 y el dto  ExcepionWrraper
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("TOKEN INVALIDO").build());
                return;
         } else {
         
             String key = "mi_clave";
             
             
             try{
             //De claims convertir el json en un objeto java Y de ahi extraer el rol
             Claims claims =    Jwts.parser()
                                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                                .parseClaimsJws(token).getBody();
                          
             System.out.println(claims.toString());
             System.out.println(claims.getExpiration().toString());
             System.out.println(claims.getSubject());
             
             //ir a la bd y valiar que el token este en la table token, si si sigue el proceso y si no entonces 
             //abortamos la peticion
             
             
            if(url.contains("/autores/") && claims.toString().contains("Administrador")) {
                return;
            } else if(url.contains("/alumnos/") && claims.toString().contains("Administrador")) {
                return;
            } else if(url.contains("/libros/") && claims.toString().contains("Vendedor")) {
                return;
            } else {
                  //Enviar el objeto de error con codigo 401 y el dto  ExcepionWrraper
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("TOKEN NO PERMITIDO PARA ESTA OPERACION").build());
                return;   
            }
             
             
             } catch(ExpiredJwtException ex) {
                  //Enviar el objeto de error con codigo 401 y el dto  ExcepionWrraper
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("TOKEN CADUCADO").build());
                return;
             } catch(Exception ex ){
                  //Enviar el objeto de error con codigo 500 y el dto  ExcepionWrraper
                             requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("ERROR AL DESCIFRAR EL TOKEN").build());
                return;
             }
         
         }
         

        
    }
    
}
