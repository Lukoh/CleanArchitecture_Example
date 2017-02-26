package com.goforer.github_clean_architecture_mvp.domain.sort;


import com.goforer.github_clean_architecture_mvp.domain.interactor.Sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortImpl<T> implements Sort<T> {
    @Override
    public List<T> sort(List<T> items, Comparator<T> comparator) {
        if (items != null && !items.isEmpty()) {
            Collections.sort(items, comparator);
        }

        return items;
    }
}
