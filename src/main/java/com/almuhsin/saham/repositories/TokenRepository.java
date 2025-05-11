package com.almuhsin.saham.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.almuhsin.saham.entities.MToken;

@Repository
public interface TokenRepository extends JpaRepository<MToken, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by token value or user ID
//     @Query(value = "SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
//             "FROM MToken t " +
//             "WHERE t.token = :token AND t.isExpired = false AND t.tokenFor = :usedFor", nativeQuery = true)
//    void isTokenExists(@Param("email") String email, @Param("usedFor") String usedFor);
     @Query("SELECT o FROM MToken o " +
       "WHERE o.user.id = :userId " +
       "AND o.tokenFor = :tokenFor " +
       "AND o.token = :token " +
       "AND o.isUsed = false " +
       "AND o.expiredAt > CURRENT_TIMESTAMP")
Optional<MToken> findValidOtp(@Param("userId") Integer userId,
                              @Param("tokenFor") String tokenFor,
                              @Param("token") String token);


    @Query("SELECT o FROM MToken o " +
           "WHERE o.user.id = :userId " +
           "AND o.tokenFor = :tokenFor " +
           "AND o.expiredAt > CURRENT_TIMESTAMP")
    Optional<MToken> findValidOtpRegister(@Param("userId") Integer userId, @Param("tokenFor") String tokenFor);

    Optional<MToken> findByUserIdAndTokenForAndIsUsedFalseAndExpiredAtAfter(
        Integer userId, String tokenFor, LocalDateTime now);
    
}