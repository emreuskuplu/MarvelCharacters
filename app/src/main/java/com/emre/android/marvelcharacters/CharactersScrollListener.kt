package com.emre.android.marvelcharacters

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class CharactersScrollListener(
    private val viewModel: MainViewModel,
) : RecyclerView.OnScrollListener() {

    private val updateDataSubject = PublishSubject.create<Unit>()
    val updateDataObservable: Observable<Unit> = updateDataSubject.hide()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = (recyclerView.layoutManager as GridLayoutManager)
        if (layoutManager.findLastVisibleItemPosition() >= viewModel.updateDataPosition && viewModel.isCurrentlyUpdate.not()) {
            updateDataSubject.onNext(Unit)
        }
    }
}