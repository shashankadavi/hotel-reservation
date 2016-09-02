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

@Component("roomDao")
public class RoomDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean createRoom(int roomType){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roomType", roomType);
		
		return jdbc.update("insert into room (roomType) values(:roomType)", params) == 1;
	}

	public boolean createMultipleRooms(List<Integer> roomTypeList) {
		for(Integer roomType: roomTypeList) {
			createRoom(roomType);
		}
		return true;
	}
	
	public List<Room> getRooms() {
		
		return jdbc.query("select * from room", new RowMapper<Room>() {
			public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
				Room room = new Room();
				room.setRoomNum(rs.getInt("roomNum"));
				room.setRoomType(rs.getInt("roomType"));
				System.out.println(room);
				return room;
			}
		});
	}
	
//	public List<Room> getRooms(int numBeds) {
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("numBeds", numBeds);
//		
//		return jdbc.query("select * from room where numBeds = :numBeds",params, new RowMapper<Room>() {
//			public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Room room = new Room();
//				room.setRoomNum(rs.getInt("roomNum"));
//				room.setNumBeds(rs.getInt("numBeds"));
//				//System.out.println(room);
//				return room;
//			}
//		});
//	}
	
	public List<Room> getRooms(int roomType) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roomType", roomType);
		
		return jdbc.query("select * from room where roomType = :roomType",params, new RowMapper<Room>() {
			public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
				Room room = new Room();
				room.setRoomNum(rs.getInt("roomNum"));
				room.setRoomType(rs.getInt("roomType"));
				//System.out.println(room);
				return room;
			}
		});
	}
	

	public int maxCount(int roomType) {
		return getRooms(roomType).size();
	}

	public List<Integer> getRoomNums(int roomType) {
		List<Room> rooms = getRooms(roomType);
		List<Integer> roomNums = new ArrayList<Integer>();
		for(Room room: rooms){
			roomNums.add(room.getRoomNum());
		}
		return roomNums;
	}
}
