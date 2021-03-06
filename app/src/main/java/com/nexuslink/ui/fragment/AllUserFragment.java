package com.nexuslink.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexuslink.R;
import com.nexuslink.app.BaseFragment;
import com.nexuslink.config.Constants;
import com.nexuslink.model.data.SearchInfo;
import com.nexuslink.model.friendmodel.OnSearchListener;
import com.nexuslink.ui.activity.FriendActivity;
import com.nexuslink.ui.adapter.FriendRecyclerViewAdapter;
import com.nexuslink.ui.view.AllUserView;
import com.nexuslink.util.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-NB on 2017/1/14.
 */

public class AllUserFragment extends BaseFragment implements AllUserView,OnSearchListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.nothing)
    TextView nothing;
    private FriendRecyclerViewAdapter adapter;
    SearchInfo searchInfo;

    public static AllUserFragment getInstance(SearchInfo searchInfo) {
        AllUserFragment fragment = new AllUserFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alluser, container, false);
        ButterKnife.bind(this, view);
        FriendActivity.setOnSearchListener(this);
        adapter = new FriendRecyclerViewAdapter(getActivity(), searchInfo);
        recyclerview.setAdapter(adapter);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void showFollow() {

    }

    @Override
    public void showUserInfo() {

    }

    @Override
    public void onSearched() {
        nothing.setVisibility(View.INVISIBLE);
    }

}
