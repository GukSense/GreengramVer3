package com.green.greengram.email.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EmailMessage {
    private String to;
    private String from;
    private String subject;

}
