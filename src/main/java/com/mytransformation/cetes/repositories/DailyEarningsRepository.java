package com.mytransformation.cetes.repositories;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import com.mytransformation.cetes.models.DailyEarnings;

@Repository
public interface DailyEarningsRepository extends MongoRepository<DailyEarnings, String> {
    @Query("{ user_id: ?0, moment: { $gte: ?1, $lte: ?2 } }")
    List<DailyEarnings> findMeByInterval(String userId, LocalDateTime from, LocalDateTime to);

    @Query("{ user_id: ?0, _id: ?1 }")
    Optional<DailyEarnings> findMe(String userId, String id);
}
