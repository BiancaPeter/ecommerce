package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddAndDeleteToWishlistDTO;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.repository.UserRepository;
import com.spring.ecommerce.repository.WishlistItemRepository;
import com.spring.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class WishlistItemService {
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private WishlistRepository wishlistRepository;
    private WishlistItemRepository wishlistItemRepository;

    @Autowired
    public WishlistItemService(ProductRepository productRepository, UserRepository userRepository, WishlistRepository wishlistRepository, WishlistItemRepository wishlistItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
        this.wishlistItemRepository = wishlistItemRepository;
    }

    public Wishlist addItemToWishlist(AddAndDeleteToWishlistDTO addToWishlistDTO) {
        Product foundProduct = productRepository.findById(addToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(addToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        Wishlist foundWishlist = foundUser.getWishlist();
        foundWishlist.setUser(foundUser);
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(foundProduct);
        wishlistItem.setWishlist(foundWishlist);
        foundWishlist.getWishlistItems().add(wishlistItem);
        return wishlistRepository.save(foundWishlist);

    }


    public List<WishlistItem> getWishlistItemsByUser(Long user_id) {
        User foundedUser = userRepository.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the user was not found"));
        return wishlistItemRepository.findAllByWishlist_User(foundedUser);
    }

    public void deleteWishlistItemFromUserWishlist(AddAndDeleteToWishlistDTO deleteToWishlistDTO) {
        Product foundProduct = productRepository.findById(deleteToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(deleteToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        WishlistItem wishlistItemToDelete = wishlistItemRepository.findWishlistItemByWishlist_UserAndProduct(foundUser, foundProduct).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "whishlistitem not found"));
        wishlistItemRepository.delete(wishlistItemToDelete);

        //foundUser.getWishlist().getWishlistItems().remove(wishlistItemToDelete);
        //return wishlistRepository.save(foundUser.getWishlist());
    }
}
