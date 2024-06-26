package com.ecom.demo.service.cart;

import com.ecom.demo.dto.CartDto;
import com.ecom.demo.dto.CartResponseDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

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
        double cost = product.getProductPrice()*cartDto.getQuantity();
        cart.setTotalAmount(cart.getTotalAmount()+cost);
        product.setQuantity(product.getQuantity()-cartDto.getQuantity());
        productService.updateProduct(product.getId(), product);
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
    }

    public boolean updateCart(CartDto cartDto){
        //Check if product exists in cart: userId, pId, quantity
        int cartId = getCartIdByUserId(cartDto.getUserId());
        if(cartId!=-1){
            Cart cart = getCartByCartId(cartId);
            if(cartHasProduct(cartDto.getpId(), cartId)){
//                CartItem cartItem = cartItemRepository.getCartItemByProductId(cartDto.getpId());
                CartItem cartItem = cartItemRepository.getCartItemByCartAndProduct(cart, productService.getProductById(cartDto.getpId()));
                Product product = productService.getProductById(cartDto.getpId());
                int initialQuantity = cartItem.getQuantity();
                int finalQuantity = cartDto.getQuantity();
                int changeInQuantity = finalQuantity - initialQuantity;
                if(changeInQuantity>0){
                    if(product.getQuantity()>=changeInQuantity){
                        cartItem.setQuantity(finalQuantity);
                        cartItemRepository.save(cartItem);
                        cart.setTotalAmount(cart.getTotalAmount() + (changeInQuantity*product.getProductPrice()));
                        cartRepository.save(cart);
                        product.setQuantity(product.getQuantity()-changeInQuantity);
                        productService.updateProduct(product.getId(), product);
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    cartItem.setQuantity(finalQuantity);
                    cartItemRepository.save(cartItem);
                    cart.setTotalAmount(cart.getTotalAmount() + (changeInQuantity*product.getProductPrice()));
                    cartRepository.save(cart);
                    product.setQuantity(product.getQuantity()-changeInQuantity);
                    productService.updateProduct(product.getId(), product);
                    return true;
                }
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

    public List<CartResponseDto> getCartProductsResponse(int cartId){
        //response List
        List<CartResponseDto> list = new ArrayList<>();
        //fetched list
        List<CartItem> list2 = cartItemRepository.findAllByCartId(cartId);
        for(CartItem x : list2){
            CartResponseDto temp = new CartResponseDto();
            temp.setProduct(x.getProduct());
            temp.setCart(x.getCart());
            temp.setQuantity(x.getQuantity());
            temp.setId(x.getId());
            double price = x.getProduct().getProductPrice() * x.getQuantity();
            temp.setTotalPrice(price);
            list.add(temp);
        }
        //returning response list
//        System.out.println(list);
        return list;
    }

    public Cart getCartByCartId(int cartId){
        return cartRepository.findById(cartId).get();
    }

    @Transactional
    public boolean deleteProductById(int pId, int userId){
        int cartId = getCartIdByUserId(userId);
        if(cartHasProduct(pId, cartId)){
            Product product = productService.getProductById(pId);
//            CartItem cartItem = cartItemRepository.getCartItemByProductId(pId);
            CartItem cartItem = cartItemRepository.getCartItemByCartAndProduct(getCartByCartId(cartId), product);
            product.setQuantity(product.getQuantity()+cartItem.getQuantity());
            productService.updateProduct(pId, product);
            Cart cart = cartRepository.findByUserId(userId);
            double cost = cart.getTotalAmount() - (product.getProductPrice()*cartItem.getQuantity());
            cart.setTotalAmount(cost);
            cartRepository.save(cart);
            cartItemRepository.deleteByProductId(pId);
            return true;
        }
        else{
            return false;
        }
    }

}
