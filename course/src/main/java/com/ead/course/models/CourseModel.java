package com.ead.course.models;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID courseId;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(nullable = false, length = 250)
  private String description;

  @Column
  private String imageUrl;

  //  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  @Column(nullable = false)
  private LocalDateTime creationDate;

  //  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  @Column(nullable = false)
  private LocalDateTime lastUpdateDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CourseStatus courseStatus;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CourseLevel courseLevel;

  @Column(nullable = false)
  private UUID userInstructor;

  @JsonProperty(access = Access.WRITE_ONLY)
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  @Fetch(FetchMode.SUBSELECT)
  private Set<ModuleModel> modules;

}
