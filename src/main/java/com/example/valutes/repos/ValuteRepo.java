package com.example.valutes.repos;

import com.example.valutes.entities.Valute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuteRepo extends JpaRepository<Valute , Long> {

}
