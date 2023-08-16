package com.hazelrp.movieratingservice.resource;

import java.util.List;

public class UserRating {
  private List<Rating> userRating;

  public UserRating(List<Rating> userRating) {
    this.userRating = userRating;
  }

  UserRating() {

  }

  public List<Rating> getUserRating() {
    return userRating;
  }

  public void setUserRating(List<Rating> userRating) {
    this.userRating = userRating;
  }
}
