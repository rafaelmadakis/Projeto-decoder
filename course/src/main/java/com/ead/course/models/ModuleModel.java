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
@Table(name = "TB_MODULES")
public class ModuleModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID moduleId;

  @Column(nullable = false, length = 150)
  private String title;

//  @Column(nullable = false, length = 250)
  private String description;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  @Column(nullable = false)
  private LocalDateTime creationDate;

  @JsonProperty(access = Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private CourseModel course;

  @JsonProperty(access = Access.WRITE_ONLY)
  @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
  @Fetch(FetchMode.SUBSELECT)
  private Set<LessonModel> lessons;

}
