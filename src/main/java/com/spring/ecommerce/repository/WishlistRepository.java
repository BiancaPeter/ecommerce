package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
   // Wishlist findByUser(User user);
}
