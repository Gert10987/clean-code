package com.easyprogramming.orders;

import com.easyprogramming.shared.ProductType;

import java.util.List;

public interface DiscountFactory {
    List<DiscountPolicy> getByProductType(ProductType productType);
}
