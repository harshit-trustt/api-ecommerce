package com.ecom.demo.controller;

import com.ecom.demo.dto.ApiResponse;
import com.ecom.demo.dto.CartDto;
import com.ecom.demo.dto.CartResponseDto;
import com.ecom.demo.entity.Product;
import com.ecom.demo.service.cart.CartService;
import com.ecom.demo.service.cart.CartServiceImpl;
import com.ecom.demo.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    private ProductService productService;

    //Add to cart

    @Operation(summary = "insert into cart", description = "Inserts into address")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully Inserted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Couldn't insert into cart")
    })
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody CartDto cartDto){
        int userId = cartDto.getUserId();
        int cartId = cartService.getCartIdByUserId(userId);
        if(cartId==-1){
            return new ResponseEntity<>(new ApiResponse(false, "User does not exist"), HttpStatus.NOT_FOUND);
        }
        Product product = productService.getProductById(cartDto.getpId());
        if(cartDto.getQuantity() > product.getQuantity()){
            return new ResponseEntity<>(new ApiResponse(false, "Quantity not available"), HttpStatus.BAD_REQUEST);
        }
        cartService.addToCart(cartDto, cartId);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.OK);
    }


    @Operation(summary = "Get a Cart by Userid", description = "Returns a Cart as per the Userid")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Employee was not found")
    })
    @GetMapping("/{userID}")
    public ResponseEntity<List<CartResponseDto>> getCart(@PathVariable int userID){
        List<CartResponseDto> list = cartService.getCartProductsResponse(cartService.getCartIdByUserId(userID));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Operation(summary = "Update a Cart by id", description = "Updates a Cart as per the id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The cart was not found")
    })
    @PutMapping
    public ResponseEntity<ApiResponse> updateCart(@RequestBody CartDto cartDto){
        boolean res = cartService.updateCart(cartDto);
        if(res){
            return new ResponseEntity<>(new ApiResponse(true, "product updated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "operation not performed"), HttpStatus.CONFLICT);
    }

    @Operation(summary = "Delete a Cart by id", description = "Deletes a Cart as per the id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The cart was not found")
    })
    @DeleteMapping("/{userId}/{pId}")
    public ResponseEntity<ApiResponse> deleteFromCart(@PathVariable int userId, @PathVariable int pId){
        boolean suc = cartService.deleteProductById(pId, userId);
        if(suc){
            return new ResponseEntity<>(new ApiResponse(true, "Product Deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "Product not found"), HttpStatus.NOT_FOUND);
    }
}

