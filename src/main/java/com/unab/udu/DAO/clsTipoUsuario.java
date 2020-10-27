/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.udu.DAO;

import com.unab.edu.conexionbd.conexionbd;
import com.unab.udu.Entidades.Tipo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class clsTipoUsuario {
    conexionbd conectarclase = new conexionbd();
    Connection conectar = conectarclase.retornarConexion();
    
    public ArrayList<Tipo> MostrartipoUsuario() {
        ArrayList<Tipo> tUsuario = new ArrayList<>();
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_S_ComboLoguin()");
            ResultSet resultadoDeConsulta = Statement.executeQuery();

            while (resultadoDeConsulta.next()) {
                Tipo est = new Tipo();
                est.setId(resultadoDeConsulta.getInt("Id"));
                est.setTipoUs(resultadoDeConsulta.getString("TipoUser"));
                

                tUsuario.add(est);
            }
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return tUsuario;
    }
    
}
