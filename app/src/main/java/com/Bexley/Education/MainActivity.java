package com.Bexley.Education;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.Bexley.Education.Fragment.facebookFragment;
import com.Bexley.Education.Fragment.gmailFragment;
import com.Bexley.Education.Fragment.anonymouslyFragment;
import com.Bexley.Education.Fragment.mailFragment;
import com.Bexley.Education.Fragment.phoneFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FirebaseAuth auth;
    private int[] tabIcons = {
            R.drawable.mail,
            R.drawable.search,
            R.drawable.facebook,
            R.drawable.phone,
            R.drawable.incognito,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user!=null)
        {
            //value=true;
            Intent in=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(in);
            finish();
        }
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        getSupportActionBar().hide();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdepter adapter = new PagerAdepter(getSupportFragmentManager());
        adapter.addFragment(new mailFragment(),"mail");
        adapter.addFragment(new gmailFragment(),"google");
        adapter.addFragment(new facebookFragment(),"Facebook");
        adapter.addFragment(new phoneFragment(),"Phone");
        adapter.addFragment(new anonymouslyFragment(),"Incognito");

        viewPager.setAdapter(adapter);
    }
}
