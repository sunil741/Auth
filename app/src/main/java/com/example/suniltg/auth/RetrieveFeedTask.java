package com.example.suniltg.auth;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by sunil TG on 10/11/2016.
 */
class RetrieveFeedTask extends AsyncTask<String, String, String> {

    private Exception exception;
    String user;
    String texttotranslate;
    Context context;
    String texttranslated="",texttranslated1="",texttranslated2="",texttranslated3="",texttranslated4="",texttranslated5="",texttranslated6="",texttranslated7="",texttranslated8="",texttranslated9="",texttranslated10="",texttranslated11="",texttranslated12="";
    View view;
    String locale;
    DatabaseReference databaseReference;
    String type;

    public RetrieveFeedTask(String user,String type, String ttt, DatabaseReference databaseReference, String locale) {
        texttotranslate = ttt;
        this.user=user;
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
*/Translate.setClientId("SunilWithPucho7");
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
             //  texttranslated13 = Translate.execute(texttotranslate, Language.AUTO_DETECT, com.memetix.mst.language.Language.HINDI);

           } catch (Exception e) {
               e.printStackTrace();
           }
          DatabaseReference databaseReference2= databaseReference.child("ro").push();
           databaseReference2.child(type).setValue(texttranslated);
           DatabaseReference databaseReference3= databaseReference.child("hi").push();
            databaseReference3.child(type).setValue(texttranslated1);
           DatabaseReference databaseReference4= databaseReference.child("ar").push();
           databaseReference4.child(type).setValue(texttranslated2);
           DatabaseReference databaseReference5= databaseReference.child("bg").push();
           databaseReference5.child(type).setValue(texttranslated3);
           DatabaseReference databaseReference6= databaseReference.child("ca").push();
           databaseReference6.child(type).setValue(texttranslated4);
           DatabaseReference databaseReference7= databaseReference.child("nl").push();
           databaseReference7.child(type).setValue(texttranslated5);
           DatabaseReference databaseReference8= databaseReference.child("en").push();
           databaseReference8.child(type).setValue(texttranslated6);
           DatabaseReference databaseReference9= databaseReference.child("fr").push();
           databaseReference9.child(type).setValue(texttranslated7);
           DatabaseReference databaseReference10= databaseReference.child("de").push();
           databaseReference10.child(type).setValue(texttranslated8);
           DatabaseReference databaseReference11= databaseReference.child("hu").push();
           databaseReference11.child(type).setValue(texttranslated9);
           DatabaseReference databaseReference12= databaseReference.child("it").push();
           databaseReference12.child(type).setValue(texttranslated10);
           DatabaseReference databaseReference13= databaseReference.child("ja").push();
           databaseReference13.child(type).setValue(texttranslated11);
           DatabaseReference databaseReference14= databaseReference.child("ko").push();
           databaseReference14.child(type).setValue(texttranslated12);





           System.out.println(translatedText);
               System.out.print("sddddddddddddddddddddddddddd"+texttranslated);

           return texttranslated;
       }

    protected void onPostExecute(String s) {
     //   TextView textView=(TextView)view.findViewById(R.id.translatedtext);
       // textView.setText(s);
    }



}
