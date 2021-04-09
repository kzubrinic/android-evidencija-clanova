package hr.unidu.kruno.zadatak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.gson.Gson;

import java.util.List;

public class PregledActivity extends AppCompatActivity implements OnRowClickInterface{
    private Context c;
    private PregledAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled);
        Intent i = getIntent();
        c = this;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Spremiste sp = new Spremiste(this);
        List<Clan> lista = sp.procitajListu();
        // specify an adapter (see also next example)
        mAdapter = new PregledAdapter(lista, c, this);
        recyclerView.setAdapter(mAdapter);
            }

    @Override
    public void onRowClick(Clan clan) {
        Intent i = new Intent();
        Gson g = new Gson();
        String pov = g.toJson(clan);
        i.putExtra("povratnaPoruka", pov);
        setResult(Activity.RESULT_OK, i);
        // završetak - potvrda
        finish();
    }
}