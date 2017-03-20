package com.goforer.github_clean_architecture_mvp.domain.sort;

import java.util.Comparator;
import java.util.List;

public interface Sort<T> {
    void sort(List<T> items, Comparator<T> comparator);
}
