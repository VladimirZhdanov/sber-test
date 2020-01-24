package ru.sber.logic;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.sber.entities.Order;
import ru.sber.entities.Par;
import ru.sber.entities.ParList;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class Parser {

    public Parser() {
    }

    /**
     * Parses XML file to object.
     *
     * @return - Order
     * @throws IOException - IOException
     */
    public Order parseXML() throws IOException {
        String path = "C:\\Projects\\sber-test\\src\\main\\resources\\source.xml";
        File file = new File(path);
        file.getCanonicalPath();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = buildString(new FileInputStream(file));
        return xmlMapper.readValue(xml, Order.class);
    }

    /**
     * Builds String from input stream.
     *
     * @param is - InputStream
     * @return - String
     * @throws IOException - IOException
     */
    public String buildString(InputStream is) throws IOException {
        String result;
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        while ((result = bufferedReader.readLine()) != null) {
            stringBuilder.append(result);
        }

        bufferedReader.close();
        return stringBuilder.toString();
    }

    /**
     * Gets list of documents from order(xml).
     *
     * @param order - Order
     * @return - Set<String>
     * 1. Необходимо сформировать коллекцию, содержащую все виды документов в отсортированном порядке
     */
    public Set<String> getDocList(Order order) {
        List<Par> pars = getParList(order);
        Set<String> docs = new TreeSet<>();

        for (Par par : pars) {
            if ((par.getPar_list() != null) && (par.getName().equals("ВИД_ДОК"))) {
                for (ParList parList : par.getPar_list()) {
                    docs.add(parList.getValue());
                }
            }
        }
        return docs;
    }

    /**
     * Gets par list form order object
     *
     * @param order - Order
     * @return - List<Par>
     */
    private List<Par> getParList(Order order) {
        return order.getServices().getServ().getPars();
    }

    /**
     * Gets attributes where par step = 1 and name = 'ГРАЖДАНСТВО'.
     *
     * @param order - order
     * @throws IllegalAccessException - IllegalAccessException
     * 2. Вывести имена и значения всех атрибутов для par step="1" name="ГРАЖДАНСТВО"
     */
    public Map<String, String> getAttributes(Order order) throws IllegalAccessException {
        Map<String, String> result = new HashMap<>();
        List<Field> fields = new ArrayList<>();
        List<Par> parList = order.getServices().getServ().getPars();

        for (Par pars : parList) {
            if (pars.getName().equals("ГРАЖДАНСТВО")) {
                Class clazz = pars.getClass();
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.getType() == String.class && field.getModifiers() == Modifier.PRIVATE) {
                        fields.add(field);
                    }
                }
                for (Field field : fields) {
                    field.setAccessible(true);
                    result.put(field.getName(), (String) field.get(pars));
                }
            }
        }
        return result;
    }
}
