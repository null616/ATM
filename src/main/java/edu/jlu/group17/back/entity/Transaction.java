package edu.jlu.group17.back.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private static final long serialVersionUID=1L;
    private Long id;
    private LocalDateTime createTime;
    private Long clientId;
    private Integer change;
}
