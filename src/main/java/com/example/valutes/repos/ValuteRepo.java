package com.example.valutes.repos;

import com.example.valutes.entities.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ValuteRepo extends JpaRepository<Valute, Long> {
    List<Valute> findByNumCodeOrderByDateAsc(int numCode);

}
