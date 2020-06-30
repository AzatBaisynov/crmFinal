package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.model.JDBCRevenueModel;
import kg.itacademy.finalproject.model.JDBCSalesModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCProfit {
    DbWorker dbWorker = new DbWorker();

    public List<JDBCSalesModel> getModel(Integer companyId, String date){
        List<JDBCRevenueModel> list = selectFromUsers(companyId, date);
        Set<String> names = new HashSet<>();
        list.forEach(x -> names.add(x.getUserFullName()));
        List<String> listNames = new ArrayList<>();
        names.forEach(x -> listNames.add(x));
        List<JDBCSalesModel> returnList = new ArrayList<>();
        for (int i = 0; i < listNames.size(); i++){
            JDBCSalesModel model = new JDBCSalesModel();
            model.setName(listNames.get(i));
            List<JDBCRevenueModel> revenue = new ArrayList<>();
            Integer sum = 0;
            for (int j = 0; j < list.size(); j++){
                if(list.get(j).getUserFullName().equals(listNames.get(i))){
                    JDBCRevenueModel revModel = new JDBCRevenueModel();
                    revModel.setDate(list.get(j).getDate());
                    revModel.setRevenue(list.get(j).getRevenue());
                    revModel.setProductName(list.get(j).getProductName());
                    revenue.add(revModel);
                    sum = sum + list.get(j).getRevenue();
                }
            }
            model.setList(revenue);
            model.setTotal(sum);
            returnList.add(model);
        }
        return returnList;
    }

    private List<JDBCRevenueModel> selectFromUsers(Integer id, String date) {
        String SQL = "select date_created, p_count ,unit_price ,product_id ,user_id from sales where company_id = ?";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<JDBCRevenueModel> list = new ArrayList<>();
            while (rs.next())
            {
                if (rs.getTimestamp(1).toLocalDateTime().toLocalDate().toString().equals(date)) {
                    Integer price1 = rs.getInt(3);
                    Integer price2 = selectFromStorage(rs.getInt(4));
                    Integer count = rs.getInt(2);
                    Integer firstAction = price1 - price2;
                    Integer revenue = count * firstAction;

                    JDBCRevenueModel model = new JDBCRevenueModel();
                    model.setDate(rs.getTimestamp(1));
                    model.setRevenue(revenue);
                    model.setProductName(selectProductName(rs.getInt(4)));
                    model.setUserFullName(selectUserName(rs.getInt(5)));

                    list.add(model);
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Integer selectFromStorage(Integer id) {
        String SQL = "select unit_price from p_storage where product_id = ?";
        try (Connection conn = dbWorker.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setInt(1, id);
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
