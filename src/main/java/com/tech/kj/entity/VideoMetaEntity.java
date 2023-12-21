package com.tech.kj.entity;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class VideoMetaEntity {
    @Id
    private String id;
    private long size;
    private String httpContentType;
}
