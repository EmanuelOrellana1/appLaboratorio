/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.udu.Entidades;

import lombok.Data;


@Data
public class Usuario extends Tipo{

    protected int IdUsuario;
    protected String Usuario;
    protected String Contra;
    
}
