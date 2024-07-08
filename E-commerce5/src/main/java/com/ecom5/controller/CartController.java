package com.ecom5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom5.exception.ProductException;
import com.ecom5.exception.UserException;
import com.ecom5.model.Cart;
import com.ecom5.model.User;
import com.ecom5.request.AddItemRequest;
import com.ecom5.response.ApiResponse;
import com.ecom5.service.CartService;
import com.ecom5.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart>findUserCart(@RequestHeader("Authorization")String jwt) throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Cart cart = cartService.findUserCart(user.getId());
		
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@GetMapping("/create")
	public ResponseEntity<Cart> createUserCart(@RequestHeader("Authorization")String jwt)throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Cart cart = cartService.createCart(user);
		
		return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
	}
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization")String jwt) throws UserException, ProductException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		cartService.addCartItem(user.getId(), req);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("item added to cart");
		res.setStatus(true);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
