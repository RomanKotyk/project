package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 *  Service entity
 *
 *  @author R.Kotyk
 *
 * */
public class Service implements Serializable {
    private int id;
    private String name;
    private String description;
    private ArrayList<Tariff> tariffList;

    public Service(){}

    public int getId() {
        return id;
    }

    /**
     *  To set service id
     *  @param id new service id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  show service name
     *  @return service name
     * */
    public String getName() {
        return name;
    }

    /**
     *  To set service name
     * @param name new service name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  show service description
     * @return service description
     * */
    public String getDescription() {
        return description;
    }

    /**
     *  To set service description
     * @param description new service description
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  show tariffs provided by the service
     *  @return list of tariffs
     * */
    public ArrayList<Tariff> getTariffList() {
        return tariffList;
    }

    /**
     *  To set service tariffs
     * @param tariffList new tariffs provided by the service
     * */
    public void setTariffList(ArrayList<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(name, service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
