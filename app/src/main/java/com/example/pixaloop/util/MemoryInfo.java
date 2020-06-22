package com.example.pixaloop.util;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public class MemoryInfo {
    @Nullable
    public static MemoryInfo a;
    public final Map<String, Long> b;

    public MemoryInfo(Map<String, Long> map) {
        this.b = ImmutableMap.copyOf(map);
    }

    public static MemoryInfo a() {
        return new MemoryInfo(d());
    }

    public static MemoryInfo c() {
        if (a == null) {
            a = a();
        }
        return a;
    }

    public static final Map<String, Long> d() {
        final Pattern compile = Pattern.compile("(\\S+):\\s*(\\d+)\\s*kB");
//          final Map<String, Long> a = Maps.newHashMap();
//         (Map<String, Long>) Files.map(new File("/proc/meminfo"), Charsets.UTF_8, new LineProcessor<Map<String, Long>>() {
//
//
//            public boolean a(String str) {
//                Matcher matcher = compile.matcher(str);
//                if (!matcher.matches()) {
//                    return false;
//                }
//                try {
//                    a.put(matcher.group(1), Long.valueOf(matcher.group(2)));
//                } catch (NumberFormatException unused) {
//                }
//                return false;
//            }
//
//            @Override
//            public boolean processLine(String line) throws IOException {
//                return false;
//            }
//
//            public Map<String, Long> getResult() {
//                return a;
//            }
//        });
        return Maps.newHashMap();
    }

    public long b() {
        Long l = this.b.get("MemTotal");
        if (l != null) {
            return l.longValue();
        }
        return -1;
    }
}
