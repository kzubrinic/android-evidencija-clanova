package hr.unidu.kruno.zadatak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferencesFactory;

public class MainActivity extends AppCompatActivity {
    private EditText ime, adresa, oib, telefon;
    private CheckBox privola;
    private RadioButton pM, pG;
    private List<Clan> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ime = findViewById(R.id.ime);
        adresa = findViewById(R.id.adresa);
        oib = findViewById(R.id.oib);
        telefon = findViewById(R.id.telefon);
        privola = findViewById(R.id.privola);
        pM = findViewById(R.id.plMjesecno);
        pG = findViewById(R.id.plGodisnje);
    }

    public void pregled(View view) {
        Intent i = new Intent(this,PregledActivity.class);
        startActivityForResult(i, 1);
    }

    public void spremi(View view) {
        if(!zapisOk()){
            Toast.makeText(this,"Morate izabrati način plaćanja članarine i dati privolu na korištenje podataka!",Toast.LENGTH_LONG).show();
            return;
        }
        Spremiste sp = new Spremiste(this);
        int clanarina = (pM.isChecked()) ? 1 : 2;
        Clan c = new Clan(ime.getText().toString(), oib.getText().toString(),
                adresa.getText().toString(), telefon.getText().toString(),
                clanarina, privola.isChecked());
        sp.spremiListu(c);
        Toast.makeText(this,"Spremanje uspješno!",Toast.LENGTH_LONG).show();
    }
    public void obrisi(View view) {
        Spremiste sp = new Spremiste(this);
        int clanarina = (pM.isChecked()) ? 1 : 2;
        Clan c = new Clan(ime.getText().toString(), oib.getText().toString(),
                adresa.getText().toString(), telefon.getText().toString(),
                clanarina, privola.isChecked());
        if(sp.ukloniIzListe(c)) {
            Toast.makeText(this, "Brisanje uspješno!", Toast.LENGTH_LONG).show();
            obrisiPolja();
        }else{
            Toast.makeText(this,"Član nije obrisan jer ne postoji u listi!",Toast.LENGTH_LONG).show();
        }
    }
    private boolean zapisOk(){
        if(privola.isChecked() && (pM.isChecked() || pG.isChecked()))
            return true;
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Provjerava je li druga aktivnost uspješno završila
        // Služi za identifikaciju određene aktivnosti
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // dohvaća podatke koje je u poslani objekt tipa Intent dodala pozvana aktivnost
                String povratna = data.getStringExtra("povratnaPoruka");
                Gson g = new Gson();
                Type tip = new TypeToken<Clan>(){}.getType();
                Clan pov = g.fromJson(povratna, tip);
                ime.setText(pov.getIme());
                adresa.setText(pov.getAdresa());
                oib.setText(pov.getOib());
                telefon.setText(pov.getTelefon());
                if (pov.getClanarina() == 1) {
                    pM.setChecked(true);
                    pG.setChecked(false);
                }else{
                    pM.setChecked(false);
                    pG.setChecked(true);
                }
                privola.setChecked(pov.isPrivola());
            }
        }
    }
    private void obrisiPolja(){
        ime.setText("");
        adresa.setText("");
        oib.setText("");
        telefon.setText("");
        pM.setChecked(false);
        pG.setChecked(false);
        privola.setChecked(false);
        ime.requestFocus();
    }
}