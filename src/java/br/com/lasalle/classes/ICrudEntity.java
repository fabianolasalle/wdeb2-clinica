/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lasalle.classes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fabiano
 */
public interface ICrudEntity {
    public void mapRequest(HttpServletRequest request);
}
