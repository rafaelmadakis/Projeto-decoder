package com.ead.authuser.clients;

import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.ResponsePageDto;
import com.ead.authuser.services.UtilsService;
import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
public class CourseClient {


  private RestTemplate restTemplate;


  public CourseClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Autowired
  private UtilsService utilsService;

  @Value("${ead.api.url.course}")
  String REQUEST_URL_COURSE;

  public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
    List<CourseDto> searchResult = null;

    String url = REQUEST_URL_COURSE + utilsService.createUrl(userId, pageable);
    log.debug("Request URL: {} ", url);
    log.info("Request URL:  {} ", url);
    ResponseEntity<ResponsePageDto<CourseDto>> result = null;
    try {
      ParameterizedTypeReference<ResponsePageDto<CourseDto>> responseType =
          new ParameterizedTypeReference<ResponsePageDto<CourseDto>>() {
          };
      result = restTemplate.exchange(url, HttpMethod.GET,
          null, responseType);
      searchResult = result.getBody().getContent();

      log.debug("Response Number Of Elements: {} ", searchResult.size());
    } catch (HttpStatusCodeException e) {
      log.error("Error request /courses {} ", e);
    }
    log.info("Ending request /courses userId {} ", userId);
    return result.getBody();
  }
}
