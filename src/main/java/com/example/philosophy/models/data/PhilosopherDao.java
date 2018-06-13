package com.example.philosophy.models.data;

import com.example.philosophy.models.Philosopher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PhilosopherDao extends CrudRepository<Philosopher, Integer> {
}
