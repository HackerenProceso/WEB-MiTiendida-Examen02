package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Categoria;

public interface ICategoria extends JpaRepository<Categoria, Long> {

}
