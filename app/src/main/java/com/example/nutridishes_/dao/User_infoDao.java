package com.example.nutridishes_.dao;

import com.example.nutridishes_.entity.User;
import com.example.nutridishes_.entity.User_info;
import com.example.nutridishes_.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User_infoDao {

    public boolean basicdata(int gender, int goal, double height, double weight){ //, int age, int bodyfat
        String sql = "insert into user(User_gender,User_goal,User_height,User_weight) values (?,?,?,?)";

        Connection con = JDBCUtils.getConn();

        return true;
    }

    public boolean basicdata_check(String User_email){

        Connection  con = JDBCUtils.getConn();


        boolean exist = false ;
        int userid ;

        userid = findUserID(User_email);

        try {
            String sql = "select * from user_info where User_ID= " + userid;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){//必加才能取值(resultset的遊標的起始位置是第一行前面,如果在這之前沒有next()一下,就會出現異常)

                String ss = rs.getString("User_height");
            }
            if(!rs.wasNull()){
                exist = true ;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }

        return exist;

    }

    public boolean setBasicData(Integer gender, int goal, double height,double weight){ //, int age, double bodyfat

        String sql = "insert into user_info(User_gender,User_goal,User_height,User_weight) values (?,?,?,?)";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setInt(2,gender);
            pst.setInt(3,goal);
            pst.setDouble(4,height);
            pst.setDouble(5,weight);
//            pst.setInt(6,age);
//            pst.setDouble(7,bodyfat);

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
    public User_info getBasicData(String User_email){

        Connection  con = JDBCUtils.getConn();

        User_info user_info = new User_info();

        int userid ;

        userid = findUserID(User_email);

        try {
            String sql = "select * from user_info where User_ID= " + userid;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){//必加才能取值(resultset的遊標的起始位置是第一行前面,如果在這之前沒有next()一下,就會出現異常)

                user_info.setGender(Integer.valueOf(rs.getString("User_gender")));
                user_info.setGoal(Integer.valueOf(rs.getString("User_goal")));
                user_info.setHeight(Double.valueOf(rs.getString("User_height")));
                user_info.setWeight(Double.valueOf(rs.getString("User_weight")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }



        return user_info;
    }

    public boolean setBasicData_bodyfat(double bodyfat){

        String sql = "insert into user_info(User_badyfat) values (?)";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setDouble(7,bodyfat);

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

    public boolean setBasicData_age(int age){

        String sql = "insert into user_info(User_age) values (?)";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setInt(6,age);

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
