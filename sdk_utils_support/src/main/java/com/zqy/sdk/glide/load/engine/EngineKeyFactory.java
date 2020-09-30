package com.zqy.sdk.glide.load.engine;

import com.zqy.sdk.glide.load.Encoder;
import com.zqy.sdk.glide.load.Key;
import com.zqy.sdk.glide.load.ResourceDecoder;
import com.zqy.sdk.glide.load.ResourceEncoder;
import com.zqy.sdk.glide.load.Transformation;
import com.zqy.sdk.glide.load.resource.transcode.ResourceTranscoder;

class EngineKeyFactory {

    @SuppressWarnings("rawtypes")
    public EngineKey buildKey(String id, Key signature, int width, int height, ResourceDecoder cacheDecoder,
            ResourceDecoder sourceDecoder, Transformation transformation, ResourceEncoder encoder,
            ResourceTranscoder transcoder, Encoder sourceEncoder) {
        return new EngineKey(id, signature, width, height, cacheDecoder, sourceDecoder, transformation, encoder,
                transcoder, sourceEncoder);
    }

}
