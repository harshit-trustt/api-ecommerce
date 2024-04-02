package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.dto.CartDto;
import com.ecom.demo.dto.CartResponseDto;
import com.ecom.demo.service.cart.CartService;
import com.ecom.demo.service.cart.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //Add to cart
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody CartDto cartDto){
        int userId = cartDto.getUserId();
        int cartId = cartService.getCartIdByUserId(userId);
        if(cartId==-1){
            return new ResponseEntity<>(new ApiResponse(false, "User does not exist"), HttpStatus.NOT_FOUND);
        }
        cartService.addToCart(cartDto, cartId);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.OK);
    }


    @GetMapping("/{userID}")
    public ResponseEntity<List<CartResponseDto>> getCart(@PathVariable int userID){
        List<CartResponseDto> list = cartService.getCartProductsResponse(cartService.getCartIdByUserId(userID));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<ApiResponse> updateCart(@RequestBody CartDto cartDto){
        boolean res = cartService.updateCart(cartDto);
        if(res){
            return new ResponseEntity<>(new ApiResponse(true, "product updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(true, "success"), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{pId}")
    public ResponseEntity<ApiResponse> deleteFromCart(@PathVariable int userId, @PathVariable int pId){
        boolean suc = cartService.deleteProductById(pId, userId);
        if(suc){
            return new ResponseEntity<>(new ApiResponse(true, "Product Deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "Product not found"), HttpStatus.NOT_FOUND);
    }
}

