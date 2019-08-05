package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalexTaxDaoJdbcTemplateImpl implements SalesTaxRateDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_SALES_TAX_RATE_BY_STATE_SQL =
            "select * from sales_tax_rate where state = ?";

    @Autowired
    public SalexTaxDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SalesTaxRate getTaxByState(String state) {
        System.out.println("STATE: " + state);
        try {
            return jdbcTemplate.queryForObject(SELECT_SALES_TAX_RATE_BY_STATE_SQL, this::mapRowToSalesTaxRate, state);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    //returns a single object for each row
    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getDouble("rate"));

        return salesTaxRate;
    }
}
