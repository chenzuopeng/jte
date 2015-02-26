package demo.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Nov 19, 2010
 * @version 1.00.00
 * @history:
 * 
 */
public class UserRowMapper implements RowMapper<User>{

	public User mapRow(ResultSet rs, int index) throws SQLException {
		User user=new User();
		user.setId(rs.getLong("id"));
		user.setCreate_time(rs.getTimestamp("create_time"));
		user.setEmail(rs.getString("email"));
		user.setLogin_name(rs.getString("login_name"));
		user.setName(rs.getString("name"));
		user.setPlain_password(rs.getString("plain_password"));
		user.setSha_password(rs.getString("sha_password"));
		user.setStatus(rs.getString("status"));
		return user;
	}

}
