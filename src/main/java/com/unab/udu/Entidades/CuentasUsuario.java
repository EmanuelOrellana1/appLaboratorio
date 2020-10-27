/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.udu.Entidades;

import java.sql.Date;

/**
 *
 * @author orell
 */
public class CuentasUsuario extends Usuario {

    protected int IdCuentaUsuario;
    protected double Saldo;
    protected int Transaccion;
    protected Date Fecha;
}
