/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.udu.DAO;

import com.unab.edu.conexionbd.conexionbd;
import com.unab.udu.Entidades.CuentasUsuario;
import com.unab.udu.Entidades.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lombok.Data;

/**
 *
 * @author PC
 */
@Data
public class clsCuentaUsuario {
    
    conexionbd conectarclase = new conexionbd();
    Connection conectar = conectarclase.retornarConexion();
    
    public ArrayList<Usuario> MostrarUsuario() {
        ArrayList<Usuario> UsuarioD = new ArrayList<>();
        try {
            CallableStatement Statement = conectar.prepareCall("call SP_S_ComboAbonar()");
            ResultSet resultadoDeConsulta = Statement.executeQuery();
            
            while (resultadoDeConsulta.next()) {
                Usuario us = new Usuario();
                us.setIdUsuario(resultadoDeConsulta.getInt("idUsuario"));
                us.setUsuario(resultadoDeConsulta.getString("Usuario"));
                us.setContra(resultadoDeConsulta.getString("PassWord"));
                us.setId(resultadoDeConsulta.getInt("tipoUsuario"));
                
                UsuarioD.add(us);
            }
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return UsuarioD;
    }
    
    public void AgregarCuenta(CuentasUsuario cuentas) {
        try {
            CallableStatement consulta = conectar.prepareCall("call SP_I_AbonarD(?,?,?,?)");
            consulta.setDouble("PSaldo", cuentas.getSaldo());
            consulta.setInt("PidUsuario", cuentas.getIdUsuario());
            consulta.setInt("PTransaccion", cuentas.getTransaccion());
            consulta.setDate("PFecha", new java.sql.Date(cuentas.getFecha().getTime()));
            consulta.executeQuery();
            JOptionPane.showMessageDialog(null, "Insercion Completada");
            conectar.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public ArrayList<CuentasUsuario> MostrarCuenta(CuentasUsuario ccuentas) {
        ArrayList<CuentasUsuario> usuario = new ArrayList<>();
        try {
            CallableStatement st = conectar.prepareCall("call SP_S_SacarD(?)");
            st.setInt("PidUsuario", ccuentas.getIdUsuario());
            ResultSet resultadoDeConsulta = st.executeQuery();

            while (resultadoDeConsulta.next()) {
                CuentasUsuario cu = new CuentasUsuario();
                cu.setSaldo(resultadoDeConsulta.getDouble("saldo"));
                cu.setTransaccion(resultadoDeConsulta.getInt("transaccion"));
                cu.setFecha(resultadoDeConsulta.getDate("fecha"));
                
               

                usuario.add(cu);
            }
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return usuario;
  }
    
}
