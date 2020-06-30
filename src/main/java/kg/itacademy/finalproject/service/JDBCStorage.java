package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.model.StorageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCStorage {
    DbWorker dbWorker = new DbWorker();

    public List<StorageModel> selectFromUsers(Integer key, String date) {
        String SQL = "select total_cost, p_count, unit_price, product_id from p_storage where company_id = ?;";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setInt(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            List<StorageModel> list = new ArrayList<>();
            while (rs.next())
            {
                StorageModel model = new StorageModel();
                model.setCount(rs.getInt(2));
                model.setPricePerUnit(rs.getInt(3));
                model.setTotalPrice(rs.getInt(1));
                model.setProductName(selectProductName(rs.getInt(4)));
                list.add(model);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String selectProductName(Integer id) {
        String SQL = "select product_name from products  where id = ?";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
