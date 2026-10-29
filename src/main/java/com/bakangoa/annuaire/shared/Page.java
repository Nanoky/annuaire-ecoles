package com.bakangoa.annuaire.shared;

import java.util.List;

public record Page<T>(
        List<T> items,
        int total,
        int page,
        int size
) {

    public static Page of(List items, int total, int page, int size) {
        return new Page(items, total, page, size);
    }
}
