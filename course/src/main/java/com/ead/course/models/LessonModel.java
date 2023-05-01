package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;


@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "TB_LESSONS")
public class LessonModel  implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID lessonId;

  @Column(nullable = false, length = 150)
  private String title;

  @Column(nullable = false, length = 250)
  private String description;

  @Column(nullable = false)
  private String videoUrl;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  @Column(nullable = false)
  private LocalDateTime creationDate;

  @JsonProperty(access = Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private ModuleModel module;

  public UUID getLessonId() {
    return lessonId;
  }

  public void setLessonId(UUID lessonId) {
    this.lessonId = lessonId;
  }

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

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public ModuleModel getModule() {
    return module;
  }

  public void setModule(ModuleModel module) {
    this.module = module;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LessonModel that = (LessonModel) o;
    return lessonId.equals(that.lessonId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lessonId);
  }

  @Override
  public String toString() {
    return "LessonModel{" +
        "lessonId=" + lessonId +
        '}';
  }
}
