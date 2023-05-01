package com.ead.course.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import lombok.Data;


public class LessonDto {

  @NotBlank
  private String title;

  private String description;

  @NotBlank
  private String videoUrl;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LessonDto lessonDto = (LessonDto) o;
    return Objects.equals(title, lessonDto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title);
  }

  @Override
  public String toString() {
    return "LessonDto{" +
        "title='" + title + '\'' +
        '}';
  }
}
