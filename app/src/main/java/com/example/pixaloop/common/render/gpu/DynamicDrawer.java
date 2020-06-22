package com.example.pixaloop.common.render.gpu;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.renderscript.Float2;
import android.renderscript.Float3;
import android.renderscript.Float4;
import android.renderscript.Matrix2f;
import android.renderscript.Matrix3f;
import android.renderscript.Matrix4f;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.utils.SizeF;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DynamicDrawer implements DisposableResource {
    public final Shader a;
    public final List<GpuStruct> b;
    public Map<String, ArrayBuffer> c = Maps.newHashMap();

    public DynamicDrawer(@NonNull Shader shader, @NonNull List<GpuStruct> list) {
        Preconditions.checkNotNull(list, (Object) "gpuStructs can not be null");
        e(list);
        this.a = shader;
        this.b = list;
        a();
    }

    public final void a() {
        this.a.a();
        for (GpuStruct c2 : this.b) {
            this.c.put(c2.c(), ArrayBuffer.a(35048));
        }
        this.a.d();
    }

    public final void b(List<AttributeData> list) {
        for (AttributeData next : list) {
            ArrayBuffer arrayBuffer = this.c.get(next.b().c());
            arrayBuffer.a(next.a());
            b(arrayBuffer, next.b());
        }
    }

    public final void c(List<Pair<String, Object>> list) {
        for (Pair next : list) {
            String str = (String) next.first;
            Object obj = next.second;
            if (obj instanceof Integer) {
                this.a.b(str, ((Integer) obj).intValue());
            } else if (obj instanceof Float) {
                this.a.a(str, ((Float) obj).floatValue());
            } else if (obj instanceof Boolean) {
                this.a.a(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof SizeF) {
                SizeF sizeF = (SizeF) obj;
                this.a.a(str, sizeF.b(), sizeF.a());
            } else if (obj instanceof PointF) {
                PointF pointF = (PointF) obj;
                this.a.a(str, pointF.x, pointF.y);
            } else if (obj instanceof Float2) {
                Float2 float2 = (Float2) obj;
                this.a.a(str, float2.x, float2.y);
            } else if (obj instanceof Float3) {
                Float3 float3 = (Float3) obj;
                this.a.a(str, float3.x, float3.y, float3.z);
            } else if (obj instanceof Float4) {
                Float4 float4 = (Float4) obj;
                this.a.a(str, float4.x, float4.y, float4.z, float4.w);
            } else if (obj instanceof Vector2) {
                Vector2 vector2 = (Vector2) obj;
                this.a.a(str, vector2.a(), vector2.b());
            } else if (obj instanceof Vector3) {
                Vector3 vector3 = (Vector3) obj;
                this.a.a(str, vector3.a(), vector3.b(), vector3.c());
            } else if (obj instanceof Vector4) {
                Vector4 vector4 = (Vector4) obj;
                this.a.a(str, vector4.b(), vector4.c(), vector4.d(), vector4.a());
            } else if (obj instanceof Matrix2f) {
                this.a.a(str, ((Matrix2f) obj).getArray());
            } else if (obj instanceof Matrix3f) {
                this.a.b(str, ((Matrix3f) obj).getArray());
            } else if (obj instanceof Matrix) {
                float[] fArr = new float[9];
                ((Matrix) obj).getValues(fArr);
                this.a.b(str, fArr);
            } else if (obj instanceof Matrix4f) {
                this.a.c(str, ((Matrix4f) obj).getArray());
            } else {
                throw new RuntimeException("Unknown uniform type " + obj.getClass() + " (name: " + str + ")");
            }
        }
    }

    public final void d(@NonNull List<AttributeData> list) {
        Preconditions.checkNotNull(this.b.size() == list.size(), (Object) "Number of GPU structs must be identical for each render pass");
        for (AttributeData b2 : list) {
            Preconditions.checkArgument(this.b.contains(b2.b()));
        }
    }

    public void dispose() {
        for (ArrayBuffer dispose : this.c.values()) {
            dispose.dispose();
        }
        this.c.clear();
    }

    public final void e(List<GpuStruct> list) {
        Preconditions.checkNotNull(!list.isEmpty(), (Object) "At least one GPU struct must be provided");
        HashSet b2 = Sets.newHashSet();
        for (GpuStruct c2 : list) {
            String c3 = c2.c();
            Preconditions.checkNotNull(!b2.contains(c3), (Object) "More then 1 GPU struct named " + c3);
            b2.add(c3);
        }
        HashSet b3 = Sets.newHashSet();
        for (GpuStruct next : list) {
            HashSet hashSet = new HashSet(Lists.transform(next.b(), C0133lb.a));
            Sets.SetView b4 = Sets.intersection(b3, hashSet);
            boolean isEmpty = b4.isEmpty();
            Preconditions.checkNotNull(isEmpty, (Object) "GPU struct " + next.toString() + " contains fields " + b4.toString() + " ,which were already encountered");
            b3.addAll(hashSet);
        }
    }

    public final void b(ArrayBuffer arrayBuffer, GpuStruct gpuStruct) {
        arrayBuffer.a();
        for (GpuStructField next : gpuStruct.b()) {
            int a2 = this.a.a(next.b());
            if (a2 == -1) {
                Log.w("DynamicDrawer", next.b() + "attribute is not found in the shader");
            } else {
                GLES20.glEnableVertexAttribArray(a2);
                GLES20.glVertexAttribPointer(a2, next.a(), next.d(), next.c(), gpuStruct.d(), gpuStruct.a(next.b()));
            }
        }
        arrayBuffer.b();
    }

    public void a(int i, int i2, List<Pair<String, Object>> list, Map<String, Texture> map, List<AttributeData> list2) {
        a(i, i2, list, map, (Map<String, Integer>) null, list2);
    }

    public void a(int i, int i2, List<Pair<String, Object>> list, Map<String, Texture> map, Map<String, Integer> map2, List<AttributeData> list2) {
        Preconditions.checkNotNull(i == 4 || i == 5);
        if (list != null) {
            c(list);
        }
        a(map, map2);
        this.a.a();
        if (list2 != null) {
            d(list2);
            b(list2);
        }
        GLES20.glDrawArrays(i, 0, i2);
        if (list2 != null) {
            a(list2);
        }
        this.a.d();
    }

    public final void a(Map<String, Texture> map, Map<String, Integer> map2) {
        int i = 0;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                GLES20.glActiveTexture(i + 33984);
                Texture texture = (Texture) next.getValue();
                texture.a();
                this.a.a((String) next.getKey(), i, texture.k());
                i++;
            }
        }
        if (map2 != null) {
            for (Map.Entry next2 : map2.entrySet()) {
                GLES20.glActiveTexture(i + 33984);
                GLES20.glBindTexture(36197, ((Integer) next2.getValue()).intValue());
                this.a.a((String) next2.getKey(), i);
                i++;
            }
        }
    }

    public final void a(List<AttributeData> list) {
        for (AttributeData next : list) {
            a(this.c.get(next.b().c()), next.b());
        }
    }

    public final void a(ArrayBuffer arrayBuffer, GpuStruct gpuStruct) {
        arrayBuffer.a();
        for (GpuStructField b2 : gpuStruct.b()) {
            int a2 = this.a.a(b2.b());
            if (a2 != -1) {
                GLES20.glDisableVertexAttribArray(a2);
            }
        }
        arrayBuffer.b();
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
