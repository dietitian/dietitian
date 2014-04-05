package zkl.dietitian.utils.datebase;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	private static ComboPooledDataSource ds;
	
	static{
		ds = new ComboPooledDataSource("transenly");
	}
	
	/**
	 * 获得数据源
	 * @return
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 获得链接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	

}
