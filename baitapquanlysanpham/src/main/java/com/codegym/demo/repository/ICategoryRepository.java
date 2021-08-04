package com.codegym.demo.repository;

import com.codegym.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.smartcardio.CardTerminal;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
