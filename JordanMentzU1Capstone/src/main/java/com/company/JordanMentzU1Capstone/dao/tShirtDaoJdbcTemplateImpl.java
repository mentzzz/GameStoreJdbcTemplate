package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.tShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class tShirtDaoJdbcTemplateImpl implements tShirtDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_T_SHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values (?, ?, ?, ?, ?)";

    private static final String SELECT_T_SHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";

    private static final String SELECT_ALL_T_SHIRT_SQL =
            "select * from t_shirt";

    private static final String UPDATE_T_SHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";

    private static final String DELETE_T_SHIRT =
            "delete from t_shirt where t_shirt_id = ?";

    private static final String SELECT_T_SHIRT_BY_COLOR =
            "select * from t_shirt where color =? ";

    private static final String SELECT_T_SHIRT_BY_SIZE =
            "select * from t_shirt where size =? ";

    @Autowired
    public tShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public tShirt addTshirt(tShirt tShirt) {
        jdbcTemplate.update(
                INSERT_T_SHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        tShirt.setId(id);

        return tShirt;
    }

    @Override
    public tShirt getTshirt(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_T_SHIRT_SQL, this::mapRowToTshirt, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this invoice id, return null
            return null;
        }
    }

    @Override
    public List<tShirt> getAllTshirts() {
        return jdbcTemplate.query(SELECT_ALL_T_SHIRT_SQL, this::mapRowToTshirt);
    }

    @Override
    public void updateTshirt(tShirt tShirt) {
        jdbcTemplate.update(
                UPDATE_T_SHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.getId()
        );

    }

    @Override
    public void deleteTshirt(int id) {
        jdbcTemplate.update(DELETE_T_SHIRT, id);

    }

    @Override
    public List<tShirt> getTshirtByColor(String color) {
        return jdbcTemplate.query(SELECT_T_SHIRT_BY_COLOR, this::mapRowToTshirt, color);

    }

    @Override
    public List<tShirt> getTshirtBySize(String size) {
        return jdbcTemplate.query(SELECT_T_SHIRT_BY_SIZE, this::mapRowToTshirt, size);
    }

    private tShirt mapRowToTshirt(ResultSet rs, int rowNum) throws SQLException {
        tShirt tShirt = new tShirt();
        tShirt.setId(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getDouble("price"));
        tShirt.setQuantity(rs.getInt("quantity"));

        return tShirt;
    }
}
