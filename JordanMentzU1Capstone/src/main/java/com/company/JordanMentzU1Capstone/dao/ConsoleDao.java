package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.Console;

import java.util.List;

public interface ConsoleDao {
    Console addConsole(Console console);

    Console getConsole(int id);

    List<Console> getAllConsoles();

    void updateConsole(Console console);

    void deleteConsole(int id);

    List<Console> getConsolesByManufacturer(String manufacturer);
}
