package com.chinasofti.ordersys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.beanutils.locale.converters.SqlDateLocaleConverter;
import org.json.simple.JSONObject;

import com.chinasofti.ordersys.entity.Dish;
import com.chinasofti.ordersys.entity.OrderDish;
import com.chinasofti.ordersys.entity.OrderInfo;
import com.chinasofti.ordersys.servlets.admin.ChangeOrderStateServlet;

public class OrderDao extends BaseDao {

	public List<OrderInfo> searchByDate(Date begin, Date end) {
		List<OrderInfo> orders = new ArrayList<>();

		String sql = "SELECT orderId, orderBeginDate, orderEndDate, waiterId, orderState, tableId, sum(dishesPrice * num) sumPrice "
				+ "FROM orderdishes " + "INNER JOIN orderinfo ON orderdishes.orderReference = orderinfo.orderId "
				+ "INNER JOIN dishesinfo ON orderdishes.dishes = dishesinfo.dishesId "
				+ "where orderBeginDate BETWEEN ? and ? " + "GROUP BY orderId";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(begin.getTime()));
			ps.setDate(2, new java.sql.Date(end.getTime()));
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				OrderInfo order = new OrderInfo();
				order.setOrderId(r.getInt("orderId"));
				order.setOrderBeginDate(r.getString("orderBeginDate"));
				order.setOrderEndDate(r.getString("orderEndDate"));
				order.setWaiter(new UserDao().getUser(r.getInt("waiterId")));
				order.setOrderState(r.getInt("orderState"));
				order.setTableId(r.getInt("tableId"));
				order.setSumPrice(r.getInt("sumPrice"));
				orders.add(order);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}

	public OrderInfo searchById(int orderId) {
		String sql = "SELECT orderId, orderBeginDate, orderEndDate, waiterId, orderState, tableId, sum(dishesPrice * num) sumPrice "
				+ "FROM orderdishes "
				+ "INNER JOIN orderinfo ON orderdishes.orderReference = orderinfo.orderId "
				+ "INNER JOIN dishesinfo ON orderdishes.dishes = dishesinfo.dishesId "
				+ "where orderId = ? "
				+ "GROUP BY orderId";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, orderId);
			ResultSet r = ps.executeQuery();
			if (r.next()) {
				OrderInfo order = new OrderInfo();
				order.setOrderId(r.getInt("orderId"));
				order.setOrderBeginDate(r.getString("orderBeginDate"));
				order.setOrderEndDate(r.getString("orderEndDate"));
				order.setWaiter(new UserDao().getUser(r.getInt("waiterId")));
				order.setOrderState(r.getInt("orderState"));
				order.setTableId(r.getInt("tableId"));
				order.setSumPrice(r.getInt("sumPrice"));
				return order;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<OrderDish> getDishes(int orderId) {
		List<OrderDish> dishes = new ArrayList<>();

		String sql = "SELECT orderId, dishes, num, tableId FROM orderdishes, orderinfo WHERE orderReference=orderId and orderReference=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, orderId);
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				OrderDish dish = new OrderDish();
				dish.setOrderId(r.getInt(1));
				dish.setDish(new DishDao().getDish(r.getInt(2)));
				dish.setNum(r.getInt(3));
				dish.setTableId(r.getInt(4));
				dishes.add(dish);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dishes;
	}

	public boolean addOrder(OrderInfo order, List<OrderDish> dishes) {
		String sql = "insert into orderinfo " + "(orderBeginDate, orderEndDate, waiterId, tableId, orderState) "
				+ "values(?, ?, ?, ?, ?);";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, order.getOrderBeginDate());
			ps.setString(2, order.getOrderEndDate());
			ps.setInt(3, order.getWaiter().getUserId());
			ps.setInt(4, order.getTableId());
			ps.setInt(5, order.getOrderState());
			System.out.println(ps);
			if (ps.executeUpdate() == -1)
				return false;
			int orderId;
			ResultSet result = ps.getGeneratedKeys();
			if (result.next()) {
				orderId = result.getInt(1);
				sql = "insert into orderdishes " + "(orderReference, dishes, num) " + "values (?, ?, ?);";
				ps = con.prepareStatement(sql);
				for (OrderDish dish : dishes) {
					ps.setInt(1, orderId);
					ps.setInt(2, dish.getDish().getId());
					ps.setInt(3, dish.getNum());
					System.out.println(ps);
					if (ps.executeUpdate() == -1)
						return false;
				}
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int count() {
		String sql = "select count(*) from orderinfo";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<OrderInfo> getOrderByPage(int page, int num) {
		List<OrderInfo> orders = new ArrayList<>();
		String sql = "select * from orderinfo where orderState=0 order by orderId asc limit ?, ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, page * num);
			ps.setInt(2, num);
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				OrderInfo order = new OrderInfo();
				order.setOrderId(r.getInt("orderId"));
				order.setOrderBeginDate(r.getString("orderBeginDate"));
				order.setOrderEndDate(r.getString("orderEndDate"));
				order.setWaiter(new UserDao().getUser(r.getInt("waiterId")));
				order.setOrderState(r.getInt("orderState"));
				order.setTableId(r.getInt("tableId"));
				//order.setSumPrice(r.getInt("sumPrice"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	public List<OrderInfo> getOrderByState(int state) {
		List<OrderInfo> orders = new ArrayList<>();
		String sql = "SELECT orderId, orderBeginDate, orderEndDate, waiterId, orderState, tableId, sum(dishesPrice * num) sumPrice "
				+ "FROM orderdishes "
				+ "INNER JOIN orderinfo ON orderdishes.orderReference = orderinfo.orderId "
				+ "INNER JOIN dishesinfo ON orderdishes.dishes = dishesinfo.dishesId "
				+ "where orderState = ? "
				+ "GROUP BY orderId";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, state);
			System.out.println(ps);
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				OrderInfo order = new OrderInfo();
				order.setOrderId(r.getInt("orderId"));
				order.setOrderBeginDate(r.getString("orderBeginDate"));
				order.setOrderEndDate(r.getString("orderEndDate"));
				order.setWaiter(new UserDao().getUser(r.getInt("waiterId")));
				order.setOrderState(r.getInt("orderState"));
				order.setTableId(r.getInt("tableId"));
				order.setSumPrice(r.getInt("sumPrice"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	public boolean changeOrderState(int orderId, int state) {
		String sql = "update orderinfo set orderState=? where orderId=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, orderId);
			return ps.executeUpdate() != -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<OrderDish> getrtdishes() {
		List<OrderDish> rtdishes = new ArrayList<>();
		try{
			String sql = "select orderId from orderinfo where orderState = 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int orderId = rs.getInt(1);
				rtdishes.addAll(getDishes(orderId));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rtdishes;
		
	}
	
}
