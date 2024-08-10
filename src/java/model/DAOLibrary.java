/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.library;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pgb31
 */
public class DAOLibrary extends DBConnect {

    public ArrayList<library> listbyid(int id) {

        ArrayList<library> list = new ArrayList<>();
        String sql = "select * from [Dictionary] where UserID = " + id;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                int lid = rs.getInt(1);
                String keyword = rs.getString(2);
                String define = rs.getString(3);
                int uid = rs.getInt(4);
                library lib = new library(lid, keyword, define, uid);
                list.add(lib);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addtolib(String key, String define, int id) {

        String sql = "insert into [Dictionary] (keyword,define,UserID) \n"
                + "values (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, key);
            ps.setString(2, define);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList selectbylid(int id) {
        ArrayList<library> list = new ArrayList<>();
        String sql = "select * from [Dictionary] where ID = " + id;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                int lid = rs.getInt(1);
                String keyword = rs.getString(2);
                String define = rs.getString(3);
                int uid = rs.getInt(4);
                library lib = new library(id, keyword, define, uid);
                list.add(lib);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void delete(int id) {

        String sql = "Delete [Dictionary] where [ID]=" + id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void edit(int id, String word, String defi) {

        String sql = "Update [Dictionary] set keyword= ?, define = ? where [ID]=" + id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, word);
            ps.setString(2, defi);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
