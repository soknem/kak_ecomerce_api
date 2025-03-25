package com.ecomerce.api.feature.order.dto;

import com.ecomerce.api.domain.OrderStatus;
import com.ecomerce.api.domain.User;
import jakarta.persistence.*;

import java.time.LocalDate;

public record OrderRequest(

        LocalDate orderDate,

        OrderStatus orderStatus,

        String userUuid

) {
}
