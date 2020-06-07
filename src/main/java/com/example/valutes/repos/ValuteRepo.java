package com.example.valutes.repos;

import com.example.valutes.entities.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ValuteRepo extends JpaRepository<Valute, Long> {

    @Query("select distinct date from Valute order by date asc ")
    List<Date> getSortedDate();

    @Query("select value from Valute where charCode = ?1 order by  date asc ")
    List<Double> getConcreteValue(String charCode);

    @Query("select nominal from Valute where charCode = ?1 order by  date asc")
    List<Integer> getConcreteNominal(String charCode);

}
