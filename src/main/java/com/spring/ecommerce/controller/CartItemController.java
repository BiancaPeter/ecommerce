package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddToCartDTO;
import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody AddToCartDTO addToCartDTO){
        return cartItemService.addToCart(addToCartDTO);
    }
}
