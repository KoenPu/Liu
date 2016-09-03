/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package wuxian.com.liu;

import javax.crypto.spec.IvParameterSpec;
import android.content.SharedPreferences;
import javax.crypto.SecretKey;
import java.util.Arrays;
import android.util.Base64;
import javax.crypto.Cipher;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import android.util.Log;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.util.Set;

public class PasswordUtil {
    private static final String TAG = "PasswordUtil";
    private String cipherName;
    private IvParameterSpec ivParms;
    private SharedPreferences mSettings;
    private String prefPrefix;
    private byte[] salt;
    private SecretKey secret;
    
    PasswordUtil(SharedPreferences prefs) {
        mSettings = prefs;
        prefPrefix = "pwdv1";
        regenerate(false);
    }
    
    void regenerate(boolean reset) {
        cipherName = "AES/CBC/PKCS5Padding";
        byte[] iv = {0xd40008d6, 0xd40008e1, 0xd400088b, 0xd4000865, 0xd4000819, 0xd4000877, 0xd400087f, 0xd4000825, 0xd4000879, 0xd40008ca, 0xd400082e, 0xd4000831, 0xd40008dd, 0xd40008d0, 0xd40008b8, 0xd4000861};
        salt = 0x0;
        if(!reset) {
            salt = get_salt();
        }
        if((reset) || (salt == null)) {
            remove(0x0);
            generate_salt();
            salt = get_salt();
        }
        if(salt != null) {
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt, 0x10, 0x80);
                SecretKey k = factory.generateSecret(spec);
                secret = new SecretKeySpec(k.getEncoded(), cipherName);
                ivParms = new IvParameterSpec(iv);
                return;
            } catch(Exception e) {
                Log.e("PasswordUtil", "regenerate", e);
            }
            secret = 0x0;
            prefPrefix = 0x0;
        }
    }
    
    private void check_salt() {
        byte[] s = get_salt();
        if((s != null) && (salt != null) && (!Arrays.equals(s, salt))) {
            regenerate(false);
        }
    }
    
    private void generate_salt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[0x10];
        sr.nextBytes(salt);
        SharedPreferences.Editor editor = mSettings.edit();
        String key = key_string("settings", "entropy");
        String salt_b64 = Base64.encodeToString(salt, 0x2);
        editor.putString(key, salt_b64);
        editor.apply();
    }
    
    private byte[] get_salt() {
        try {
            String key = key_string("settings", "entropy");
            String salt_b64 = mSettings.getString(key, 0x0);
            if(salt_b64 != null) {
                return Base64.decode(salt_b64, 0x0);
            }
        } catch(Exception localException1) {
        }
        return null;
    }
    
    private String en(String pwd_cleartext) {
        check_salt();
        if((pwd_cleartext != null) && (secret != null) && (prefPrefix != null)) {
            try {
                Cipher cipher = Cipher.getInstance(cipherName);
                cipher.init(0x1, secret, ivParms);
                byte[] ciphertext = cipher.doFinal(pwd_cleartext.getBytes("UTF-8"));
                return Base64.encodeToString(ciphertext, 0x2);
            } catch(Exception e) {
                Log.e("PasswordUtil", "en", e);
                regenerate(true);
            }
        }
        return null;
    }
    
    private String de(String pwd_ciphertext) {
        check_salt();
        if((pwd_ciphertext != null) && (secret != null) && (prefPrefix != null)) {
            try {
                byte[] ciphertext = Base64.decode(pwd_ciphertext, 0x0);
                Cipher cipher = Cipher.getInstance(cipherName);
                cipher.init(0x2, secret, ivParms);
                return new String(cipher.doFinal(ciphertext), "UTF-8");
            } catch(Exception e) {
                Log.e("PasswordUtil", "de", e);
                regenerate(true);
            }
        }
        return null;
    }
    
    private String key_string(String type, String account) {
        return String.format("%s.%s.%s", prefPrefix, type, account);
    }
    
    private String key_prefix(String type) {
        if(type != null) {
            return String.format("%s.%s.", prefPrefix, type);
        }
        return String.format("%s.", prefPrefix, type, prefPrefix);
    }
    
    public String get(String type, String account) {
        try {
            String key = key_string(type, account);
            String ciphertext = mSettings.getString(key, 0x0);
            return de(ciphertext);
        } catch(ClassCastException e) {
            Log.d("PasswordUtil", "get() class cast exception");
            regenerate(true);
        }
        return null;
    }
    
    public void set(String type, String account, String pw) {
        SharedPreferences.Editor editor = mSettings.edit();
        String key = key_string(type, account);
        String ciphertext = en(pw);
        if(ciphertext != null) {
            editor.putString(key, ciphertext);
            editor.apply();
        }
    }
    
    public void remove(String type, String account) {
        SharedPreferences.Editor editor = mSettings.edit();
        String key = key_string(type, account);
        editor.remove(key);
        editor.apply();
    }
    
    public void remove(String type) {
        SharedPreferences.Editor editor = mSettings.edit();
        Set<String> pref_keys = mSettings.getAll().keySet();
        String prefix = key_prefix(type);
        for(String key : pref_keys) {
            if(key.startsWith(prefix)) {
                editor.remove(key);
            }
        }
        editor.apply();
    }
}
