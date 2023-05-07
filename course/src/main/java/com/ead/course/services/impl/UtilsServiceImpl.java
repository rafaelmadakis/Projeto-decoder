package com.ead.course.services.impl;

import com.ead.course.services.UtilsService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements UtilsService {

//  @Value("${ead.api.url.authuser}")
//  String REQUEST_URI;

  public String createUrlGetAllUsersByCourse(UUID courseId, Pageable pageable) {
    return "/users?courseId=" + courseId + "&page=" + pageable.getPageNumber()
        + "&size="
        + pageable.getPageSize() + "&sort=" + pageable.getSort().toString()
        .replaceAll(": ", ",");
  }

}
