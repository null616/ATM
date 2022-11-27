package edu.jlu.group17.back.entity;

import lombok.Data;

@Data
public class Client {
    private static final long serialVersionUID=1L;
    private Long id;
    private String name;
    private String card_number;
    private String password;
    private Integer balance;
}
