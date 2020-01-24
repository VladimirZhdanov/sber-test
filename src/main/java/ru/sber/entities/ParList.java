package ru.sber.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
@Entity
@Table(name = "Directory")
@JacksonXmlRootElement(localName = "par_list")
public class ParList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JacksonXmlProperty(localName = "value", isAttribute = true)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ParList{"
                + "id=" + id
                + ", value='" + value + '\''
                + '}';
    }
}
