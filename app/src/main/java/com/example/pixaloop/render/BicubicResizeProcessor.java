package com.example.pixaloop.render;

import android.renderscript.Matrix3f;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Shader;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.gpu.TexturedRect;
import com.example.pixaloop.util.ShaderLoader;
import com.google.common.collect.Maps;
import java.util.HashMap;

public class BicubicResizeProcessor implements DisposableResource {
    public final TexturedRect a;
    public final Shader shader = new Shader(ShaderLoader.a("LTPassthroughShader.vsh"), ShaderLoader.a("LTBicubicResize.fsh"));

    public BicubicResizeProcessor(Texture texture) {
//        this.shader.b("textureTransform", new Matrix3f().getArray());
//        this.shader.a("texelStep", 1.0f / ((float) texture.l()), 1.0f / ((float) texture.i()));
        HashMap c = Maps.newHashMap();
        c.put("sourceTexture", texture);
        this.a = new TexturedRect(c, "sourceTexture", this.shader);
    }

    public void a() {
        this.a.a();
    }

    public void dispose() {
        this.a.dispose();
        this.shader.dispose();
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
