package com.mytransformation.cetes.models;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.springframework.data.mongodb.core.mapping.*;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Document(collection = "daily_earnings")
public class DailyEarnings {

    @MongoId(FieldType.OBJECT_ID)
    String id;

    @Field("user_id")
    String userId;

    @Field("moment")
    LocalDateTime moment;

    @Field("earnings")
    int earnings = 0;

    @Field("created_at")
    LocalDateTime createdAt = LocalDateTime.now();

    @Field("updated_at")
    LocalDateTime updatedAt = LocalDateTime.now();
    
}
