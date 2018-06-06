package ua.danit.utils;

import ua.danit.dao.ClientDAO;
import ua.danit.dao.ItemDAO;
import ua.danit.model.Client;
import ua.danit.model.Item;

import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args) {
        Date date1 = new Date(System.currentTimeMillis());
        System.out.println(date1.toString());
    }
}
