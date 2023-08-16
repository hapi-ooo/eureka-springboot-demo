package com.hazelrp.movieinfoservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {
  
  @RequestMapping("/{id}")
  public Movie getMovie(@PathVariable("id") String id) {
    return new Movie(id, "Test name " + id);
  }
}
