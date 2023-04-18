package com.raymundo.doorshop.util;

import com.raymundo.doorshop.entity.BaseEntity;

public interface EntityConvertable<T extends BaseEntity> {

    T toEntity();

}
