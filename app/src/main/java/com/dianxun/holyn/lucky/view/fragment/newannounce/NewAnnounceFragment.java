package com.dianxun.holyn.lucky.view.fragment.newannounce;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;
import com.dianxun.holyn.lucky.model.parcelable.NewAnnouncePar;
import com.dianxun.holyn.lucky.presenter.newannounce.NewAnnouncePresenter;
import com.dianxun.holyn.lucky.view.activity.BaseToolBarActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseXRecyclerViewFragment;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/27.
 */
public class NewAnnounceFragment extends BaseXRecyclerViewFragment implements NewAnnouncePresenter.UniqueViewInterface {

    @Inject
    NewAnnouncePresenter newAnnouncePresenter;

    private NewAnnounceRvAdapter recyclerViewAdapter;

    public static NewAnnounceFragment newInstance() {
        return new NewAnnounceFragment();
    }

    public NewAnnounceFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        begainLoadNewAnnounce();
        setLoadingListenner(true, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseToolBarActivity) getActivity()).setToolBarTitle("奖品详情");
        newAnnouncePresenter.resume();
    }

    @Override
    protected void initPresenter() {
        newAnnouncePresenter.setBaseViewInferface(this);
        newAnnouncePresenter.setUniqueViewInterface(this);
        newAnnouncePresenter.initialize();
    }

    @Override
    protected void initXRecyclerViewAdapter() {
        recyclerViewAdapter = new NewAnnounceRvAdapter(getXrecyclerview());

//        recyclerViewAdapter.setOnRVItemClickListener(this);
//        recyclerViewAdapter.setOnItemChildClickListener(this);

        getXrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));

        getXrecyclerview().setAdapter(recyclerViewAdapter);
        getXrecyclerview().addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

    private void begainLoadNewAnnounce() {
        newAnnouncePresenter.getNewAnnounce();
    }

    @Override
    public void successGetNewAnnouncePar(NewAnnouncePar newAnnouncePar) {
        List<NewAnnouncePar> newAnnounceParList = new ArrayList<NewAnnouncePar>();
        for (int i = 0; i < 10; i++) {
            NewAnnouncePar announcePar = new NewAnnouncePar();
            announcePar.setTerm(String.valueOf(i));
            announcePar.setName("holyn_" + i);
            newAnnounceParList.add(announcePar);
        }
        if(recyclerViewAdapter!=null && getXrecyclerview()!=null){
            recyclerViewAdapter.addNewDatas(newAnnounceParList);
            getXrecyclerview().smoothScrollToPosition(0);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                List<NewAnnouncePar> newAnnounceParList = new ArrayList<NewAnnouncePar>();
                for (int i = 0; i < 10; i++) {
                    NewAnnouncePar announcePar = new NewAnnouncePar();
                    announcePar.setTerm(String.valueOf(i));
                    announcePar.setName("holyn_" + (recyclerViewAdapter.getItemCount()+i));
                    newAnnounceParList.add(announcePar);
                }
                recyclerViewAdapter.addMoreDatas(newAnnounceParList);
                recyclerViewAdapter.notifyDataSetChanged();
                getXrecyclerview().loadMoreComplete();
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                recyclerViewAdapter.clear();
                List<NewAnnouncePar> newAnnounceParList = new ArrayList<NewAnnouncePar>();
                for (int i = 0; i < 10; i++) {
                    NewAnnouncePar announcePar = new NewAnnouncePar();
                    announcePar.setTerm(String.valueOf(i));
                    announcePar.setName("holyn_" + i);
                    newAnnounceParList.add(announcePar);
                }
                recyclerViewAdapter.addNewDatas(newAnnounceParList);
                getXrecyclerview().smoothScrollToPosition(0);
                recyclerViewAdapter.notifyDataSetChanged();
                getXrecyclerview().refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        newAnnouncePresenter.pause();
    }
}
