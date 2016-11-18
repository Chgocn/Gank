package com.chgocn.gankio.mvp.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xu on 2015/12/29.
 */
public class Tools {
    private static Tools _instance;

    public static Tools getInstance() {
        if (_instance == null) {
            _instance = new Tools();
        }
        return _instance;
    }

    private final static String TAG = "Tools";
    private InputMethodManager imm;

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变 （DisplayMetrics类中属性scaledDensity）
     *
     * @param context
     * @param pxValue
     * @return
     */
    public int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变 （DisplayMetrics类中属性scaledDensity）
     *
     * @param context
     * @param spValue
     * @return
     */
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取当前距1970
     *
     * @return
     */
    public long getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = sdf.format(curDate);
        long time = 0;
        try {
            time = sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * 把yyyy/MM/dd HH:mm:ss 日期
     * 转换日期格式为yyyy-MM-dd HH:mm
     */
    public String getChangeDateFormat(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    /**
     * 转换日期格式为yyyy-MM-dd HH:mm
     */
    public String getChangeDateFormat(Date date) {
        String str = null;
        if (date != null && !"".equals(date)) {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = sd.format(date);
        }
        return str;
    }

    /**
     * 转换日期格式为yyyy-MM-dd
     */
    public String getChangeDayFormat(Date date) {
        String str = null;
        if (date != null && !"".equals(date)) {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            str = sd.format(date);
        }
        return str;
    }


    /**
     * 转换日期格式为yyyy-MM-dd
     */
    public String getDayFormat(String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return getChangeDayFormat(d);
    }


    /**
     * 转换月份格式为yyyy-MM
     */
    public String getChangeMonth(Date date) {
        String str = null;
        if (date != null && !"".equals(date)) {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
            str = sd.format(date);
        }
        return str;
    }

    /**
     * 转换日期格式为HH:mm
     */
    public String getChangeTimeFormat(Date date) {
        String str = null;
        if (date != null && !"".equals(date)) {
            SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
            str = sd.format(date);
        }
        return str;
    }


    /**
     * 获取当前为星期几
     *
     * @param date
     * @return
     */
    public String getChangeWeekFormat(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        String str = "" + day;
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 获取当前时间
     */
    public String getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * 获取当前时间
     */
    public String getXieData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * 获取年龄
     */
    public int getAgeByDate(String birth) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay= null;
        try {
            birthDay = sdf.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }

    /**
     * 设置bitMap新的大小
     *
     * @param bitMap
     * @param newWidth
     * @param newHeight
     * @return 新的大小的bitMap
     */
    public Bitmap setBitmapSize(Bitmap bitMap, int newWidth, int newHeight) {
        // Bitmap bitMap = BitmapFactory.decodeFile(path);
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
    }

    /**
     * MD5加密字符串
     *
     * @param pwd
     * @return MD5加密的值
     */
    public String getMD5(String pwd) {
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(pwd.getBytes());
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buffer.toString();

//        try {
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            md5.update(pwd.getBytes("UTF-8"));
//            byte[] encryption = md5.digest();
//
//            StringBuffer strBuf = new StringBuffer();
//            for (int i = 0; i < encryption.length; i++) {
//                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
//                    strBuf.append("0").append(
//                            Integer.toHexString(0xff & encryption[i]));
//                } else {
//                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
//                }
//            }
//            return strBuf.toString();
//        } catch (Exception e) {
//            return "";
//        }
    }

    /**
     * 判断网络是否连接
     *
     * @param activity
     * @return
     */
    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    // 判断当前是否使用的是 WIFI网络
    public boolean isWifiActive(Context icontext) {
        Context context = icontext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info;
        if (connectivity != null) {
            info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public void hideKeyBoard(Activity activity) {
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            //imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }


    /**
     * 获取当前栈顶activity名
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public String getTopActivityName(Context context) {
        String topActivityName = null;
        ActivityManager activityManager = (ActivityManager) (context.getSystemService(android.content.Context.ACTIVITY_SERVICE));
        List<RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            String topActivityClassName = f.getClassName();
            String temp[] = topActivityClassName.split("\\.");
            topActivityName = temp[temp.length - 1];
            System.out.println("topActivityName=" + topActivityName);
        }
        return topActivityName;
    }


    @SuppressWarnings("deprecation")
    public BitmapDrawable getResBitmap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);
        try {
            is.close();
        } catch (IOException e) {
            Log.w("getResBitmap", e.getMessage());
        }
        return new BitmapDrawable(context.getResources(), bitmap);
    }


    public Bitmap getSDBitmap(Context context, String path) {
        FileInputStream f;
        try {
            f = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            Log.w("getSDBitmap", e.getMessage());
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;//图片的长宽都是原来的1/8
        BufferedInputStream bis = new BufferedInputStream(f);
        try {
            f.close();
        } catch (IOException e) {
            Log.w("getSDBitmap", e.getMessage());
            return null;
        }
        return BitmapFactory.decodeStream(bis, null, options);
    }


    public BitmapDrawable getAssertBitmap(Context context, String name) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        Bitmap mBitmap = null;
        try {
            InputStream is = context.getResources().getAssets().open(name);
            mBitmap = BitmapFactory.decodeStream(is, null, opt);
            is.close();
        } catch (IOException e) {
            Log.w("getAssertBitmap", e.getMessage());
        }
        return new BitmapDrawable(context.getResources(), mBitmap);
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo("com.kingsmith.run", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return verCode;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo("com.kingsmith.run", 0).versionName;
        } catch (NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return verName;
    }


    /**
     * 反射获取drawable等资源id
     * 例如：int id = getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     *
     * @param value
     * @return
     */
    public int getStrLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * common match
     *
     * @param reg
     * @param string
     * @return
     */
    public boolean matcher(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

}

