package com.qxy.mzbzy.ui.binding_adapter;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * Create by KunMinX at 19/9/18
 */
public class CommonBindingAdapter {

    @BindingAdapter(value = {"imageUrl", "placeHolder"}, requireAll = false)
    public static void imageUrl(ImageView view, String url, Drawable placeHolder) {
        Glide.with(view.getContext()).load(url).placeholder(placeHolder).into(view);
    }

    @BindingAdapter(value = {"visible"}, requireAll = false)
    public static void visible(View view, boolean visible) {
        if (visible && view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        } else if (!visible && view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter(value = {"invisible"}, requireAll = false)
    public static void invisible(View view, boolean visible) {
        if (visible && view.getVisibility() == View.INVISIBLE) {
            view.setVisibility(View.VISIBLE);
        } else if (!visible && view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @BindingAdapter(value = {"size"}, requireAll = false)
    public static void size(View view, Pair<Integer, Integer> size) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        params.width = size.first;
        params.height = size.second;
        view.setLayoutParams(params);
    }

    @BindingAdapter(value = {"transX"}, requireAll = false)
    public static void translationX(View view, float translationX) {
        view.setTranslationX(translationX);
    }

    @BindingAdapter(value = {"transY"}, requireAll = false)
    public static void translationY(View view, float translationY) {
        view.setTranslationY(translationY);
    }

    @BindingAdapter(value = {"x"}, requireAll = false)
    public static void x(View view, float x) {
        view.setX(x);
    }

    @BindingAdapter(value = {"y"}, requireAll = false)
    public static void y(View view, float y) {
        view.setY(y);
    }

    @BindingAdapter(value = {"alpha"}, requireAll = false)
    public static void alpha(View view, float alpha) {
        view.setAlpha(alpha);
    }

    @BindingAdapter(value = {"textColor"}, requireAll = false)
    public static void setTextColor(TextView textView, int textColorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextColor(textView.getContext().getColor(textColorRes));
        }
    }

    @BindingAdapter(value = {"selected"}, requireAll = false)
    public static void selected(View view, boolean select) {
        view.setSelected(select);
    }

}
