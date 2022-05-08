package billingSystem;

import java.util.ArrayList;

public class Order {

    private final ArrayList < Item > items;
    private final Integer miscCount;
    private final Integer luxuryCount;
    private final Integer essentialCount;

    public Order(
        ArrayList < Item > items,
        Integer miscCount,
        Integer luxuryCount,
        Integer essentialsCount) {
        this.items = items;
        this.miscCount = miscCount;
        this.luxuryCount = luxuryCount;
        this.essentialCount = essentialsCount;
    }

    public ArrayList < Item > getOrderItems() {
        return items;
    }

    public Integer getMiscCount() {
        return miscCount;
    }

    public Integer getLuxuryCount() {
        return luxuryCount;
    }

    public Integer getEssentialsCount() {
        return essentialCount;
    }
}