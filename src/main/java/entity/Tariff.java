package entity;
/**
 * Tariff entity
 *
 * @author R.Kotyk
 *
 * */
public class Tariff {
    private int id;
    private String name;
    private String description;
    private int price;
    private int service_id;
    private Service service;
    private boolean best;

    /**
     * Empty constructor
     * */
    public Tariff(){

    }
    /**
     *  shows tariff id
     *  @return tariff id
     * */
    public int getId() {
        return id;
    }

    /**
     * To set tariff id
     * @param id new tariff id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  shows tariff name
     * @return tariff name
     * */
    public String getName() {
        return name;
    }

    /**
     *  To set new tariff name
     * @param name new tariff name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  shows tariff description
     * @return tariff description
     * */
    public String getDescription() {
        return description;
    }

    /**
     *  To set tariff description
     * @param description new tariff description
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  shows tariff price
     *  @return tariff price
     * */
    public int getPrice() {
        return price;
    }

    /**
     *  To set new tariff price
     *  @param price new tariff price
     * */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *  shows tariff service id
     *  @return tariff service id
     * */
    public int getService_id() {
        return service_id;
    }

    /**
     *  To set tariff service id
     * @param service_id new tariff service id
     * */
    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    /**
     *  show tariff service
     * @return tariff service
     * */
    public Service getService() {
        return service;
    }

    /**
     *  To set tariff service
     * @param service new tariff service
     * */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     *  shows is it tariff is the best
     * @return tariff best
     * */
    public boolean isBest() {
        return best;
    }

    /**
     *  To set a flag that this tariff is the best
     * @param best new best value
     * */
    public void setBest(boolean best) {
        this.best = best;
    }

    @Override
    public String toString() {
        return ""+id;
    }
}
