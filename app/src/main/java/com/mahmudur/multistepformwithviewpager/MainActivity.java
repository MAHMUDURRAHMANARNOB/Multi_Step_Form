package com.mahmudur.multistepformwithviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Button nextButton;
    private Button previousButton;
    private int currentStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ClientFragment());
        fragments.add(new CourtFragment());
        fragments.add(new LawyerFragment());
        fragments.add(new JudgementFragment());
        fragments.add(new PaymentFragment());

        final FormPagerAdapter pagerAdapter = new FormPagerAdapter(this, fragments);
        viewPager.setAdapter(pagerAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep < fragments.size() - 1) {
                    currentStep++;
                    viewPager.setCurrentItem(currentStep);
                    updateButtonVisibility();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    currentStep--;
                    viewPager.setCurrentItem(currentStep);
                    updateButtonVisibility();
                }
            }
        });

        // Initialize button visibility based on the current step
        updateButtonVisibility();

        viewPager.setUserInputEnabled(false);


    }

    // Update button visibility based on the current step
    private void updateButtonVisibility() {
        if (currentStep == 0) {
            previousButton.setVisibility(View.INVISIBLE);
        } else {
            previousButton.setVisibility(View.VISIBLE);
        }

        if (currentStep == viewPager.getAdapter().getItemCount() - 1) {
            nextButton.setText("Submit"); // Change the text of the "Next" button on the last step
        } else {
            nextButton.setText("Next");
        }
    }

    public void onBackPressed(){
        if (currentStep > 0) {
            currentStep--;
            viewPager.setCurrentItem(currentStep);
            updateButtonVisibility();
        }
        else{
            finish();
        }
    }
}