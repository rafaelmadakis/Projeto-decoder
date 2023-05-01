package com.ead.course.repositories;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LessonRepository extends JpaRepository<LessonModel, UUID>,
    JpaSpecificationExecutor<LessonModel> {

  @Query(value = "SELECT * FROM TB_LESSONS WHERE MODULE_MODULE_ID= :moduleId", nativeQuery = true)
  List<LessonModel> findAllLessonsIntoModule(@Param("moduleId") UUID moduleId);

  @Query(value = "SELECT * FROM TB_LESSONS WHERE MODULE_MODULE_ID= :moduleId AND LESSON_ID= :lessonId", nativeQuery = true)
  Optional<LessonModel> findLessonIntoModule(@Param("moduleId") UUID moduleId, @Param("lessonId") UUID lessonId );

}
