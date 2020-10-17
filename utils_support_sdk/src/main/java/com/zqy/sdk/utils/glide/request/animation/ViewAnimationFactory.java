package com.zqy.sdk.utils.glide.request.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * A {@linkcom.zqy.sutils.glide.request.animation.GlideAnimationFactory} that produces
 * {@linkcom.zqy.sutils.glide.request.animation.ViewAnimation}s.
 *
 * @param <R> The type of the resource displayed in the view that is animated
 */
public class ViewAnimationFactory<R> implements GlideAnimationFactory<R> {
    private final ViewAnimation.AnimationFactory animationFactory;
    private GlideAnimation<R> glideAnimation;

    public ViewAnimationFactory(Animation animation) {
        this(new ConcreteAnimationFactory(animation));
    }

    public ViewAnimationFactory(Context context, int animationId) {
        this(new ResourceAnimationFactory(context, animationId));
    }

    ViewAnimationFactory(ViewAnimation.AnimationFactory animationFactory) {
        this.animationFactory = animationFactory;
    }

    /**
     * Returns a new {@linkcom.zqy.sutils.glide.request.animation.GlideAnimation} for the given arguments. If
     * isFromMemoryCache is {@code true} or isFirstImage is {@code false}, returns a
     * {@linkcom.zqy.sutils.glide.request.animation.NoAnimation} and otherwise returns a new
     * {@linkcom.zqy.sutils.glide.request.animation.ViewAnimation}.
     *
     * @param isFromMemoryCache {@inheritDoc}
     * @param isFirstResource   {@inheritDoc}
     */
    @Override
    public GlideAnimation<R> build(boolean isFromMemoryCache, boolean isFirstResource) {
        if (isFromMemoryCache || !isFirstResource) {
            return NoAnimation.get();
        }

        if (glideAnimation == null) {
            glideAnimation = new ViewAnimation<R>(animationFactory);
        }

        return glideAnimation;
    }

    private static class ConcreteAnimationFactory implements ViewAnimation.AnimationFactory {
        private final Animation animation;

        public ConcreteAnimationFactory(Animation animation) {
            this.animation = animation;
        }

        @Override
        public Animation build() {
            return animation;
        }
    }

    private static class ResourceAnimationFactory implements ViewAnimation.AnimationFactory {
        private final Context context;
        private final int animationId;

        public ResourceAnimationFactory(Context context, int animationId) {
            this.context = context.getApplicationContext();
            this.animationId = animationId;
        }

        @Override
        public Animation build() {
            return AnimationUtils.loadAnimation(context, animationId);
        }
    }
}