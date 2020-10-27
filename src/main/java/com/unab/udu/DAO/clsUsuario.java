/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.udu.DAO;

import com.unab.edu.conexionbd.conexionbd;
import com.unab.udu.Entidades.Usuario;
import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */

public class clsUsuario {
    
    conexionbd con = new conexionbd();
    Connection conectar = con.retornarConexion();
    
  public boolean Loguin(String usuario, String Pass, int Combo) {
        ArrayList<Usuario> ListaUsuarios = new ArrayList<>();
        ArrayList<Usuario> ListarContra = new ArrayList<>();
        try {
            CallableStatement st = conectar.prepareCall("call SP_S_Loguin (?,?,?)");

            st.setString("pusuario", usuario);
            st.setString("ppass", Pass);
            st.setInt("ptipoUsuario", Combo);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Usuario es = new Usuario();
                es.setUsuario(rs.getNString("Usuario"));
                es.setContra(rs.getNString("PassWord"));
                es.setId(rs.getInt("tipoUsuario"));
                ListaUsuarios.add(es);
            }
            String usuariodebasedatos = null;
            String passdebasededatos = null;
            int tipodeusuariobasededatos = 0;
            for (var iterador : ListaUsuarios) {
                usuariodebasedatos = iterador.getUsuario();
                passdebasededatos = iterador.getContra();
                tipodeusuariobasededatos = iterador.getId();
            }

            CallableStatement st2 = conectar.prepareCall("call SP_S_CRIP(?)");
            st2.setString("PcripPassWord", Pass);
            ResultSet rs2 = st2.executeQuery();
            while (rs2.next()) {
                Usuario escrip = new Usuario();

                escrip.setContra(rs2.getNString("crip"));
                ListarContra.add(escrip);
            }

            String passcrip = null;
            for (var iterador : ListarContra) {

                passcrip = iterador.getContra();

                Pass = passcrip;

            }

            if (usuariodebasedatos != null && passdebasededatos != null) {
                if (usuariodebasedatos.equals(usuario) && passdebasededatos.equals(Pass)) {
                    return true;
                }
            }
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return false;
    }
    
}
