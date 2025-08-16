package uz.pdp.paymentsystemforcafe.projection;


public interface OrderItemShowPro {
    String getProductName();
    Float getProductPrice();
    Integer getProductCount();
    String getCreatedAt();
}
