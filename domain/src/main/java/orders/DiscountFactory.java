package orders;

import shared.ProductType;

import java.util.List;

public interface DiscountFactory {
    List<DiscountPolicy> getByProductType(ProductType productType);
}
