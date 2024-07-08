package com.ecom5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom5.exception.ProductException;
import com.ecom5.exception.UserException;
import com.ecom5.model.Rating;
import com.ecom5.model.User;
import com.ecom5.request.RatingRequest;
import com.ecom5.service.RatingService;
import com.ecom5.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating>createRating(@RequestBody RatingRequest req,
			@RequestHeader("Authorization")String jwt) throws UserException, ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Rating rating = ratingService.createRating(req, user);
		
		return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>>getProductsRating(@PathVariable Long productId,
			@RequestHeader("Authorization")String jwt) throws UserException, ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Rating>rating = ratingService.getProductsRating(productId);
		
		return new ResponseEntity<>(rating, HttpStatus.CREATED);
	}
	
	
}
