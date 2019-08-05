package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.SalesTaxRate;

public interface SalesTaxRateDao {

    SalesTaxRate getTaxByState(String state);
}
