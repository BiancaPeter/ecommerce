package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findAllByUser(User user);

    List<CartItem> findAllByUser_Id(Long userId);

    void deleteAllByUser(User user);

    @Modifying
    @Query("delete from CartItem c where c.user=:user")
    void deleteAllOfUser(@Param("user") User user);

}
