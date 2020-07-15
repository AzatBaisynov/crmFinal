package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.model.TelegramProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCProducts {
     private DbWorker dbWorker = new DbWorker();

    public Integer selectFromUsers(String key) {
        String SQL = "select company_id from users where telegram_key = ?";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setString(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
            return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<String> getAllRegistrations(){
        String SQL = "select telegram_id from telegram_registration;";
        try (
                Connection conn = dbWorker.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL))
        {
            List<String> list = new ArrayList<>();
            while (rs.next())
            {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void deleteTelegramRegistration(String id){
        List<String> list = getAllRegistrations();
        if(list!=null) {
            if (list.contains(id)) {
                String SQL = "delete from telegram_registration where telegram_id = ?;";
                try (Connection conn = dbWorker.connect();
                     PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
                    preparedStatement.setString(1, id);
                    preparedStatement.execute();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String createTelegramRegistration(String specialKey, String telegramId, Integer companyId) {
        deleteTelegramRegistration(telegramId);
        String SQL = "insert into telegram_registration(special_key , telegram_id ,company_id , date_created ) values (?,?,?,current_timestamp);";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setString(1, specialKey);
            preparedStatement.setString(2, telegramId);
            preparedStatement.setInt(3, companyId);
            preparedStatement.execute();
            return "Пользователь сохранен";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "ошибка";
    }

    public List<TelegramProduct> selectProductFromUsers(Integer companyId) {
        String SQL = "select id, product_name from products where company_id = ?";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setInt(1, companyId);
            ResultSet rs = preparedStatement.executeQuery();
            List<TelegramProduct> list = new ArrayList<>();
            while (rs.next())
            {
                String path = selectImagePath(rs.getInt(1));
                list.add(new TelegramProduct(rs.getInt(1), rs.getString(2), path));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String selectImagePath(Integer productId) {
        String SQL = "select path from image where product_id = ?";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
               return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Integer selectFromTelegramReg(String telegramId) {
        String SQL = "select company_id from telegram_registration tr where telegram_id = ?";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setString(1, telegramId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
