package com.joker.logs;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.util.Log.*;

/**
 * JLog
 * Log统一管理类
 * Debug版本输出LOG release 不输出LOG 不需要做其他处理
 *
 * @author joker
 * @date 2019/1/18.
 */
public final class JLog {

    private static final String J_TAG = "JLog";
    private static final String LINE_SEPARATOR = "\n";
    private static final int MAX_LENGTH = 4000;
    private static final String REGEX = "(.{" + MAX_LENGTH + "})";

    private static String tag = J_TAG;
    private static boolean debug = true;
    private static String title;

    //默认获取堆栈中的index
    public static final int TRACE_INDEX_4 = 4;
    //封装一层工具类，使用它获取堆栈中的index
    public static final int TRACE_INDEX_5 = 5;

    private void Log() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void init(Context context) {
        init(context, J_TAG);
    }

    public static void init(Context context, String tagName) {
        debug = context.getApplicationInfo() != null && (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        tag = tagName;
    }

    private static StackTraceElement getStackTraceElement(int traceIndex) {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace[traceIndex];
    }

    private static void getMethodNames(StackTraceElement element) {
        String className = element.getFileName();
        String methodName = element.getMethodName();
        int lineNumber = element.getLineNumber();

        //logcat中只要打印的内容中带有”(类名.java:行号)”就可以自动变为可点击的链接
        title = "JLog in " + "(" + className + ':' + lineNumber + ") -> " + methodName;
    }

    public static void v(String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, VERBOSE);
        }
    }

    public static void v(String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, VERBOSE);
        }
    }

    public static void v(String tag, String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, VERBOSE);
        }
    }

    public static void v(String tag, String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, VERBOSE);
        }
    }

    public static void d(String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, DEBUG);
        }
    }

    public static void d(String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, DEBUG);
        }
    }

    public static void d(String tag, String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, DEBUG);
        }
    }

    public static void d(String tag, String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, DEBUG);
        }
    }

    public static void i(String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, INFO);
        }
    }

    public static void i(String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, INFO);
        }
    }

    public static void i(String tag, String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, INFO);
        }
    }

    public static void i(String tag, String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, INFO);
        }
    }

    public static void w(String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, WARN);
        }
    }

    public static void w(String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, WARN);
        }
    }

    public static void w(String tag, String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, WARN);
        }
    }

    public static void w(String tag, String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, WARN);
        }
    }

    public static void e(String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, ERROR);
        }
    }

    public static void e(String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, ERROR);
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            getMethodNames(getStackTraceElement(TRACE_INDEX_4));
            print(tag, msg, ERROR);
        }
    }

    public static void e(String tag, String msg, int traceIndex) {
        if (debug) {
            getMethodNames(getStackTraceElement(traceIndex));
            print(tag, msg, ERROR);
        }
    }

    private static void print(String tag, String msg, int type) {
        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = formatMessage(msg);
            }
        } catch (JSONException e) {
            message = formatMessage(msg);
        }

        log(tag, "[═══════════════════════════════════════════ start " + title + " ═══════════════════════════════════════════]", type);
        final String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            log(tag, line, type);
        }
        log(tag, "[═══════════════════════════════════════════  end  " + title + " ═══════════════════════════════════════════]", type);
    }

    private static String formatMessage(String msg) {
        return msg.replaceAll(REGEX, "$1" + LINE_SEPARATOR);
    }

    private static void log(String tag, String msg, int type) {
        switch (type) {
            case VERBOSE:
                Log.v(tag, msg);
                return;
            case DEBUG:
                Log.d(tag, msg);
                return;
            case INFO:
                Log.i(tag, msg);
                return;
            case WARN:
                Log.w(tag, msg);
                return;
            case ERROR:
                Log.e(tag, msg);
                return;
            default:
                Log.v(tag, msg);
        }
    }
}
