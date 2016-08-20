package com.android.jwang.demo.anims;

import android.view.View;

public interface SpringAnimationListener
{
    public static SpringAnimationListener NO_OP = new SpringAnimationListener()
    {
        @Override
        public void onAnimationCompleted(View view)
        {

        }
    };

    public void onAnimationCompleted(View view);
}
