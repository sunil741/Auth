package com.example.suniltg.auth;

/**
 * Created by sunil TG on 10/22/2016.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;



import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by sunil TG on 10/11/2016.
 */
class RetrieveEdit extends AsyncTask<String, String, String> {

    private Exception exception;
    String texttotranslate,texttotranslate2;
    Context context;
    String texttranslated="",texttranslated1="",texttranslated2="",texttranslated3="",texttranslated4="",texttranslated5="",texttranslated6="",texttranslated7="",texttranslated8="",texttranslated9="",texttranslated10="",texttranslated11="",texttranslated12="";
    String text1translated="",text1translated1="",text1translated2="",text1translated3="",text1translated4="",text1translated5="",text1translated6="",text1translated7="",text1translated8="",text1translated9="",text1translated10="",text1translated11="",text1translated12="";

    View view;
    String locale;
    DatabaseReference databaseReference;
    String key;
    String type;
    String user;
    public RetrieveEdit(String user,String ttt,String ttt1, DatabaseReference databaseReference, String key,String locale) {
        texttotranslate = ttt;
        this.user=user;
        texttotranslate2=ttt1;
        this.key=key;
        this.databaseReference = databaseReference;
        this.locale=locale;
        this.type=type;

    }

        /*try {

            Translator translate = Translator.getInstance();
            texttranslated= translate.translate(texttotranslate, Language.ENGLISH, Language.ROMANIAN);
            System.out.print(texttranslated);
            return  texttranslated;

        } catch (Exception e) {
            this.exception = e;

            return null;
        }*/
    //Translate.setClientId("SunilWithPucho7");
    // Translate.setClientSecret("SunilWithPucho123456789");

