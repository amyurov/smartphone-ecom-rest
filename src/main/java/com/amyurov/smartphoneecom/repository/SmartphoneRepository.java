package com.amyurov.smartphoneecom.repository;

import com.amyurov.smartphoneecom.entity.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {

}
