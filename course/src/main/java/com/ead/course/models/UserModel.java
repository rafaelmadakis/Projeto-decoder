package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private UUID userid;


}
