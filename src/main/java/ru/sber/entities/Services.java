package ru.sber.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
@JacksonXmlRootElement(localName = "services")
public class Services {

    @JacksonXmlProperty(localName = "serv")
    private Serv serv;

    public Serv getServ() {
        return serv;
    }

    public void setServ(Serv serv) {
        this.serv = serv;
    }

    @Override
    public String toString() {
        return "XmlObgects.Services{"
                + "serv=" + serv +
                '}';
    }
}
