package util;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Utils {


    public static String bytesToStringUTFNIO(byte[] bytes) {
        CharBuffer cBuffer = ByteBuffer.wrap(bytes).asCharBuffer();
        return cBuffer.toString();
    }


    public static String ConvertByteArrayToString(byte[] intNumbers) {

        String aux = new String();
        if (intNumbers.length < 10000) {
            for (int i = 1; i < intNumbers.length; i++) {
                aux += Character.toChars(intNumbers[i])[0];
            }
        }

        return aux;
    }


    public static Double pointDistance(Point origin, Point dest) {

        if (origin != null && dest != null)
            return Math.sqrt(Math.pow(origin.x - dest.x, 2) + Math.pow(origin.y - dest.y, 2));

        return null;
    }

    public static float distanceFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    /// ANDROID ID
    public static String getAndroidId(Context context) {

//private String android_id = Secure.getString(getContext().getContentResolver(),Secure.ANDROID_ID);
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    // FORM UTILS

    // ///// LOGIN UILS

    /**
     * Check String as a valid email address.
     *
     * @param email Target Email address to check.
     * @return
     */
    public static boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    // LOCALITATION GPS ------------------------------------------------

    private static final int TWO_MINUTES = 1000 * 60 * 2;

    /**
     * Determines whether one Location reading is better than the current Location fix
     *
     * @param newLocation         The new Location that you want to evaluate
     * @param currentBestLocation The current Location fix, to which you want to compare the new one
     * @result true if newLocation is the best, otherwise false if currentBestLocation is better location
     */
    public static boolean isBetterLocation(Location newLocation, Location currentBestLocation) {
        // A not null location is always better than no location
        if (currentBestLocation == null) {
            return true;
        } else if (newLocation == null)
            return false;

        try {
            // Check whether the new location fix is newer or older
            long timeDelta = newLocation.getTime() - currentBestLocation.getTime();
            boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
            boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;

            // If it's been more than two minutes since the current location, use the new location
            // because the user has likely moved
            if (isSignificantlyNewer) {
                return true;
                // If the new location is more than two minutes older, it must be worse
            } else if (isSignificantlyOlder) {
                return false;
            }

            // Check whether the new location fix is more or less accurate
            int accuracyDelta = (int) (newLocation.getAccuracy() - currentBestLocation.getAccuracy());
            boolean isLessAccurate = accuracyDelta > 0;
            boolean isMoreAccurate = accuracyDelta < 0;
            boolean isSignificantlyLessAccurate = accuracyDelta > 200;

            // Check if the old and new location are from the same provider
            boolean isFromSameProvider = isSameProvider(newLocation.getProvider(),
                    currentBestLocation.getProvider());

            boolean isNewer = timeDelta > 0;

            // Determine location quality using a combination of timeliness and accuracy
            if (isMoreAccurate) {
                return true;
            } else if (isNewer && !isLessAccurate) {
                return true;
            } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks whether two providers are the same
     */
    public static boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }


    /// PROJECT FUNCTIONS


    public static int getAppVersionInt(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }


    public static String getAppVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static String getAppPackage(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    // Set locale default
    public static void changeLocaleDefault(Context baseContext, String localeAbr) {


        Locale locale = new Locale(localeAbr);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        baseContext.getResources().updateConfiguration(config,
                baseContext.getResources().getDisplayMetrics());

    }


    // DRAWABLES AND PICTURES --------------------------------


    public static int dpToPx(int dps, DisplayMetrics metrics) {
        int px = (int) (dps * metrics.density);
        return px;
    }

    public static int pxToDp(int px, DisplayMetrics metrics) {
        int dp = (int) (px / metrics.density);
        return dp;
    }

    public static int dpToPx(Context context, int dps) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        return dpToPx(dps, metrics);
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        return pxToDp(px, metrics);
    }


//    public static void blurImageInView(Context context, int drawableResource, View view) {
//        Drawable drawable;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            drawable = context.getResources().getDrawable(drawableResource, context.getTheme());
//        } else {
//            drawable = context.getResources().getDrawable(drawableResource);
//        }
//        Bitmap blurredOne = Utils.blurImage(context, Utils.drawableToBitmap(drawable));
//        BackgroundBitmapDrawable bitmapDrawableBlur = new BackgroundBitmapDrawable(context.getResources(), blurredOne);
//        setBackground(view, bitmapDrawableBlur);
//
//    }
//
//    public static void blurRemoteImageInViewBack(final Context context, String url, View imgView) {
//        ImageLoader.getInstance().displayImage(url, new ViewAware(imgView) {
//            @Override
//            protected void setImageDrawableInto(Drawable drawable, View view) {
//                Bitmap blurredOne = Utils.blurImage(context, Utils.drawableToBitmap(drawable));
//                BackgroundBitmapDrawable bitmapDrawableBlur = new BackgroundBitmapDrawable(context.getResources(), blurredOne);
//                setBackground(view, bitmapDrawableBlur);
//            }
//
//            @Override
//            protected void setImageBitmapInto(Bitmap bitmap, View view) {
//                Bitmap blurredOne = Utils.blurImage(context, bitmap);
//                BackgroundBitmapDrawable bitmapDrawableBlur = new BackgroundBitmapDrawable(context.getResources(), blurredOne);
//                setBackground(view, bitmapDrawableBlur);
//            }
//
//        });
//    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 20.0f;

    public static Bitmap blurImage(Context context, Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap outputBitmap = null;
        try {

            Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
            outputBitmap = Bitmap.createBitmap(width, height, inputBitmap.getConfig());

            RenderScript rs = RenderScript.create(context);
            ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
            theIntrinsic.setRadius(BLUR_RADIUS);

            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);
        } catch (RSRuntimeException rse) {
            width = Math.round(width / 20.0f);
            height = Math.round(height / 20.0f);
            outputBitmap = Bitmap.createScaledBitmap(image, width, height, true);
        } catch (Exception e) {
            outputBitmap = null;
            e.printStackTrace();
        }
        return outputBitmap;
    }


    // FILES AND OBJECTS ------------------------------------------------


    // Constant with a file name
//      public static String fileName = "createResumeForm.ser";
    public static boolean saveToFile(Context context, Object obj) {
        String filename = obj.getClass().getSimpleName() + ".dat";
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Creates an object by reading it from a file
    @SuppressWarnings("unchecked")
    public static <T> T readFromFile(Context context, Class<T> classType) {
        String filename = classType.getSimpleName() + ".dat";
        T createResumeForm = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            createResumeForm = (T) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            return null;
        }
        return createResumeForm;
    }


    // ///// FILE MANAGEMENT

    /**
     * Get ArrayList file content resource.
     *
     * @param resourceId Id of resource of MainActivity
     * @param context    The MainActivity which get the resource.
     * @return ArrayList with the file content, on item per line.
     */
    public static ArrayList<String> getFileResources(Context context,
                                                     int resourceId) {

        ArrayList<String> infoArray = new ArrayList<String>();

        try {

            InputStream fstream = context.getResources().openRawResource(
                    resourceId);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // skip lines with # character
                if (!strLine.contains("#"))
                    infoArray.add(strLine);
            }
            in.close();

        } catch (Exception e) {

            return null;
        }

        return infoArray;
    }

    /**
     * Get String file content resource.
     *
     * @param resourceId Id of resource of MainActivity
     * @param context    The MainActivity which get the resource.
     * @return String with the file content or null if exception.
     */
    public static String getFileResourcesString(Context context, int resourceId) {
        StringBuilder builder = new StringBuilder();

        try {

            InputStream fstream = context.getResources().openRawResource(
                    resourceId);

            DataInputStream in = new DataInputStream(fstream);

            InputStreamReader isr = new InputStreamReader(in, "UTF-8");

            BufferedReader br = new BufferedReader(isr);
            String strLine;

            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // skip lines with # character
                if (!strLine.contains("#"))
                    // infoArray.add(strLine);
                    builder.append(strLine);
            }
            in.close();
        } catch (Exception e) {
            return null;
        }
        return builder.toString();
    }


    /**
     * Save an String to internal file.
     *
     * @param context
     * @param filename
     * @param data
     * @return False if any exception, otherwise true.
     */
    public static boolean saveDataToFile(Context context, String filename,
                                         String data) {
        try {
            FileOutputStream fOut = context.openFileOutput(filename,
                    Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(data);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Read a internal storage file.
     *
     * @param context
     * @param filename
     * @return String file content or null if any exception.
     */
    public static String readFile(Context context, String filename) {
        StringBuffer data = new StringBuffer("");
        try {
            FileInputStream fIn = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader buffreader = new BufferedReader(isr);

            String readString = buffreader.readLine();
            while (readString != null) {
                data.append(readString);
                readString = buffreader.readLine();
            }
            isr.close();
        } catch (IOException ioe) {
            return null;
        }
        return data.toString();
    }


    /**
     * Delete file.
     *
     * @param context  Application context.
     * @param filename The name of the file to delete; can not contain path
     *                 separators.
     * @return True if the file was successfully deleted; else false.
     */
    public static boolean deleteFile(Context context, String filename) {

        return context.deleteFile(filename);
    }

    /**
     * File exist or not.
     *
     * @param context
     * @param filename
     * @return true if file exist, otherwise false.
     */
    public static boolean isFile(Context context, String filename) {

        try {
            @SuppressWarnings("unused")
            FileInputStream fIn = context.openFileInput(filename);

        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    // SHARED PREFERENCES


    // Constant with a file name
//      public static String fileName = "createResumeForm.ser";
    public static boolean saveToSharedPreferencesAsJSON(SharedPreferences sp, Object obj) {
        return saveToSharedPreferencesAsJSON(sp, obj, obj.getClass().getName());
    }

    public static Gson gson = new Gson();

    public static boolean saveToSharedPreferencesAsJSON(SharedPreferences sp, Object obj, String className) {
        synchronized (obj.getClass()) {
            SharedPreferences.Editor prefsEditor = sp.edit();

            String json = toJson(obj);
            prefsEditor.putString(className, json);
            return prefsEditor.commit();
        }
    }

    public static void clearSharedPreferences(SharedPreferences sharedPreferences, Object obj, String nameObject) {

        saveToSharedPreferencesAsJSON(sharedPreferences, obj, nameObject);

//        sharedPreferences.edit().clear();
//        sharedPreferences.edit().apply();
//        boolean result = sharedPreferences.edit().commit();

        Object objectResult = readFromSharedPreferencesAsJSON(sharedPreferences, obj.getClass());

        if (objectResult != null)
            objectResult.toString();


    }

    public static <T> T readFromSharedPreferencesAsJSON(SharedPreferences sp, Class<T> classType) {
        return readFromSharedPreferencesAsJSON(sp, classType, classType.getName());
    }

    public static <T> T readFromSharedPreferencesAsJSON(SharedPreferences sp, Class<T> classType, String className) {
        T result = null;
        synchronized (classType) {
            try {
                String json = sp.getString(className, null);
                if (json != null)
                    result = gson.fromJson(json, classType);
            } catch (IllegalStateException | JsonSyntaxException e) {
                return null;
            }
        }
        return result;
    }

    public static String toJson(Object obj) {
        String json = gson.toJson(obj);
        return json;
    }

    public static void removeFromSharedPreferences(SharedPreferences sp) {

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public static void removeFromSharedPreferencesByKey(SharedPreferences sp, String key) {

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }


    // JSON FUNCTIONS ----------------------------------------------------------------

    public static String[] jsonArrayStringToStringArray(String str) {

        List<String> result = new ArrayList<String>();
        if (str != null) {
            try {
                JSONArray jsonArr = new JSONArray(str);
                for (int i = 0; i < jsonArr.length(); i++) {
                    result.add(jsonArr.getString(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toArray(new String[result.size()]);
    }


    // DATE FUNCTIONS -----------------------------------------------------

    // ////// DATE FUNCTIONS

    /**
     * Return date in specified format.
     *
     * @param milliSeconds Date in milliseconds
     * @param dateFormat   Date format
     * @return String representing date in specified format
     */
    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified
        // format.
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in
        // milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    /**
     * Return current date in dateFormat format.
     *
     * @param dateFormat Date format.
     * @return Current date in dateFormat format.
     */
//    public static String getCurrentDate(String dateFormat) {
//
//        // Set last access in setting file
//        SimpleDateFormat sdf = new SimpleDateFormat(DataManagment.DATE_DEFAULT);
//        Calendar cal = Calendar.getInstance();
//
//        return sdf.format(cal.getTime());
//    }

    /**
     * Return current date in dateFormat format.
     *
     * @return Current date in dateFormat format.
     */
    public static long getCurrentDateMillis() {

        // Set last access in setting file
        Calendar cal = Calendar.getInstance();

        return cal.getTimeInMillis();
    }

    /**
     * Split milliseconds in days, hours, minutes, seconds and millis
     *
     * @param milliseconds
     * @return int[] where days are int[0].. and millis are in int[4]
     */
    protected int[] getSplitTime(long milliseconds) {
        int[] timeResult = new int[5];

        timeResult[0] = (int) (milliseconds / (1000 * 60 * 60 * 24));
        milliseconds %= (1000 * 60 * 60 * 24);
        timeResult[1] = (int) (milliseconds / (1000 * 60 * 60));
        milliseconds %= (1000 * 60 * 60);
        timeResult[2] = (int) (milliseconds / (1000 * 60));
        milliseconds %= (1000 * 60);
        timeResult[3] = (int) (milliseconds / 1000);
        milliseconds %= (1000);
        timeResult[4] = (int) milliseconds;

        return timeResult;
    }


    ////////// PHONE INFORMATION
    public static String getDeviceName(String divider) {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        String product = Build.PRODUCT;
        if (model.startsWith(manufacturer)) {
            return model + divider + product;
        } else {
            return manufacturer + divider + model + divider + product;
        }
    }


    public static JSONArray sortByDistance(JSONArray array, Location mylocation, String latitude, String longitude, double maxDistanceZone) {
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        double lat, lon;
        JSONObject row;

        try {
            for (int i = 0; i < array.length(); i++) {
                row = array.getJSONObject(i);

                lat = row.getDouble(latitude);
                lon = row.getDouble(longitude);

                double distance = (6371 * Math.acos(Math.cos(Math.toRadians(mylocation.getLatitude())) *
                        Math.cos(Math.toRadians(lat)) *
                        Math.cos(Math.toRadians(lon) - Math.toRadians(mylocation.getLongitude())) +
                        Math.sin(Math.toRadians(mylocation.getLatitude())) * Math.sin(Math.toRadians(lat)))) * 1000;

                if (distance <= maxDistanceZone) {
                    if (jsons.size() == 0) jsons.add(row);

                    else {
                        int j = 0;
                        boolean found = false;

                        while (j < jsons.size() && !found) {
                            JSONObject obj = jsons.get(j);
                            double latJ, lonJ;

                            latJ = obj.getDouble(latitude);
                            lonJ = obj.getDouble(longitude);

                            double distanceJ = (6371 * Math.acos(Math.cos(Math.toRadians(mylocation.getLatitude())) *
                                    Math.cos(Math.toRadians(latJ)) *
                                    Math.cos(Math.toRadians(lonJ) - Math.toRadians(mylocation.getLongitude())) +
                                    Math.sin(Math.toRadians(mylocation.getLatitude())) * Math.sin(Math.toRadians(latJ))));

                            if (distance < distanceJ) {
                                jsons.add(j, row);
                                found = true;
                            }
                            j++;
                        }
                        if (!found && j >= jsons.size())
                            jsons.add(row);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONArray(jsons);
    }


    public static Location cloneLocation(Location adminLocation, String tag) {

        Location location = new Location(tag);
        location.setLatitude(adminLocation.getLatitude());
        location.setLongitude(adminLocation.getLongitude());

        return location;
    }

    public static String getHourMinuteFormat(int hours, int minutes) {

        String result = String.valueOf(hours) + ":";

        if (minutes < 10)
            result += "0";//String.valueOf(minutes);

        result += String.valueOf(minutes);
        return result;
    }
}

