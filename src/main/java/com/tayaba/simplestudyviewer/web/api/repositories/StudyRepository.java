package com.tayaba.simplestudyviewer.web.api.repositories;

import com.tayaba.simplestudyviewer.models.Study;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends CrudRepository<Study, Long> {

}
