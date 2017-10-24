package com.solly74.spongemytext;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    String mainText = "";
    EditText myTextView;
    EditText myDisplayText;
    Button btnProcess;
    ImageButton btnCopy;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = (EditText) findViewById(R.id.txtInputText);
        myDisplayText = (EditText) findViewById(R.id.txtDisplayText);
        btnProcess = (Button) findViewById(R.id.btnSponge);
        btnCopy = (ImageButton) findViewById(R.id.btnCopy);

        btnProcess.setOnClickListener(this);
        btnCopy.setOnClickListener(this);

        Log.d(TAG, "onCreate: it is " + new emailValidator().validateEmail("sdfsdf") );

    }


    private Dictionary<Integer, String> collectText(String enteredText) {

        String[] processedText = enteredText.split(" ");

        Dictionary<Integer, String> myCollection = new Hashtable<>();

        int counter = 1;

        for (String theText : processedText) {

            myCollection.put(counter, theText);

            counter++;

        }

        displayNewText(myCollection);

        return myCollection;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSponge) {
            mainText = myTextView.getText().toString();

            collectText(mainText);
        } else if (v.getId() == R.id.btnCopy) {

            String copyText = myDisplayText.getText().toString();

            if (!copyText.isEmpty()) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("copyText", copyText);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(v.getContext(), "Text copied...", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(v.getContext(), "No results to copy...", Toast.LENGTH_SHORT).show();
            }


        }


    }

    private void displayNewText(Dictionary<Integer, String> collection) {

        EditText displayText = (EditText) findViewById(R.id.txtDisplayText);

        StringBuilder newValue = new StringBuilder();

        int maxItemCount = collection.size();
        for (int x = 1; x <= maxItemCount; x++) {
            newValue.append(processWord(collection.get(x).toString()) + " ");
        }

        displayText.setText(newValue);


    }

    @NonNull
    private String processWord(String word) {

        StringBuilder newWord = new StringBuilder();

        int wordLength = word.length();

        boolean previousCharUpper = false;

        if (wordLength > 1) {

            for (int x = 0; x < wordLength; x++) {

                Character myChar = Character.toLowerCase(word.charAt(x));

                if (x == 0) {
                    newWord.append(Character.toLowerCase(myChar));
                } else if (isVowel(myChar)) {

                    if (x == wordLength - 1) {
                        newWord.append(Character.toLowerCase(myChar));
                        previousCharUpper = false;
                    } else {
                        newWord.append(Character.toUpperCase(myChar));
                        previousCharUpper = true;
                    }


                } else if (isSpecialVowel(myChar)) {
                    newWord.append(Character.toLowerCase(myChar));
                    previousCharUpper = false;
                } else {

                    if (x % 2 != 0) {
                        if (!previousCharUpper) {
                            newWord.append(Character.toUpperCase(myChar));
                            previousCharUpper = true;
                        } else {
                            newWord.append(Character.toLowerCase(myChar));
                            previousCharUpper = false;
                        }

                    } else {
                        if (!previousCharUpper) {
                            newWord.append(Character.toUpperCase(myChar));
                            previousCharUpper = true;
                        } else {
                            newWord.append(Character.toLowerCase(myChar));
                            previousCharUpper = false;
                        }

                    }


                }

            }

        } else {
            newWord.append(word.toLowerCase());
        }

        return newWord.toString();
    }

    private boolean isVowel(Character myChar) {
        boolean isVowel = false;

        String[] vowels = getResources().getStringArray(R.array.upper_vowels);

        for (String vowel : vowels) {
            if (myChar.equals(vowel.charAt(0))) {
                isVowel = true;
            }

        }

        return isVowel;
    }

    private boolean isSpecialVowel(Character myChar) {
        boolean isSpecialVowel = false;

        String[] vowels = getResources().getStringArray(R.array.lower_vowels);

        for (String vowel : vowels) {
            if (myChar.equals(vowel.charAt(0))) {
                isSpecialVowel = true;
            }

        }

        return isSpecialVowel;
    }

}