    /* try {
        /* InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
         InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
         String locale = ims.getLocale();
         String translatedText = Translate.execute(texttotranslate, com.memetix.mst.language.Language.fromString(locale), com.memetix.mst.language.Language.ENGLISH);
        }
     } catch (Exception e) {
         e.printStackTrace();
         return  null;
     }
 }*/
    @Override
    protected String doInBackground(String... params) {

           /*Translator translate = Translator.getInstance();
               texttranslated = translate.translate(texttotranslate, Language.ENGLISH, Language.ROMANIAN);
               databaseReference.child("ro_Ro").push().setValue(texttranslated);
               texttranslated = translate.translate(texttotranslate, Language.ENGLISH, Language.T);
               databaseReference.child("hi_IN").push().setValue(texttranslated);
*/
        Translate.setClientId("SunilWithPucho7");
        Translate.setClientSecret("SunilWithPucho123456789");

        String translatedText = null;
        try {
            texttranslated= Translate.execute(texttotranslate, Language.AUTO_DETECT, com.memetix.mst.language.Language.ROMANIAN);
            texttranslated1 = Translate.execute(texttotranslate, Language.AUTO_DETECT, com.memetix.mst.language.Language.HINDI);
            texttranslated2 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.ARABIC);
            texttranslated3 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.BULGARIAN);
            texttranslated4 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.CATALAN);
            texttranslated5 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.DUTCH);
            texttranslated6 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.ENGLISH);
            texttranslated7 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.FRENCH);
            texttranslated8 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.GERMAN);
            texttranslated9 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.HUNGARIAN);
            texttranslated10 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.ITALIAN);
            texttranslated11 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.JAPANESE);
            texttranslated12 = Translate.execute(texttotranslate, Language.AUTO_DETECT, Language.KOREAN);
            text1translated= Translate.execute(texttotranslate2, Language.AUTO_DETECT, com.memetix.mst.language.Language.ROMANIAN);
            text1translated1 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, com.memetix.mst.language.Language.HINDI);
            text1translated2 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.ARABIC);
            text1translated3 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.BULGARIAN);
            text1translated4 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.CATALAN);
            text1translated5 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.DUTCH);
            text1translated6 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.ENGLISH);
            text1translated7 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.FRENCH);
            text1translated8 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.GERMAN);
            text1translated9 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.HUNGARIAN);
            text1translated10 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.ITALIAN);
            text1translated11 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.JAPANESE);
            text1translated12 = Translate.execute(texttotranslate2, Language.AUTO_DETECT, Language.KOREAN);
            //  texttranslated13 = Translate.execute(texttotranslate, Language.AUTO_DETECT, com.memetix.mst.language.Language.HINDI);

        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseReference databaseReference2= databaseReference.child("ro").child("");
        databaseReference2.child("user").setValue(user);
        databaseReference2.child("title").setValue(texttranslated);
        databaseReference2.child("desc").setValue(text1translated);
        DatabaseReference databaseReference3= databaseReference.child("hi").push();
        databaseReference3.child("user").setValue(user);
        databaseReference3.child("title").setValue(texttranslated1);
        databaseReference3.child("desc").setValue(text1translated1);
        DatabaseReference databaseReference4= databaseReference.child("ar").push();
        databaseReference4.child("user").setValue(user);
        databaseReference4.child("title").setValue(texttranslated2);
        databaseReference4.child("desc").setValue(text1translated2);
        DatabaseReference databaseReference5= databaseReference.child("bg").push();
        databaseReference5.child("user").setValue(user);
        databaseReference5.child("title").setValue(texttranslated3);
        databaseReference5.child("desc").setValue(text1translated3);
        DatabaseReference databaseReference6= databaseReference.child("ca").push();
        databaseReference6.child("user").setValue(user);
        databaseReference6.child("title").setValue(texttranslated4);
        databaseReference6.child("desc").setValue(text1translated4);
        DatabaseReference databaseReference7= databaseReference.child("nl").push();
        databaseReference7.child("user").setValue(user);
        databaseReference7.child("title").setValue(texttranslated5);
        databaseReference7.child("desc").setValue(text1translated5);
        DatabaseReference databaseReference8= databaseReference.child("en").push();
        databaseReference8.child("user").setValue(user);
        databaseReference8.child("title").setValue(texttranslated6);
        databaseReference8.child("desc").setValue(text1translated6);
        DatabaseReference databaseReference9= databaseReference.child("fr").push();
        databaseReference9.child("user").setValue(user);
        databaseReference9.child("title").setValue(texttranslated7);
        databaseReference9.child("desc").setValue(text1translated7);
        DatabaseReference databaseReference10= databaseReference.child("de").push();
        databaseReference10.child("user").setValue(user);
        databaseReference10.child("title").setValue(texttranslated8);
        databaseReference10.child("desc").setValue(text1translated8);
        DatabaseReference databaseReference11= databaseReference.child("hu").push();
        databaseReference11.child("user").setValue(user);
        databaseReference11.child("title").setValue(texttranslated9);
        databaseReference11.child("desc").setValue(text1translated9);
        DatabaseReference databaseReference12= databaseReference.child("it").push();
        databaseReference12.child("user").setValue(user);
        databaseReference12.child("title").setValue(texttranslated10);
        databaseReference12.child("desc").setValue(text1translated10);
        DatabaseReference databaseReference13= databaseReference.child("ja").push();
        databaseReference13.child("user").setValue(user);
        databaseReference13.child("title").setValue(texttranslated11);
        databaseReference13.child("desc").setValue(text1translated11);
        DatabaseReference databaseReference14= databaseReference.child("ko").push();
        databaseReference14.child("user").setValue(user);
        databaseReference14.child("title").setValue(texttranslated12);
        databaseReference14.child("desc").setValue(text1translated12);





        System.out.println(translatedText);
        System.out.print("sddddddddddddddddddddddddddd"+texttranslated);

        return texttranslated;
    }

    protected void onPostExecute(String s) {
        //   TextView textView=(TextView)view.findViewById(R.id.translatedtext);
        // textView.setText(s);
    }



}

