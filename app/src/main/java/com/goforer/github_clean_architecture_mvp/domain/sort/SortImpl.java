package com.goforer.github_clean_architecture_mvp.domain.sort;

import com.goforer.github_clean_architecture_mvp.annotation.ForUnitTest;
import com.goforer.github_clean_architecture_mvp.presentation.presenter.RepositoryPresenter;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class SortImpl<T> implements Sort<T> {
    private RepositoryPresenter mRepositoryPresenter;

    private View mView;

    @Inject
    public SortImpl() {

    }

    @Override
    public void sort(List<T> items, Comparator<T> comparator) {
        if (items != null && !items.isEmpty()) {
            Collections.sort(items, comparator);
        }

        mRepositoryPresenter.onSorted(mView, items);
    }

    public void setSort(RepositoryPresenter presenter, View view) {
        mRepositoryPresenter = presenter;
        mView = view;
    }

    @ForUnitTest
    public List<T> testSort(List<T> items, Comparator<T> comparator) {
        if (items != null && !items.isEmpty()) {
            Collections.sort(items, comparator);
        }

        return items;
    }
}
