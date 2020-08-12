package com.carbooking.carbookingapi.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.carbooking.carbookingapi.constant.DatabaseConstant;
import com.carbooking.carbookingapi.dao.UserDao;
import com.carbooking.carbookingapi.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private String TABLE = "user";

  private final String ID = "id";
  private final String NAME = "name";
  private final String PASSWORD = "password";
  private final String ROLE = "role";

  private final RowMapper<User> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
    User user = new User();
    user.setId(rs.getInt(ID));
    user.setName(rs.getString(NAME));
    user.setPassword(rs.getString(PASSWORD));
    user.setRole(rs.getString(ROLE));
    return user;
  };

  public List<User> find() throws Exception {
    StringBuilder sql = new StringBuilder();
    List<Object> parameters = new ArrayList<>();

    try {
      sql
        .append("SELECT * FROM ").append(TABLE);
      return jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

  public User findById(int id) throws Exception {
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
  public User findByName(String name) throws Exception {
    StringBuilder sql = new StringBuilder();

    try {
      sql
        .append("SELECT * FROM ").append(TABLE)
        .append(" WHERE ").append(NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK);
      return jdbcTemplate.queryForObject(sql.toString(), ROW_MAPPER, name);
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

  public void insert(User user) throws Exception {
    StringBuilder sql = new StringBuilder();
    ArrayList<Object> parameters = new ArrayList<>();

    try {
      sql
        .append("INSERT INTO ").append(TABLE).append(" (")
        .append(NAME).append(DatabaseConstant.SIGN_COMMA)
        .append(PASSWORD).append(DatabaseConstant.SIGN_COMMA)
        .append(ROLE)
        .append(")");
      sql
        .append(" VALUES ");
      sql
        .append("( ")
        .append(DatabaseConstant.SIGN_QESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(DatabaseConstant.SIGN_QESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(DatabaseConstant.SIGN_QESTION_MARK)
        .append(" )");
      parameters.add(user.getName());
      parameters.add(user.getPassword());
      parameters.add(user.getRole());
      jdbcTemplate.update(sql.toString(), parameters.toArray());
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

  public void update(User user) throws Exception {
    StringBuilder sql = new StringBuilder();
    ArrayList<Object> parameters = new ArrayList<>();

    try {
      sql
        .append("UPDATE ").append(TABLE).append(" SET ")
        .append(NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(PASSWORD).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
        .append(ROLE).append(DatabaseConstant.EQUAL_QUESTION_MARK);
      sql.append(" WHERE ").append(ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
      parameters.add(user.getName());
      parameters.add(user.getPassword());
      parameters.add(user.getRole());
      parameters.add(user.getId());

      jdbcTemplate.update(sql.toString(), parameters.toArray());
    } catch (DataAccessException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
  }

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
