package billingSystem;

public class Item {
    private Integer quantity;
    private String cardNumber;
    private String item;

    public Item(String item, Integer quantity, String cardNumber) {
        this.item = item;
        this.quantity = quantity;
        this.cardNumber = cardNumber;
    }

    public String getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}