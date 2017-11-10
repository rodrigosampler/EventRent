package br.com.rodrigosampler.eventrent.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rodrigo on 05/10/17.
 */

public class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;
    private static FirebaseUser usuarioCurent;

    public static DatabaseReference getFirebase(){
        if (referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

    public static FirebaseUser getFirebaseCurrent(){
        usuarioCurent = FirebaseAuth.getInstance().getCurrentUser();
        return usuarioCurent;
    }
}
