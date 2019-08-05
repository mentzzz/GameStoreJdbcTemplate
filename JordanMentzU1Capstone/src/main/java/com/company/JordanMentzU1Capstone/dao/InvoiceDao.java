package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice saveInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoices();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int id);
}
