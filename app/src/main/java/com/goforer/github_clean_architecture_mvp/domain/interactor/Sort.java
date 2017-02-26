package com.goforer.github_clean_architecture_mvp.domain.interactor;

import java.util.Comparator;
import java.util.List;

public interface Sort<T> {
    List<T> sort(List<T> items, Comparator<T> comparator);
}
