package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.ProcessingFee;

public interface ProcessingFeeDao {

    ProcessingFee getFeeByItemType(String itemType);

}
