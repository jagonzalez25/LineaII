/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebawebwar.service;

import com.mycompany.pruebawebwar.exception.BussinessException;

/**
 *
 * @author ASUS
 */
public class EstudianteService {
    
    /**
     * 
     * @throws ArrayIndexOutOfBoundsException 
     */
    public void metodo() throws ArrayIndexOutOfBoundsException {
            int[] vector = {1, 2, 3, 4, 5};
            int x = vector[-2];
    }
    
    /**
     * 
     * @throws ArrayIndexOutOfBoundsException, BussinessException
     */
    public void metodo2(int indice) 
            throws ArrayIndexOutOfBoundsException, BussinessException, ArrayIndexOutOfBoundsException, Exception {
        try {    
            int[] vector = {1, 2, 3, 4, 5};
            if(indice == 3) {
               throw new BussinessException("No se permite el numero 3");
            } else {
                if(indice < 0 || indice > vector.length) {
                   throw new ArrayIndexOutOfBoundsException("Indice fuera de rango");
                } else {
                    int x = vector[indice];
                }
            }
        } catch(BussinessException | ArrayIndexOutOfBoundsException e) {
            throw e;
        } catch(Exception e) {
            throw new Exception("");
        } 
    }    
    
}
