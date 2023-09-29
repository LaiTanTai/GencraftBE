package com.vti.Gencraft.Repository;

import com.vti.Gencraft.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
//    Khi để nativeQuery là true thì lúc này mã JPQL được định nghĩa thành SQL và truyền tham số tương tự như dạng HQL
//    gọi được các procedure function ...
    @Query(value = "SELECT * FROM users where users.username = :username",nativeQuery = true)
//    Tham số được truyền thông qua một annotation là @Param
    List<UserEntity> findAllUsername(@Param("username") String username);

    UserEntity findByUsername(@Param("username") String username);
    UserEntity findById(int id);
}