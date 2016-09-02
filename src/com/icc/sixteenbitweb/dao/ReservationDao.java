package com.icc.sixteenbitweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("reservationDao")
public class ReservationDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public ReservationDao() {

	}

	// Check if a room is available on a certain day.
	public boolean available(int roomNum, String date) {
		if (getReservations(roomNum, date).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	// Check if a room is available on a certain day with type.
		public boolean available(int roomNum, String date, int type) {
			if (getReservations(roomNum, date, type).isEmpty()) {
				return true;
			} else {
				return false;
			}
		}

	// Check if a reservation exists using id.
	public boolean available(int id) {
		if (getReservations(id).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	// Check how many rooms of a certain type are available on a certain day.
	public int typeAvailable(int type, String date) {
		return getReservationsByType(type, date).size();

	}

	// Get a list of all reservations.
	public List<Reservation> getReservations() {
		return jdbc.query("select * from reservations", new RowMapper<Reservation>() {
			public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reservation reservation = new Reservation();
				reservation.setRoomNum(rs.getInt("roomNum"));
				reservation.setName(rs.getString("guestName"));
				reservation.setDate(rs.getString("date"));
				return reservation;
			}
		});
	}

	// Get a reservation for a certain room on a certain day.
	public List<Reservation> getReservations(int roomNum, String date) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roomNum", roomNum);
		params.addValue("date", date);
		
		return jdbc.query("select * from reservations where date = :date and roomNum = :roomNum", params,
				new RowMapper<Reservation>() {
					public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
						Reservation reservation = new Reservation();
						reservation.setRoomNum(rs.getInt("roomNum"));
						reservation.setName(rs.getString("guestName"));
						reservation.setDate(rs.getString("date"));
						return reservation;
					}
				});
	}
	
	// Get a reservation for a certain room on a certain day with type.
		public List<Reservation> getReservations(int roomNum, String date, int type) {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("roomNum", roomNum);
			params.addValue("date", date);
			params.addValue("type", type);
			
			return jdbc.query("select * from reservations where roomNum = :roomNum and date = :date and type = :type", params,
					new RowMapper<Reservation>() {
						public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
							Reservation reservation = new Reservation();
							reservation.setRoomNum(rs.getInt("roomNum"));
							reservation.setName(rs.getString("guestName"));
							reservation.setDate(rs.getString("date"));
							reservation.setType(rs.getInt("type"));
							return reservation;
						}
					});
		}

	// Get a reservation by its id.
	public List<Reservation> getReservations(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.query("select * from reservations where id = :id", params, new RowMapper<Reservation>() {
			public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reservation reservation = new Reservation();
				reservation.setRoomNum(rs.getInt("roomNum"));
				reservation.setName(rs.getString("guestName"));
				reservation.setDate(rs.getString("date"));
				return reservation;
			}
		});
	}

	// Get a list of reservations for a certain type on a certain day.
	public List<Reservation> getReservationsByType(int type, String date) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("type", type);
		params.addValue("date", date);

		return jdbc.query("select * from reservations where date = :date and type = :type", params,
				new RowMapper<Reservation>() {
					public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
						Reservation reservation = new Reservation();
						reservation.setRoomNum(rs.getInt("roomNum"));
						reservation.setName(rs.getString("guestName"));
						reservation.setDate(rs.getString("date"));
						reservation.setType(rs.getInt("type"));
						return reservation;
					}
				});
	}

	// Create a reservation.
	public boolean createReservation(int roomNum, String name, String date, int type) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roomNum", roomNum);
		params.addValue("name", name);
		params.addValue("date", date);
		params.addValue("type", type);

		return jdbc.update(
				"insert into reservations (roomNum,guestName,date,type) values(:roomNum, :name, :date, :type)",
				params) == 1;

	}

	// See if a room is available on a certain day, if so, create a reservation.
	public boolean setReservation(int roomNum, String name, String date, int type) {
		if (available(roomNum, date, type)) {
			if (!createReservation(roomNum, name, date, type)) {
				System.out.println("Reservation could not been made.");
				return false;
			}
		} else {
			System.out.println("Reservation could not be made.");
			return false;
		}
		System.out.println("Reservation has been made.");
		return true;
	}

	// See if a room is available on multiple days, if so, create a reservation.
	@Transactional
	public boolean setReservations(int roomNum, String name, List<String> dates, int type) {
		for (String date : dates) {
			if (!available(roomNum, date, type)) {
				System.out.println("Reservation could not be made on " + date);
				return false;
			}
		}
		for (String date : dates) {
			if (!setReservation(roomNum, name, date, type)) {
				System.out.println("Reservation could not be made: Problem with database.");
				return false;
			}
		}
		System.out.println("Reservations created!!!");
		return true;
	}

	// Cancel a reservation.
	public boolean removeReservation(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("delete from reservations where id = :id", params) == 1;
	}

	public boolean cancel(int id) {
		if (!available(id)) {
			if (!removeReservation(id)) {
				System.out.println("Reservation could not be deleted.");
				return false;
			}
		} else {
			System.out.println("Reservation could not be deleted.");
			return false;
		}
		System.out.println("Reservation has been deleted.");
		return true;
	}

	// Get a list of dates between a start and end date.
	public List<String> getDates(String start, String end) {

		List<String> sDates = new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();

		DateFormat formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy");

		try {
			Date startDate = (Date) formatter.parse(start);
			Date endDate = (Date) formatter.parse(end);

			long interval = 24 * 1000 * 60 * 60; // 1 day in milliseconds

			long curTime = startDate.getTime();
			long endTime = endDate.getTime();

			while (curTime <= endTime) {
				dates.add(new Date(curTime));
				curTime += interval;
			}

			for (int i = 0; i < dates.size(); i++) {
				Date lDate = (Date) dates.get(i);
				sDates.add(formatter.format(lDate));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return sDates;
	}
}
