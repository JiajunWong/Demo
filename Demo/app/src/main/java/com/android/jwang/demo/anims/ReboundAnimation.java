package com.android.jwang.demo.anims;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import android.view.View;

public class ReboundAnimation
{
    public static Spring startSlideAnimation(final View view, final int startTranslationX, final int finalTranslationX, final SpringAnimationListener listener)
    {
        return startAnimation(view, startTranslationX, (int) view.getTranslationY(), finalTranslationX, (int) view.getTranslationY(), listener);
    }

    public static Spring startDropAnimation(final View view, final int startTranslationY, final int finalTranslationY, final SpringAnimationListener listener)
    {
        return startAnimation(view, (int) view.getTranslationX(), startTranslationY, (int) view.getTranslationX(), finalTranslationY, listener);
    }

    public static Spring startAnimation(final View view, final int startTranslationX, final int startTranslationY, final int finalTranslationX, final int finalTranslationY, final SpringAnimationListener listener)
    {
        view.setTranslationX(startTranslationX);
        view.setTranslationY(startTranslationY);
        view.setVisibility(View.VISIBLE);

        // Create a system to run the physics loop for a set of springs.
        SpringSystem springSystem = SpringSystem.create();

        // Add a spring to the system.
        Spring spring = springSystem.createSpring();
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(80, 7));

        // Add a listener to observe the motion of the spring.
        spring.addListener(new SimpleSpringListener()
        {
            @Override
            public void onSpringUpdate(Spring spring)
            {
                // You can observe the updates in the spring
                // state by asking its current value in onSpringUpdate.
                float value = (float) spring.getCurrentValue();
                float newX = startTranslationX - (startTranslationX - finalTranslationX) * (value);
                float newY = startTranslationY - (startTranslationY - finalTranslationY) * (value);
                view.setTranslationX(newX);
                view.setTranslationY(newY);
            }

            @Override
            public void onSpringAtRest(Spring spring)
            {
                if (listener != null)
                {
                    listener.onAnimationCompleted(view);
                }
            }
        });

        // Set the spring in motion; moving from 0 to 1
        spring.setEndValue(1);
        return spring;
    }

    //    public static Spring startCurveAnimation(final View view, final int startTranslationX, final int startTranslationY, final int finalTranslationX, final int finalTranslationY, final double size, boolean overshoot, final SpringAnimationListener listener)
    //    {
    //        view.setTranslationX(startTranslationX);
    //        view.setTranslationY(startTranslationY);
    //        view.setVisibility(View.VISIBLE);
    //
    //        // Create a system to run the physics loop for a set of springs.
    //        SpringSystem springSystem = SpringSystem.create();
    //
    //        // Add a spring to the system.
    //        Spring spring = springSystem.createSpring();
    //        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(80, 7));
    //        spring.setOvershootClampingEnabled(overshoot);
    //
    //        // Add a listener to observe the motion of the spring.
    //        spring.addListener(new SimpleSpringListener()
    //        {
    //            @Override
    //            public void onSpringUpdate(Spring spring)
    //            {
    //                // You can observe the updates in the spring
    //                // state by asking its current value in onSpringUpdate.
    //                double value = spring.getCurrentValue();
    //                double newX = startTranslationX - (startTranslationX - finalTranslationX) * (value);
    //                double newY;
    //                if (finalTranslationY <= 0)
    //                {
    //                    newY = -MathUtil.getCurveY(newX, size);
    //                }
    //                else
    //                {
    //                    newY = MathUtil.getCurveY(newX, size);
    //                }
    //                view.setTranslationX((int) newX);
    //                view.setTranslationY((int) newY);
    //            }
    //
    //            @Override
    //            public void onSpringAtRest(Spring spring)
    //            {
    //                if (listener != null)
    //                {
    //                    listener.onAnimationCompleted(view);
    //                }
    //            }
    //        });
    //
    //        // Set the spring in motion; moving from 0 to 1
    //        spring.setEndValue(1);
    //        return spring;
    //    }

    public static Spring startScaleAnimation(final View view, final float startScale, final float finalScale, final SpringAnimationListener listener)
    {
        if (view == null)
        {
            return null;

        }
        view.setScaleX(startScale);
        view.setScaleY(startScale);
        view.setVisibility(View.VISIBLE);

        // Create a system to run the physics loop for a set of springs.
        SpringSystem springSystem = SpringSystem.create();

        // Add a spring to the system.
        Spring spring = springSystem.createSpring();

        // Add a listener to observe the motion of the spring.
        spring.addListener(new SimpleSpringListener()
        {
            @Override
            public void onSpringUpdate(final Spring spring)
            {
                // You can observe the updates in the spring
                // state by asking its current value in onSpringUpdate.
                float value = (float) spring.getCurrentValue();
                float mappedValue;
                float highValue = finalScale - startScale;
                float highValueMax = finalScale;
                if (value < 1)
                {
                    mappedValue = startScale + highValue * value;
                }
                else
                {
                    value -= 1;
                    mappedValue = highValueMax - highValue * value;
                }
                view.setScaleX(mappedValue);
                view.setScaleY(mappedValue);
            }

            @Override
            public void onSpringAtRest(final Spring spring)
            {
                if (listener != null)
                {
                    listener.onAnimationCompleted(view);
                }
            }
        });

        // Set the spring in motion; moving from 0 to 1
        spring.setEndValue(2);
        return spring;
    }

    public static Spring startBounceAnimation(final View view)
    {
        return ReboundAnimation.startScaleAnimation(view, 1, 1.2f, null);
    }

}
