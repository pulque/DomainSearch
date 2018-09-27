package com.lizheblogs.domainsearch.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.lizheblogs.domainsearch.R;
import com.lizheblogs.domainsearch.data.DomainCheckRepository;
import com.lizheblogs.domainsearch.data.DomainInfoRepository;
import com.lizheblogs.domainsearch.main.info.InfoFragment;
import com.lizheblogs.domainsearch.main.info.InfoPresenter;
import com.lizheblogs.domainsearch.main.multiple.SearchMultFragment;
import com.lizheblogs.domainsearch.main.multiple.SearchMultPresenter;
import com.lizheblogs.domainsearch.main.once.SearchFragment;
import com.lizheblogs.domainsearch.main.once.SearchPresenter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_once:
                    updateFragment(searchFragment);
                    return true;
                case R.id.navigation_multiple:
                    updateFragment(searchMultFragment);
                    return true;
                case R.id.navigation_info:
                    updateFragment(infoFragment);
                    return true;
            }
            return false;
        }

    };

    private SearchFragment searchFragment;
    private SearchMultFragment searchMultFragment;
    private InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-8468077059975424~3013646857");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchFragment = new SearchFragment();
        searchMultFragment = new SearchMultFragment();
        infoFragment = new InfoFragment();
        new SearchPresenter(DomainCheckRepository.getInstance(), searchFragment);
        new SearchMultPresenter(DomainCheckRepository.getInstance(), searchMultFragment);
        new InfoPresenter(DomainInfoRepository.getInstance(), infoFragment);
        navigation.setSelectedItemId(R.id.navigation_once);

    }

    private void updateFragment(Fragment fragment) {
        FragmentManager frmanager = getSupportFragmentManager();
        FragmentTransaction frtransaction = frmanager.beginTransaction();
        frtransaction.replace(R.id.content, fragment);
        frtransaction.commit();
    }


}
