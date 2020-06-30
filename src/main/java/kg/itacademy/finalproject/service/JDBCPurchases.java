package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPurchases {
    DbWorker dbWorker = new DbWorker();

            public List<PurchaseSalesProductsListModel> selectFromUsers(Integer key, String date) {
                String SQL = "select date_created, p_count, unit_price, product_id, user_id from purchases where company_id = ?;";
                try (Connection conn = dbWorker.connect();
                     PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
                    preparedStatement.setInt(1, key);
                    ResultSet rs = preparedStatement.executeQuery();
                    List<PurchaseSalesProductsListModel> list = new ArrayList<>();
                    while (rs.next())
                    {
                        if(rs.getTimestamp(1).toLocalDateTime().toLocalDate().toString().equals(date)) {
                            PurchaseSalesProductsListModel model = new PurchaseSalesProductsListModel();
                            model.setDate(rs.getTimestamp(1).toLocalDateTime().toLocalDate());
                            model.setCount(rs.getInt(2));
                            model.setPricePerUnit(rs.getInt(3));
                            model.setTotalPrice(rs.getInt(2) * rs.getInt(3));
                            model.setProductName(selectProductName(rs.getInt(4)));
                            model.setUserName(selectUserName(rs.getInt(5)));
                            list.add(model);
                        }
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

            private String selectUserName(Integer id) {
                String SQL = "select user_full_name from users where id = ?";
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
