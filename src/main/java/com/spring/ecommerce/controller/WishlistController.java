package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddAndDeleteToWishlistDTO;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private WishlistItemService wishlistItemService;

    @Autowired
    public WishlistController(WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }


    @PostMapping("/add")
    public Wishlist addToWishlist(@RequestBody AddAndDeleteToWishlistDTO addToWishlistDTO) {
        return wishlistItemService.addItemToWishlist(addToWishlistDTO);
    }

    @GetMapping("/{userId}")
    public List<WishlistItem> getWishlistByUser(@PathVariable Long userId) {
        return wishlistItemService.getWishlistItemsByUser(userId);
    }

    //TODO: nu se sterge
    @DeleteMapping("/delete")
    public Wishlist deleteWishlistItemFromUser(@RequestBody AddAndDeleteToWishlistDTO deleteToWishlistDTO) {
        return wishlistItemService.deleteWishlistItemFromWishlistOfUser(deleteToWishlistDTO);
    }
}
