package com.ecom.demo.service;

import com.ecom.demo.dto.CartDto;
import com.ecom.demo.entity.Cart;
import com.ecom.demo.entity.CartItem;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.Users;
import com.ecom.demo.repository.CartItemRepository;
import com.ecom.demo.repository.CartRepository;
import com.ecom.demo.service.product.ProductService;
import com.ecom.demo.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public int getCartIdByUserId(int userId){
        Optional<Users> usersOptional = userService.findUserById(userId);
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            Optional<Cart> cartOptional = cartRepository.findByUser(user);
            Cart cart;
            if(cartOptional.isPresent()){
                cart = cartOptional.get();
                return cart.getId();
            }
            else{
                cart = new Cart(0, user);
            }
            cartRepository.save(cart);
            return cart.getId();
        }
        else {
            return -1;
        }
    }

    public void addToCart(CartDto cartDto, int cartId){
        Cart cart = getCartByCartId(cartId);
        Product product = productService.getProductById(cartDto.getpId());
        CartItem cartItem = new CartItem(cartDto.getQuantity(), product, cart);
        cart.getCartItems().add(cartItem);
        //adding to total cost
        double cost = product.getProductPrice()*cartDto.getQuantity();
        cart.setTotalAmount(cart.getTotalAmount()+cost);
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
    }

    public boolean updateCart(CartDto cartDto){
        //Check if product exists in cart: userId, pId, quantity
        int cartId = getCartIdByUserId(cartDto.getUserId());
        if(cartId!=-1){
            Cart cart = getCartByCartId(cartId);
            if(cartHasProduct(cartDto.getpId(), cartId)){
                CartItem cartItem = cartItemRepository.getCartItemByProductId(cartDto.getpId());
                Product product = productService.getProductById(cartDto.getpId());
                int changeInQuantity = cartItem.getQuantity() - cartDto.getQuantity();
                cartItem.setQuantity(cartDto.getQuantity());
                cartItemRepository.save(cartItem);
                cart.setTotalAmount(cart.getTotalAmount() + (changeInQuantity*product.getProductPrice()));
                cartRepository.save(cart);
                return true;
            }
        }
        return false;
    }

    public boolean cartHasProduct(int pId, int cartId){
        List<CartItem> list = getCartProducts(cartId);
        for(CartItem x : list){
            int id = x.getProduct().getId();
            if(id==pId){
                return true;
            }
        }
        return false;
    }

    public List<CartItem> getCartProducts(int cartId){
        return cartItemRepository.findAllByCartId(cartId);
    }

    public Cart getCartByCartId(int cartId){
        return cartRepository.findById(cartId).get();
    }

    @Transactional
    public boolean deleteProductById(int pId, int userId){
        int cartId = getCartIdByUserId(userId);
        if(cartHasProduct(pId, cartId)){
            cartItemRepository.deleteByProductId(pId);
            return true;
        }
        else{
            return false;
        }
    }

}
