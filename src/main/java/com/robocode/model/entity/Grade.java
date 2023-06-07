package com.robocode.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {

    private long id;
    private Long mark;
    private Student student;
    private Course course;
}
