package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDataBeans;
import beans.ItemDataBeans;

public class UserDataDao {
	public static UserDataDao getInstance() {
		return new UserDataDao();

	}

	public static ArrayList<ItemDataBeans> getBuyItemData(int userId) throws SQLException {
		ArrayList<ItemDataBeans> idbList = new ArrayList<ItemDataBeans>();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT\n" +
					"    t_buy_detail.* ,t_buy.*,m_item.*,m_delivery_method.*\n" +
					"FROM\n" +
					"    t_buy_detail INNER JOIN t_buy\n" +
					"    ON t_buy_detail.buy_id = t_buy.id\n" +
					"    INNER JOIN m_item\n" +
					"    ON t_buy_detail.item_id = m_item.id \n" +
					"    INNER JOIN m_delivery_method\n" +
					"    ON t_buy.delivery_method_id = m_delivery_method.id\n" +
					"    \n" +
					"    \n" +
					"WHERE\n" +
					"    t_buy.user_id = ? ");
			st.setInt(1, userId);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();

				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));
				idb.setDeliveryMethodName(rs.getString("m_delivery_method.name"));
				idb.setDeliveryMethodPrice(rs.getInt("m_delivery_method.price"));

				idbList.add(idb);



			}

			st.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("searching UserDataBeans by userId has been completed");
		return idbList;
	}

	public static ArrayList<ItemDataBeans> getBuyData(int userId,String id) throws SQLException {
		ArrayList<ItemDataBeans> idbList2 = new ArrayList<ItemDataBeans>();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT\n" +
					"    t_buy_detail.* ,t_buy.*,m_item.*,m_delivery_method.*\n" +
					"FROM\n" +
					"    t_buy_detail INNER JOIN t_buy\n" +
					"    ON t_buy_detail.buy_id = t_buy.id\n" +
					"    INNER JOIN m_item\n" +
					"    ON t_buy_detail.item_id = m_item.id \n" +
					"    INNER JOIN m_delivery_method\n" +
					"    ON t_buy.delivery_method_id = m_delivery_method.id\n" +
					"    \n" +
					"    \n" +
					"WHERE\n" +
					"    t_buy.user_id = ? AND t_buy.id = ?");
			st.setInt(1, userId);
			st.setString(2, id);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				ItemDataBeans idb2 = new ItemDataBeans();

				idb2.setId(rs.getInt("id"));
				idb2.setName(rs.getString("name"));
				idb2.setPrice(rs.getInt("price"));
				idb2.setDeliveryMethodName(rs.getString("m_delivery_method.name"));
				idb2.setDeliveryMethodPrice(rs.getInt("m_delivery_method.price"));

				idbList2.add(idb2);



			}

			st.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("searching UserDataBeans by userId has been completed");
		return idbList2;
	}


	public static BuyDataBeans getBuyData(int userId) throws SQLException {
		BuyDataBeans bdb = new BuyDataBeans();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT\r\n" +
					"    t_buy.* ,m_delivery_method.* \r\n" +
					"FROM\r\n" +
					"    t_buy INNER JOIN m_delivery_method\r\n" +
					"    ON t_buy.delivery_method_id = m_delivery_method.id\r\n" +
					"    \r\n" +
					"WHERE\r\n" +
					"    t_buy.id = ? ");
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				bdb.setId(rs.getInt("id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
				bdb.setBuyDate(rs.getDate("create_date"));
				bdb.setDeliveryMethodName(rs.getString("name"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
				bdb.setTotalPrice(rs.getInt("price")+rs.getInt("total_price"));



			}

			st.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("searching UserDataBeans by userId has been completed");
		return bdb;
	}

	public static BuyDataBeans getBuyData2(int userId,String id) throws SQLException {
		BuyDataBeans bdb2 = new BuyDataBeans();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT t_buy.* ,m_delivery_method.* FROM t_buy INNER JOIN m_delivery_method ON t_buy.delivery_method_id = m_delivery_method.id WHERE t_buy.user_id = ? AND t_buy.id = ?");
			st.setInt(1, userId);
			st.setString(2, id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				bdb2.setId(rs.getInt("id"));
				bdb2.setUserId(rs.getInt("user_id"));
				bdb2.setDelivertMethodId(rs.getInt("delivery_method_id"));
				bdb2.setBuyDate(rs.getTimestamp("create_date"));
				bdb2.setDeliveryMethodName(rs.getString("name"));
				bdb2.setDeliveryMethodPrice(rs.getInt("price"));
				bdb2.setTotalPrice(rs.getInt("total_price"));
				bdb2.getFormatDate();




			}

			st.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("searching UserDataBeans by userId has been completed");
		return bdb2;
	}





}