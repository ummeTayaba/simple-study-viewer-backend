package com.tayaba.simplestudyviewer.web.api.repositories;

import com.tayaba.simplestudyviewer.models.Study;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends CrudRepository<Study, Long> {
    List<Study> findAllByOrderByStudyCreationTimeDescStudyUpdateTimeDesc();
}
