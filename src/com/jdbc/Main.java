package com.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)  throws SQLException{


    }

    public static void selectData() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DbHelper dbHelper = new DbHelper();
        try {

            connection = dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select code,name,continent,region from country");
            ArrayList<Country> countries = new ArrayList<>();

            while(resultSet.next()){
                countries.add(new Country(resultSet.getString("code"),
                        resultSet.getString("name"),resultSet.getString("continent"),
                        resultSet.getString("region")));
            }
            System.out.println(countries.size());
        } catch (SQLException e) {
            dbHelper.showErrorMessage(e);
        } finally {
            connection.close();
            statement.close();
        }
    }
    public static void insertData() throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        DbHelper dbHelper = new DbHelper();
        try {
            connection = dbHelper.getConnection();
            String sql = "insert into city (Name,CountryCode,District,Population) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"Düzce2");
            statement.setString(2,"TUR");
            statement.setString(3,"Turkey");
            statement.setInt(4,70000);
            int result = statement.executeUpdate();
            System.out.println(result);
            System.out.println("Kayıt eklendi");
        } catch (SQLException e) {
            dbHelper.showErrorMessage(e);
        } finally {
            connection.close();
            statement.close();
        }
    }
    public static void updateData() throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        DbHelper dbHelper = new DbHelper();
        try {
            connection = dbHelper.getConnection();
            String sql = "update city set population=?,district=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,30000);
            statement.setString(2,"Kobol");
            statement.setInt(3,1);
            int result = statement.executeUpdate();
            System.out.println(result);
            System.out.println("Kayıt güncellendi");
        } catch (SQLException e) {
            dbHelper.showErrorMessage(e);
        } finally {
            connection.close();
            statement.close();
        }
    }
    public static void deleteData() throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        DbHelper dbHelper = new DbHelper();
        try {
            connection = dbHelper.getConnection();
            String sql = "delete from city where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,1000);
            int result = statement.executeUpdate();
            System.out.println(result);
            System.out.println("Kayıt silindi");
        } catch (SQLException e) {
            dbHelper.showErrorMessage(e);
        } finally {
            connection.close();
            statement.close();
        }
    }
}
