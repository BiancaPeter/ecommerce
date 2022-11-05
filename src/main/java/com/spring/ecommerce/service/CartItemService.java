package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddToCartDTO;
import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.CartItemRepository;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartItemService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(ProductRepository productRepository, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }


    public CartItem addToCart(AddToCartDTO addToCartDTO) {
        Product foundProduct = productRepository.findById(addToCartDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(addToCartDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        CartItem cartItem = new CartItem();
        cartItem.setProduct(foundProduct);
        cartItem.setUser(foundUser);
        cartItem.setQuantity(addToCartDTO.getQuantity());
        return cartItemRepository.save(cartItem);
    }
}
