package com.raymundo.doorshop.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raymundo.doorshop.dto.BaseDto;

import java.util.Date;
import java.util.List;

public record ErrorResponse(
        List<String> messages,

        @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Europe/Moscow")
        Date timestamp

) implements BaseDto {

}