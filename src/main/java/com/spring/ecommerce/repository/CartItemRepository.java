package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findAllByUser(User user);

    List<CartItem> findAllByUser_Id(Long userId);

    void deleteAllByUser(User user);

}
