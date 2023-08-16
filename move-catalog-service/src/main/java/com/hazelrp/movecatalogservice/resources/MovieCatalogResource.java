package com.hazelrp.movecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
 
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

    // get all rated movie IDs
    UserRating userRatings = this.restTemplate.getForObject("http://move-rating-service/ratingsdata/users/"+userId, UserRating.class);

    // for each movie ID, call movie info service to get details, then build a CatalogItem
    return userRatings.getUserRating().stream().map(rating -> {
      Movie movie = this.restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
      return new CatalogItem( movie.getName(), "Test", rating.getRating());
    })

    // put them all together
    .collect(Collectors.toList());
  }

  // async alternative to RestTemplate; not worth getting into for this demo
  // private Mono<Movie> getMovieInfo(String uri) {
  //   return webClientBuilder.build()
  //     .get()
  //     .uri(uri)
  //     .retrieve()
  //     .bodyToMono(Movie.class);
  // }
}
