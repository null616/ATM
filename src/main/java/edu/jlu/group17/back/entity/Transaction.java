package edu.jlu.group17.back.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 10186
 */
@Data
public class Transaction {
    private static final long serialVersionUID=1L;
    private Long id;
    private LocalDateTime create_time;
    private Long client_id;
    private Double money_change;
    private String explanation;
}
