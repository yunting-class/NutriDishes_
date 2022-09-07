package com.example.nutridishes_.dao;

import com.example.nutridishes_.entity.User;
import com.example.nutridishes_.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {


    public boolean login(String email,String password){

        String sql = "select * from user where User_email = ? and User_pw = ?";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,email);
            pst.setString(2,password);

            if(pst.executeQuery().next()){

                return true;

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return false;
    }

    public boolean register(String email,String password,String username,Date createdate){

        String sql = "insert into user(User_email,User_pw,User_name,User_createdate) values (?,?,?,?)";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,email);
            pst.setString(2,password);
            pst.setString(3,username);
            pst.setDate(4,createdate);

            int value = pst.executeUpdate();

            if(value>0){
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return false;
    }

    public User getUserData(String User_email){

        Connection  con = JDBCUtils.getConn();

        User user = new User();

        int userid ;

        userid = findUserID(User_email);

        try {
            String sql = "select * from user where User_ID= " + userid ;//+User_email = "+User_email;
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){//必加才能取值(resultset的遊標的起始位置是第一行前面,如果在這之前沒有next()一下,就會出現異常)

                user.setEmail(rs.getString("User_email"));
//                String ss = rs.getString("User_pw");
//                System.out.println("ss:"+ss);
                user.setPassword(rs.getString("User_pw"));
                user.setUsername(rs.getString("User_name"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return user;
    }

    public boolean findUser(String User_email){

        String sql = "select * from user where User_email = ?";
        boolean tf = false;

        Connection  con = JDBCUtils.getConn();
        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,User_email);

            ResultSet rs = pst.executeQuery();
            String emaildb = null ;
            while (rs.next()){
                emaildb = rs.getString(1); //getemail_id
//                System.out.println("emaildb:"+emaildb);
                if(emaildb!= null){
                    tf = true;
                    return tf;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return tf;
    }

    public int findUserID(String User_email){

        String sql = "select * from user where User_email = ?";
        int userid = 0 ;

        Connection  con = JDBCUtils.getConn();
        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,User_email);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                userid = Integer.valueOf(rs.getString(1)); //getemail_id

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return userid;
    }



}

