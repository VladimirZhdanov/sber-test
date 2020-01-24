package ru.sber.logic;

import ru.sber.db.Service;
import ru.sber.entities.Order;
import ru.sber.entities.Par;
import ru.sber.entities.ParList;

import java.util.List;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class DAO {

    public DAO() {
    }

    /**
     * Stores data to the data base.
     *
     * @param order - Order
     * @param service - service
     * 3. Задача со звездочкой создать в базе таблицу справочник со значениями из первой части
     */
    public void storeDataToDB(Order order, Service service) {
        List<Par> pars = getParList(order);

        for (Par par : pars) {
            if ((par.getPar_list() != null) && (par.getName().equals("ВИД_ДОК"))) {
                for (ParList parr : par.getPar_list()) {
                    service.create(parr);
                }
            }
        }
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
}
