package com.vti.Gencraft.Repository;
import com.vti.Gencraft.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
    List<ImageEntity> findAll();
    @Query(value = "select * from images where user_id = :id",nativeQuery = true)
    List<ImageEntity> findImageByUserId(@Param("id") int id);
}
