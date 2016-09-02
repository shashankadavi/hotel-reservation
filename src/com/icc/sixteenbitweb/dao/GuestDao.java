package com.icc.sixteenbitweb.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("guestDao")
public class GuestDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Transactional
	public boolean createGuest(Guest guest) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", guest.getUsername());
		params.addValue("name", guest.getName());
		params.addValue("email", guest.getEmail());
		params.addValue("password", guest.getPassword());
		params.addValue("authority", guest.getAuthority());
				
		return jdbc.update("insert into guests (username, name, email, password, authority) values(:username, :name, :email, :password, :authority)", params) == 1;
	}
	
	public Guest getGuest(Guest guest) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", guest.getUsername());
		List<Guest> listOfGuests = new ArrayList<>();
		
		listOfGuests.addAll( jdbc.query("select * from guests where username = :username",params, new RowMapper<Guest>() {
			
			public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
				Guest guest = new Guest();
				guest.setName(rs.getString("username"));
				guest.setUsername(rs.getString("name"));
				guest.setPassword(rs.getString("email"));
				guest.setAuthority(rs.getString("password"));
				guest.setEmail(rs.getString("authority"));
				return guest;
			}
		}));
		
		return listOfGuests.get(0);
	}
	public Guest updateGuest(Guest guest){
//			MapSqlParameterSource params = new MapSqlParameterSource();
//			params.addValue("username", guest.getUsername());
//			params.addValue("name", guest.getName());
//			params.addValue("email", guest.getEmail());
//			params.addValue("password", guest.getPassword());
//			params.addValue("authority", guest.getAuthority());
//			
//			jdbc.update("insert into guests (username, name, email, password, authority) values(:username, :name, :email, :password, :authority)", params);
//			
//			return jdbc.update("insert into guests (username, name, email, password, authority) values(:username, :name, :email, :password, :authority)", params) == 1;
			
			
			return null;//guestRepositoryNameHere.save(guest);
	}
	
	
	public Guest updatePassword(Guest guest){
		return null;
	}
}