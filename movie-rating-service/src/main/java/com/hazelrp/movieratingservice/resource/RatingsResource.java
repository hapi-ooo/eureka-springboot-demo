package com.hazelrp.movieratingservice.resource;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

  @RequestMapping("/{movieId}")
  public Rating getRating(@PathVariable("movieId") String id) {
    return new Rating(id, 4);
  }

  @RequestMapping("/users/{userId}")
  public UserRating getUserRating(@PathVariable("userId") String userId) {
    return new UserRating( Arrays.asList(
      new Rating("1234", 4),
      new Rating("5678", 3)
    ));
    
  }

}
