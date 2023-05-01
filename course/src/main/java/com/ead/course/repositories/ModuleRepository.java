package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>,
    JpaSpecificationExecutor<ModuleModel> {

//  @EntityGraph(attributePaths = {"course"})  //simula LAZY, EAGER
//  ModuleModel findByTitle(String title);


  @Query(value = "SELECT * FROM TB_MODULES WHERE COURSE_COURSE_ID= :courseId", nativeQuery = true)
  List<ModuleModel> findAllModulesIntoCourses(@Param("courseId") UUID courseId);


  @Query(value = "SELECT * FROM TB_MODULES WHERE COURSE_COURSE_ID= :courseId AND MODULE_ID= :moduleId", nativeQuery = true)
  Optional<ModuleModel> findModuleIntoCourse(@Param("courseId") UUID courseId,
      @Param("moduleId") UUID moduleId);

}
