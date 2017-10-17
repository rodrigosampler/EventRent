package br.com.rodrigosampler.eventrent.Helper;

import android.util.Base64;

/**
 * Created by rodrigo on 05/10/17.
 */

public class Base64Custom {

    public static String codificadorBase64 (String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodificarBase64 (String textoCodificado){
        return new String(Base64.decode(textoCodificado, Base64.DEFAULT));
    }
}
