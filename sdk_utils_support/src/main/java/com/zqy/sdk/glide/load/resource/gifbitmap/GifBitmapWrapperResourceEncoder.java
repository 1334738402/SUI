package com.zqy.sdk.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;

import com.zqy.sdk.glide.load.ResourceEncoder;
import com.zqy.sdk.glide.load.engine.Resource;
import com.zqy.sdk.glide.load.resource.gif.GifDrawable;

import java.io.OutputStream;

/**
 * A {@linkcom.zqy.sutils.glide.load.ResourceEncoder} that can encode either an {@link Bitmap} or
 * {@linkcom.zqy.sutils.glide.load.resource.gif.GifDrawable}.
 */
public class GifBitmapWrapperResourceEncoder implements ResourceEncoder<GifBitmapWrapper> {
    private final ResourceEncoder<Bitmap> bitmapEncoder;
    private final ResourceEncoder<GifDrawable> gifEncoder;
    private String id;

    public GifBitmapWrapperResourceEncoder(ResourceEncoder<Bitmap> bitmapEncoder,
            ResourceEncoder<GifDrawable> gifEncoder) {
        this.bitmapEncoder = bitmapEncoder;
        this.gifEncoder = gifEncoder;
    }

    @Override
    public boolean encode(Resource<GifBitmapWrapper> resource, OutputStream os) {
        final GifBitmapWrapper gifBitmap = resource.get();
        final Resource<Bitmap> bitmapResource = gifBitmap.getBitmapResource();

        if (bitmapResource != null) {
            return bitmapEncoder.encode(bitmapResource, os);
        } else {
            return gifEncoder.encode(gifBitmap.getGifResource(), os);
        }
    }

    @Override
    public String getId() {
        if (id == null) {
            id = bitmapEncoder.getId() + gifEncoder.getId();
        }
        return id;
    }
}
