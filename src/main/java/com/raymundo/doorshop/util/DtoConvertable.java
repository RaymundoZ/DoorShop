package com.raymundo.doorshop.util;

import com.raymundo.doorshop.dto.BaseDto;

public interface DtoConvertable<T extends BaseDto> {

    T toDto();
}