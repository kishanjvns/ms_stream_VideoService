package com.tech.kj.entity;
import jakarta.persistence.*;
import lombok.*;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class VideoMetaEntity extends BaseEntity {
    private long size;
    private String httpContentType;
    private String userId;
    {

    }
}
