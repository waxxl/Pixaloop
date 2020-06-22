package com.example.pixaloop.common.render.gpu;

import android.opengl.GLES20;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.common.base.Preconditions;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Texture;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Shader implements DisposableResource {
    public static final String NO_FILTER_FRAGMENT_SHADER = "precision highp float;\nvarying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}";
    public static final String NO_FILTER_VERTEX_SHADER = "precision highp float;\nattribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}";
    public final Map<String, ProgramObject> a = new HashMap();
    public final Map<String, ProgramObject> b = new HashMap();
    public int c;

    @Override
    public void close() throws Exception {
        dispose();
    }

    private static class ProgramObject {
        public final String a;
        public final int b;
        public final int c;
        public final int d;

        public ProgramObject(String str, int i, int i2, int i3) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }
    }

    public Shader(String str, String str2) {
        int a2 = a(35633, str);
        int a3 = a(35632, str2);
        this.c = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.c, a2);
        GLES20.glAttachShader(this.c, a3);
        GLES20.glLinkProgram(this.c);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(this.c, 35714, iArr, 0);
        GLES20.glDetachShader(this.c, a2);
        GLES20.glDetachShader(this.c, a3);
        GLES20.glDeleteShader(a2);
        GLES20.glDeleteShader(a3);
        String glGetProgramInfoLog = GLES20.glGetProgramInfoLog(this.c);
        if (glGetProgramInfoLog != null && !glGetProgramInfoLog.isEmpty()) {
            Log.w("Shader", "Shader linker:\n" + glGetProgramInfoLog);
        }
        if (iArr[0] == 1) {
            b();
        } else {
            GLES20.glDeleteProgram(this.c);
            throw new IllegalArgumentException(glGetProgramInfoLog);
        }
    }

    public static int a(int i, String str) {
        String str2;
        int[] iArr = {-1};
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        String glGetShaderInfoLog = GLES20.glGetShaderInfoLog(glCreateShader);
        String str3 = "vertex";
        if (glGetShaderInfoLog != null && !glGetShaderInfoLog.isEmpty()) {
            Locale locale = Locale.US;
            Object[] objArr = new Object[2];
            if (i == 35633) {
                str2 = str3;
            } else {
                str2 = "fragment";
            }
            objArr[0] = str2;
            objArr[1] = glGetShaderInfoLog;
            Log.w("Shader", String.format(locale, "Compiling %s shader: %s", objArr));
        }
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateShader;
        }
        Locale locale2 = Locale.US;
        Object[] objArr2 = new Object[2];
        if (i != 35633) {
            str3 = "fragment";
        }
        objArr2[0] = str3;
        objArr2[1] = glGetShaderInfoLog;
        throw new IllegalArgumentException(String.format(locale2, "Error compiling %s shader: %s", objArr2));
    }

    public int b(String str) {
        ProgramObject programObject = this.a.get(str);
        if (programObject != null) {
            return programObject.d;
        }
        return -1;
    }

    public int c() {
        return this.c;
    }

    public void d() {
        GLES20.glUseProgram(0);
    }

    public void dispose() {
        int i = this.c;
        if (i != 0) {
            GLES20.glDeleteProgram(i);
            this.c = 0;
        }
    }

    public void c(String str, float[] fArr) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 35676, 1);
        a();
        GLES20.glUniformMatrix4fv(programObject.d, 1, false, fArr, 0);
        d();
    }

    public void b(String str, int i) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 5124, 1);
        a();
        GLES20.glUniform1i(programObject.d, i);
        d();
    }

    public void b(String str, float[] fArr) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 35675, 1);
        a();
        GLES20.glUniformMatrix3fv(programObject.d, 1, false, fArr, 0);
        d();
    }

    public void a() {
        GLES20.glUseProgram(this.c);
    }

    public int a(String str) {
        ProgramObject programObject = this.b.get(str);
        if (programObject != null) {
            return programObject.d;
        }
        return -1;
    }

    public final void b() {
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(this.c, 35718, iArr, 0);
        for (int i = 0; i < iArr[0]; i++) {
            ProgramObject b2 = b(this.c, i);
            this.a.put(b2.a, b2);
        }
        int[] iArr2 = new int[1];
        GLES20.glGetProgramiv(this.c, 35721, iArr2, 0);
        for (int i2 = 0; i2 < iArr2[0]; i2++) {
            ProgramObject a2 = a(this.c, i2);
            this.b.put(a2.a, a2);
        }
    }

    public void a(String str, int i, Texture.Type type) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        int i2 = type.p == 5124 ? 36298 : 35678;
        int i3 = type.p;
        if (i3 == 5123 || i3 == 5125) {
            i2 = 36306;
        }
        a(str, programObject, i2, 1);
        a();
        GLES20.glUniform1i(programObject.d, i);
        d();
    }

    public void a(String str, int i) {
        ProgramObject programObject = this.a.get(str);
        boolean z = false;
        Preconditions.checkNotNull(programObject != null, "Can't set value for uniform \"%s\": no active uniform with such name exists in the linked shader program", (Object) str);
        int i2 = programObject.b;
        Preconditions.checkNotNull(i2 == 36198 || i2 == 35678, (Object) "Can't set value for uniform \"%s\": uniform has type [%s] instead of the expected GL_SAMPLER_2D or GL_SAMPLER_EXTERNAL_OES");
        if (programObject.c == 1) {
            z = true;
        }
        Preconditions.checkNotNull(z, "Can't set value for uniform \"%s\": uniform has size [%s] instead of the expected [1]", programObject.a, Integer.valueOf(programObject.c), 1);
        a();
        GLES20.glUniform1i(programObject.d, i);
        d();
    }

    public static ProgramObject b(int i, int i2) {
        int i3 = i;
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(i3, 35719, iArr, 0);
        byte[] bArr = new byte[iArr[0]];
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[1];
        int[] iArr4 = new int[1];
        GLES20.glGetActiveUniform(i, i2, bArr.length, iArr2, 0, iArr3, 0, iArr4, 0, bArr, 0);
        String str = new String(bArr, 0, iArr2[0]);
        return new ProgramObject(str, iArr4[0], iArr3[0], GLES20.glGetUniformLocation(i3, str));
    }

    public void a(String str, float f) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 5126, 1);
        a();
        GLES20.glUniform1f(programObject.d, f);
        d();
    }

    public void a(String str, boolean z) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 35670, 1);
        a();
        GLES20.glUniform1i(programObject.d, z ? 1 : 0);
        d();
    }

    public void a(String str, float[] fArr) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 35674, 1);
        a();
        GLES20.glUniformMatrix2fv(programObject.d, 1, false, fArr, 0);
        d();
    }

    public void a(String str, float f, float f2) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 35664, 1);
        a();
        GLES20.glUniform2f(programObject.d, f, f2);
        d();
    }

    public void a(String str, float f, float f2, float f3) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 35665, 1);
        a();
        GLES20.glUniform3f(programObject.d, f, f2, f3);
        d();
    }

    public void a(String str, float f, float f2, float f3, float f4) {
        ProgramObject programObject = this.a.get(str);
        if(programObject == null) {
            Log.e("xxl", "Shader error null" );
            return;
        }
        a(str, programObject, 35666, 1);
        a();
        GLES20.glUniform4f(programObject.d, f, f2, f3, f4);
        d();
    }

    public static ProgramObject a(int i, int i2) {
        int i3 = i;
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(i3, 35722, iArr, 0);
        byte[] bArr = new byte[iArr[0]];
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[1];
        int[] iArr4 = new int[1];
        GLES20.glGetActiveAttrib(i, i2, bArr.length, iArr2, 0, iArr4, 0, iArr3, 0, bArr, 0);
        String str = new String(bArr, 0, iArr2[0]);
        return new ProgramObject(str, iArr4[0], iArr3[0], GLES20.glGetAttribLocation(i3, str));
    }

    public static void a(String str, @Nullable ProgramObject programObject, int i, int i2) {
//        boolean z = true;
//        Preconditions.checkNotNull(programObject != null, "Can't set value for uniform \"%s\": no active uniform with such name exists in the linked shader program", (Object) str);
//        Preconditions.checkNotNull(programObject.b == i, "Can't set value for uniform \"%s\": uniform has type [%s] instead of the expected [%s]", programObject.a, Integer.valueOf(programObject.b), Integer.valueOf(i));
//        if (programObject.c != i2) {
//            z = false;
//        }
//        Preconditions.checkNotNull(z, "Can't set value for uniform \"%s\": uniform has size [%s] instead of the expected [%s]", programObject.a, Integer.valueOf(programObject.c), Integer.valueOf(i2));
    }
}
