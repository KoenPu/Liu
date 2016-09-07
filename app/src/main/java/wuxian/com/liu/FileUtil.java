/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package wuxian.com.liu;

import java.io.IOException;
import java.io.File;
import android.content.Context;
import java.io.InputStream;
import java.io.FileInputStream;
import android.net.Uri;
import java.io.FileOutputStream;

public class FileUtil {
    
    public FileUtil() {
    }
    
    public static String readFile(String path, long max_len) throws IOException {
        return readStream(new FileInputStream(path), max_len, path);
    }
    
    public static String readUri(Context context, Uri uri, long max_len) throws IOException {
        return readStream(context.getContentResolver().openInputStream(uri), max_len, uriBasename(uri));
    }
    
    public static String readAsset(Context context, String filename) throws IOException {
        return readStream(context.getResources().getAssets().open(filename), 0x0);
    }
    
    public static String readFileAppPrivate(Context context, String filename) throws IOException {
        return readStream(context.openFileInput(filename), 0x0);
    }
    
    public static void writeFileAppPrivate(Context context, String filename, String content) throws IOException {
        FileOutputStream fos = context.openFileOutput(filename, 0x0);
        fos.write(content.getBytes());
        fos.close();
    }
    
    public static String readStream(InputStream stream, long max_len, String fn) throws IOException {
        // :( Parsing error. Please contact me.
    }
    
    public static byte[] readFileByteArray(String path, long max_len) throws IOException {
        File file = new File(path);
        FileInputStream is = new FileInputStream(file);
        long length = file.length();
        if((max_len > 0x0) && (length > max_len)
         || length > 0x7fffffff) {
            throw new FileUtil.FileTooLarge(path, max_len);
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0x0;
        int numRead = 0x0;
        for(; offset < bytes.length; numRead >= 0; ) {
            offset += numRead;
        }
        (offset < bytes.length)) {
            throw new IOException("Could not completely read file: " + path);
        }
        is.close();
        return bytes;
    }
    
    public static boolean deleteFile(String path) {
        if(path != null) {
            File file = new File(path);
            return file.delete();
        }
        return false;
    }
    
    public static boolean renameFile(String from_path, String to_path) {
        if((from_path != null) && (to_path != null)) {
            File from_file = new File(from_path);
            File to_file = new File(to_path);
            return from_file.renameTo(to_file);
        }
        return false;
    }
    
    public static String basename(String path) {
        if(path != null) {
            File theFile = new File(path);
            return theFile.getName();
        }
        return null;
    }
    
    public static String dirname(String path) {
        if(path != null) {
            File theFile = new File(path);
            return theFile.getParent();
        }
        return null;
    }
    
    public static String uriBasename(Uri uri) {
        if(uri != null) {
            return getLastPathSegment();
        }
        return null;
    }
}
