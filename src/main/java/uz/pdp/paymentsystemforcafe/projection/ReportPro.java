package uz.pdp.paymentsystemforcafe.projection;

public interface ReportPro {
    String getProductName();
    Float getProductPrice();
    Long getProductCount();
    Float getTotalPrice();
    String getCreatedAt();

}

