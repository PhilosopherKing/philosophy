package com.example.philosophy.models.data;

import com.example.philosophy.models.Wisdom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WisdomDao extends CrudRepository<Wisdom, Integer> {

    void save(MultipartFile file);

}
