package com.befiring.xbefiring.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befiring.xbefiring.adapter.MyRecyclerAdapter;
import com.befiring.xbefiring.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);


        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class FirstFragment extends Fragment {
        private static final String TAB_POSITION = "tab_position";
        public FirstFragment(){

        }

        public static FirstFragment newInstance(int tabPosition){
            FirstFragment fragment=new FirstFragment();
            Bundle args=new Bundle();
            args.putInt(TAB_POSITION,tabPosition);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            Bundle args=getArguments();
            int tabPosition=args.getInt(TAB_POSITION);

            ArrayList<String> items=new ArrayList<>();
            for(int i=0;i<50;i++){
                items.add("Tab #"+tabPosition+" item #"+i);
            }
            View v=inflater.inflate(R.layout.fragment_list_view,container,false);
            RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(new MyRecyclerAdapter(items));
            return v;
        }
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FirstFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "新闻"+position;
        }
    }
}
