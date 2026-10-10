package com.excelliance.lbsdk.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class PlatformResources extends Resources {
    private static Resources a;
    private static Resources b;
    private static Context c;

    private PlatformResources(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration) {
        super(assetManager, displayMetrics, configuration);
    }

    private static synchronized void a(Context context) {
        URL resource;
        Locale locale;
        try {
            c = context;
            if (b == null && a == null) {
                b = context.getApplicationContext().getResources();
                String str = null;
                try {
                    ClassLoader classLoader = Class.forName("com.excelliance.open.PromptActivity").getClassLoader();
                    Enumeration<URL> resources = classLoader.getResources("assets/lbuilogid");
                    if ((resources == null || !resources.hasMoreElements()) && (resource = classLoader.getResource("assets/lbuilogid")) != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(resource);
                        resources = Collections.enumeration(arrayList);
                    }
                    while (resources.hasMoreElements()) {
                        URL urlNextElement = resources.nextElement();
                        if (urlNextElement != null) {
                            try {
                                JarURLConnection jarURLConnection = (JarURLConnection) urlNextElement.openConnection();
                                jarURLConnection.getJarEntry();
                                String name = jarURLConnection.getJarFile().getName();
                                if (name.endsWith(GameUtil.UI_JAR)) {
                                    str = name;
                                    break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    com.excelliance.lbsdk.main.g.b.c("PlatformResources", "mainPath=" + str, new Object[0]);
                    Configuration configuration = new Configuration(b.getConfiguration());
                    Configuration configuration2 = Resources.getSystem().getConfiguration();
                    if (Build.VERSION.SDK_INT >= 24) {
                        configuration.setLocales(configuration2.getLocales());
                        locale = configuration2.locale;
                    } else {
                        locale = configuration2.locale;
                    }
                    configuration.locale = locale;
                    AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
                    assetManager.getClass().getMethod("addAssetPath", String.class).invoke(assetManager, str);
                    a = new PlatformResources(assetManager, b.getDisplayMetrics(), configuration);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static Resources getApkResources(Context context) {
        a(context);
        Resources resources = b;
        if (resources != null) {
            return resources;
        }
        throw new RuntimeException("PlatformResources not inited2");
    }

    public static Resources getMainResources(Context context) {
        a(context);
        Resources resources = a;
        if (resources == null) {
            throw new RuntimeException("PlatformResources not inited1");
        }
        if (b != null) {
            return resources;
        }
        throw new RuntimeException("PlatformResources not inited2");
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i) {
        try {
            return super.getAnimation(i);
        } catch (Exception unused) {
            return b.getAnimation(i);
        }
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i) {
        try {
            return super.getBoolean(i);
        } catch (Exception unused) {
            return b.getBoolean(i);
        }
    }

    @Override // android.content.res.Resources
    public int getColor(int i) {
        try {
            return super.getColor(i);
        } catch (Exception unused) {
            return b.getColor(i);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(23)
    public int getColor(int i, Resources.Theme theme) {
        try {
            return super.getColor(i, theme);
        } catch (Exception unused) {
            return b.getColor(i, theme);
        }
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i) {
        try {
            return super.getColorStateList(i);
        } catch (Exception unused) {
            return b.getColorStateList(i);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(23)
    public ColorStateList getColorStateList(int i, Resources.Theme theme) {
        try {
            return super.getColorStateList(i, theme);
        } catch (Exception unused) {
            return b.getColorStateList(i, theme);
        }
    }

    @Override // android.content.res.Resources
    public float getDimension(int i) {
        try {
            return super.getDimension(i);
        } catch (Exception unused) {
            return b.getDimension(i);
        }
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i) {
        try {
            return super.getDimensionPixelOffset(i);
        } catch (Exception unused) {
            return b.getDimensionPixelOffset(i);
        }
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i) {
        try {
            return super.getDimensionPixelSize(i);
        } catch (Exception unused) {
            return b.getDimensionPixelSize(i);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        try {
            return super.getDrawable(i);
        } catch (Exception unused) {
            return b.getDrawable(i);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(21)
    public Drawable getDrawable(int i, Resources.Theme theme) {
        try {
            return super.getDrawable(i, theme);
        } catch (Exception unused) {
            return b.getDrawable(i, theme);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(15)
    public Drawable getDrawableForDensity(int i, int i2) {
        try {
            return super.getDrawableForDensity(i, i2);
        } catch (Exception unused) {
            return b.getDrawableForDensity(i, i2);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(21)
    public Drawable getDrawableForDensity(int i, int i2, Resources.Theme theme) {
        try {
            return super.getDrawableForDensity(i, i2, theme);
        } catch (Exception unused) {
            return b.getDrawableForDensity(i, i2, theme);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(29)
    public float getFloat(int i) {
        try {
            return super.getFloat(i);
        } catch (Exception unused) {
            return b.getFloat(i);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(26)
    public Typeface getFont(int i) {
        try {
            return super.getFont(i);
        } catch (Exception unused) {
            return b.getFont(i);
        }
    }

    @Override // android.content.res.Resources
    public float getFraction(int i, int i2, int i3) {
        try {
            return super.getFraction(i, i2, i3);
        } catch (Exception unused) {
            return b.getFraction(i, i2, i3);
        }
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        int identifier = super.getIdentifier(str, str2, "com.excelliance.lbui");
        return identifier > 0 ? identifier : getApkResources(c).getIdentifier(str, str2, str3) & 1073741823;
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i) {
        try {
            return super.getIntArray(i);
        } catch (Exception unused) {
            return b.getIntArray(i);
        }
    }

    @Override // android.content.res.Resources
    public int getInteger(int i) {
        try {
            return super.getInteger(i);
        } catch (Exception unused) {
            return b.getInteger(i);
        }
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i) {
        try {
            return super.getLayout(i);
        } catch (Exception unused) {
            return b.getLayout(i);
        }
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i) {
        try {
            return super.getMovie(i);
        } catch (Exception unused) {
            return b.getMovie(i);
        }
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i, int i2) {
        try {
            return super.getQuantityString(i, i2);
        } catch (Exception unused) {
            return b.getQuantityString(i, i2);
        }
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i, int i2, Object... objArr) {
        try {
            return super.getQuantityString(i, i2, objArr);
        } catch (Exception unused) {
            return b.getQuantityString(i, i2, objArr);
        }
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int i, int i2) {
        try {
            return super.getQuantityText(i, i2);
        } catch (Exception unused) {
            return b.getQuantityText(i, i2);
        }
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i) {
        try {
            return super.getResourceEntryName(i);
        } catch (Exception unused) {
            return b.getResourceEntryName(i);
        }
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i) {
        try {
            return super.getResourceName(i);
        } catch (Exception unused) {
            return b.getResourceName(i);
        }
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i) {
        try {
            return super.getResourcePackageName(i);
        } catch (Exception unused) {
            return b.getResourcePackageName(i);
        }
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i) {
        try {
            return super.getResourceTypeName(i);
        } catch (Exception unused) {
            return b.getResourceTypeName(i);
        }
    }

    @Override // android.content.res.Resources
    public String getString(int i) {
        try {
            return super.getString(i);
        } catch (Exception unused) {
            return b.getString(i);
        }
    }

    @Override // android.content.res.Resources
    public String getString(int i, Object... objArr) {
        try {
            return super.getString(i, objArr);
        } catch (Exception unused) {
            return b.getString(i, objArr);
        }
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i) {
        return super.getStringArray(i);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i) {
        try {
            return super.getText(i);
        } catch (Exception unused) {
            return b.getText(i);
        }
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i, CharSequence charSequence) {
        try {
            return super.getText(i, charSequence);
        } catch (Exception unused) {
            return b.getText(i, charSequence);
        }
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i) {
        try {
            return super.getTextArray(i);
        } catch (Exception unused) {
            return b.getTextArray(i);
        }
    }

    @Override // android.content.res.Resources
    public void getValue(int i, TypedValue typedValue, boolean z) {
        try {
            super.getValue(i, typedValue, z);
        } catch (Exception unused) {
            b.getValue(i, typedValue, z);
        }
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z) {
        try {
            super.getValue(str, typedValue, z);
        } catch (Exception unused) {
            b.getValue(str, typedValue, z);
        }
    }

    @Override // android.content.res.Resources
    @TargetApi(15)
    public void getValueForDensity(int i, int i2, TypedValue typedValue, boolean z) {
        try {
            super.getValueForDensity(i, i2, typedValue, z);
        } catch (Exception unused) {
            b.getValueForDensity(i, i2, typedValue, z);
        }
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int i) {
        try {
            return super.getXml(i);
        } catch (Exception unused) {
            return b.getXml(i);
        }
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        try {
            return super.obtainAttributes(attributeSet, iArr);
        } catch (Exception unused) {
            return b.obtainAttributes(attributeSet, iArr);
        }
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int i) {
        try {
            return super.obtainTypedArray(i);
        } catch (Exception unused) {
            return b.obtainTypedArray(i);
        }
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i) {
        try {
            return super.openRawResource(i);
        } catch (Exception unused) {
            return b.openRawResource(i);
        }
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i, TypedValue typedValue) {
        try {
            return super.openRawResource(i, typedValue);
        } catch (Exception unused) {
            return b.openRawResource(i, typedValue);
        }
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i) {
        try {
            return super.openRawResourceFd(i);
        } catch (Exception unused) {
            return b.openRawResourceFd(i);
        }
    }
}
