package com.example.philosophy.models.data;

import com.example.philosophy.models.Wisdom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WisdomDao extends CrudRepository<Wisdom, Long> {

    @Override
    <S extends Wisdom> S save(S entity);
}
