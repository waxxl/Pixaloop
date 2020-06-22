package com.example.pixaloop.render;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.gpu.Vector2;
import com.example.pixaloop.common.render.painter.Painter;
import com.example.pixaloop.features.StrokeData;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class FreezePainter implements DisposableResource {
    public final Painter a;
    public List<StrokeData> b = new ArrayList();
    public boolean c = false;

    public FreezePainter(Painter painter) {
        this.a = painter;
    }

    public boolean a(ImmutableList<StrokeData> immutableList, boolean z) {
        ImmutableList<StrokeData> immutableList2 = null;
        if (immutableList.size() != this.b.size()) {
            if (this.b.size() == 0 || immutableList.size() < this.b.size()) {
                this.a.g();
                immutableList2 = immutableList;
            } else {
                ImmutableList<StrokeData> subList = immutableList.subList(this.b.size(), immutableList.size());
                if (subList.size() != 0) {
                    immutableList2 = subList;
                }
            }
        }
        if (immutableList2 != null) {
            a(immutableList2);
            this.c = true;
        }
        this.b = immutableList;
        boolean z2 = false;
        boolean z3 = !z && this.c;
        if (!z3 && this.c) {
            z2 = true;
        }
        this.c = z2;
        return z3;
    }

    public void dispose() {
        Painter painter = this.a;
        if (painter != null) {
            painter.dispose();
        }
    }

    public void a(List<StrokeData> list) {
        for (StrokeData next : list) {
            this.a.a(next.d().a());
            this.a.a((double) next.e());
            this.a.a(next.f(), next.b(), (List<Vector2>) null);
            this.a.d();
        }
    }

    public Texture a() {
        return this.a.f();
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}