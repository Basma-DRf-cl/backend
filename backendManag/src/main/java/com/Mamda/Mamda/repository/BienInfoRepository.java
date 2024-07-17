package com.Mamda.Mamda.repository;

import com.Mamda.Mamda.model.BienInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BienInfoRepository extends JpaRepository<BienInfo, Integer> {
    // You can add custom query methods here if needed
}
