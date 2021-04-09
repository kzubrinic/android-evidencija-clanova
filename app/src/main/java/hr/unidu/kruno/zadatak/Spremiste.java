package hr.unidu.kruno.zadatak;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Spremiste {
    Context c;
    public Spremiste(Context c){
        this.c = c;
    }
    public List<Clan> procitajListu(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        // 1. dohvati JSON string iz spremišta
        String string = sp.getString("lista","");
        Gson g = new Gson();
        // 2. Deserijaliziraj JSON->Java objekt
        Type tipListe = new TypeToken<ArrayList<Clan>>(){}.getType();
        // taj tip se navodi kao drugi parametar kod serijalizacije i deserijalizacije
        List<Clan> lista = g.fromJson(string, tipListe);
        // Ako je spremište porazno, lista je null pa stvaramo praznu listu
        if (lista == null) lista = new ArrayList<Clan>();
        return lista;
    }
    public void spremiListu(Clan cl){
        List<Clan> lista = procitajListu();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        Gson g = new Gson();
        Type tipListe = new TypeToken<ArrayList<Clan>>(){}.getType();
        int poz = lista.indexOf(cl);
        if(poz<0)
            lista.add(cl);
        else
            lista.set(poz, cl);
        String nova = g.toJson(lista, tipListe);
        // 5. spremi JSON string u spremište
        SharedPreferences.Editor e = sp.edit();
        e.putString("lista", nova);
        e.apply();
    }
    public boolean ukloniIzListe(Clan cl){
        List<Clan> lista = procitajListu();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        Gson g = new Gson();
        Type tipListe = new TypeToken<ArrayList<Clan>>(){}.getType();
        int poz = lista.indexOf(cl);
        if(poz<0)
            return false;
        else
            lista.remove(poz);
        String nova = g.toJson(lista, tipListe);
        // 5. spremi JSON string u spremište
        SharedPreferences.Editor e = sp.edit();
        e.putString("lista", nova);
        e.apply();
        return true;
    }
}
