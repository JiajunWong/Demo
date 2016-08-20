package com.android.jwang.demo.activities;

import com.android.jwang.demo.R;
import com.android.jwang.demo.anims.morph.MorphButtonToDialog;
import com.android.jwang.demo.anims.morph.MorphDialogToButton;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.transition.ArcMotion;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/**
 * Created by Jiajun Wang on 8/19/16.
 * Copyright (c) 2015 Tank Exchange, Inc. All rights reserved.
 */
public class DetailsActivity extends BaseActivity
        implements View.OnClickListener
{
    private View mRootView;
    private View mContainer;

    public static void startActivity(Context context, View view)
    {
        Intent intent = new Intent(context, DetailsActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, context.getString(R.string.transition_morph_view));
        context.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mRootView = findViewById(R.id.root_view);
        mRootView.setOnClickListener(this);
        mContainer = findViewById(R.id.container);

        setupSharedElementTransitionsButton(this, mContainer);
    }

    private void setupSharedElementTransitionsButton(@NonNull Activity activity, @Nullable View target)
    {
        ArcMotion arcMotion = new ArcMotion();
        int color = ContextCompat.getColor(activity, R.color.color_blue);
        Interpolator easeInOut = AnimationUtils.loadInterpolator(activity, android.R.interpolator.fast_out_slow_in);
        MorphButtonToDialog sharedEnter = new MorphButtonToDialog(color);
        sharedEnter.setPathMotion(arcMotion);
        sharedEnter.setInterpolator(easeInOut);
        MorphDialogToButton sharedReturn = new MorphDialogToButton(color);
        sharedReturn.setPathMotion(arcMotion);
        sharedReturn.setInterpolator(easeInOut);
        if (target != null)
        {
            sharedEnter.addTarget(target);
            sharedReturn.addTarget(target);
        }
        activity.getWindow().setSharedElementEnterTransition(sharedEnter);
        activity.getWindow().setSharedElementReturnTransition(sharedReturn);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.root_view:
                dismiss();
        }
    }

    private void dismiss()
    {
        setResult(Activity.RESULT_CANCELED);
        finishAfterTransition();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        dismiss();
    }
}
