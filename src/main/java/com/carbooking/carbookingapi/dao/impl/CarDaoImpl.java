package com.carbooking.carbookingapi.dao.impl;

import com.carbooking.carbookingapi.constant.DatabaseConstant;
import com.carbooking.carbookingapi.domain.Car;
import com.carbooking.carbookingapi.dao.CarDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Kittitad S.
 */
@Repository
public class CarDaoImpl implements CarDao {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private String TABLE = "car";

  private final String ID = "id";
  private final String BRAND_NAME = "brand_name";
  private final String MODEL_NAME = "model_name";
  private final String PRICE = "price";
  private final String RESERVED = "reserved";

  private final RowMapper<Car> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
    Car car = new Car();
    car.setId(rs.getInt(ID));
    car.setBrandName(rs.getString(BRAND_NAME));
    car.setModelName(rs.getString(MODEL_NAME));
    car.setPrice(rs.getInt(PRICE));
    car.setReserved(rs.getBoolean(RESERVED));
    return car;
  };

  @Override
  public List<Car> find() throws Exception {
    StringBuilder sql = new StringBuilder();

    try {
      sql
        .append("SELECT * FROM ").append(TABLE);
      return jdbcTemplate.query(sql.toString(), ROW_MAPPER);
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public Car findById(int id) throws Exception {
    StringBuilder sql = new StringBuilder();

    try {
      sql
        .append("SELECT * FROM ").append(TABLE)
        .append(" WHERE ").append(ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
      return jdbcTemplate.queryForObject(sql.toString(), ROW_MAPPER, id);
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void insert(Car car) throws Exception {
    StringBuilder sql = new StringBuilder();
    ArrayList<Object> parameters = new ArrayList<>();

    try {
      sql
        .append("INSERT INTO ").append(TABLE).append(" (")
        .append(BRAND_NAME).append(DatabaseConstant.SIGN_COMMA)
        .append(MODEL_NAME).append(DatabaseConstant.SIGN_COMMA)
        .append(PRICE).append(DatabaseConstant.SIGN_COMMA)
        .append(RESERVED)
        .append(")");
      sql
        .append(" VALUES ");
      sql
        .append(" ( ")
        .append(DatabaseConstant.SIGN_QESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(DatabaseConstant.SIGN_QESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(DatabaseConstant.SIGN_QESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(DatabaseConstant.SIGN_QESTION_MARK)
        .append(" ) ");
      parameters.add(car.getBrandName());
      parameters.add(car.getModelName());
      parameters.add(car.getPrice());
      parameters.add(car.getReserved());
      jdbcTemplate.update(sql.toString(), parameters.toArray());
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void update(Car car) throws Exception {
    StringBuilder sql = new StringBuilder();
    ArrayList<Object> parameters = new ArrayList<>();

    try {
      sql
        .append("UPDATE ").append(TABLE).append(" SET ")
        .append(BRAND_NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(MODEL_NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(PRICE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(RESERVED).append(DatabaseConstant.EQUAL_QUESTION_MARK);
      sql.append(" WHERE ").append(ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
      parameters.add(car.getBrandName());
      parameters.add(car.getModelName());
      parameters.add(car.getPrice());
      parameters.add(car.getReserved());
      parameters.add(car.getId());

      jdbcTemplate.update(sql.toString(), parameters.toArray());
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void deleteById(int id) throws Exception {
    StringBuilder sql = new StringBuilder();

    try {
      sql
        .append("DELETE FROM ").append(TABLE)
        .append(" WHERE ").append(ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
      jdbcTemplate.update(sql.toString(), id);
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }
}
