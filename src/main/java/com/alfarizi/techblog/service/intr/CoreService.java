package com.alfarizi.techblog.service.intr;

import java.util.List;

public interface CoreService<T, U> {

    T create (U dto);

    T getById(String id);

    List<T> getAll();

    T update (U dto, String id);

    void delete(String id);
}
