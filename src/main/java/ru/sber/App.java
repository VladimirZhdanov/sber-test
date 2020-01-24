package ru.sber;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.sber.db.Service;
import ru.sber.entities.Order;
import ru.sber.entities.Par;
import ru.sber.entities.ParList;
import ru.sber.logic.DAO;
import ru.sber.logic.Parser;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class App {

    public static void main(String[] args) throws IOException, IllegalAccessException {

        Parser parser = new Parser();
        DAO dao = new DAO();

        parser.getDocList(parser.parseXML()).forEach(System.out::println);
        System.out.println("--------------------------------");
        Map<String, String> attributes = parser.getAttributes(parser.parseXML());
        for (Map.Entry<String, String> maps : attributes.entrySet()) {
            System.out.println(maps.getKey() + " = " + maps.getValue());
        }

        Service service = new Service();
        dao.storeDataToDB(parser.parseXML(),service);
    }
}
