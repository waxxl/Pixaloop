package com.example.pixaloop.render;

import android.renderscript.Matrix3f;

import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Shader;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.gpu.TexturedRect;
import com.example.pixaloop.util.ShaderLoader;
import com.google.common.collect.Maps;
import java.util.HashMap;

public class QuadMixerProcessor implements DisposableResource {
    public final TexturedRect a;
    public final Shader b = new Shader(ShaderLoader.a("LTPassthroughShader.vsh"), ShaderLoader.a("LTMixer.fsh"));

    public QuadMixerProcessor(Texture texture, Texture texture2, Texture texture3) {
        this.b.b("textureTransform", new Matrix3f().getArray());
        this.b.b("blendMode", 0);
        this.b.a("opacity", 1.0f);
        this.b.b("frontMatrix", new Matrix3f().getArray());
        this.b.b("maskMatrix", new Matrix3f().getArray());
        HashMap c = Maps.newHashMap();
        c.put("sourceTexture", texture);
        c.put("frontTexture", texture2);
        c.put("maskTexture", texture3);
        this.a = new TexturedRect(c, "sourceTexture", this.b);
    }

    public void a() {
        this.a.a();
    }

    public void dispose() {
        this.a.dispose();
        this.b.dispose();
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
