package goncinha.energ;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private List<Bacon> Lista_bacons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populatebacons();
        populateListView();
        registerClickCallback();
        fabfunc();
    }
    private void fabfunc() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Criando Bacon...",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void registerClickCallback() {
        final ListView list = (ListView) findViewById(R.id.Bacons);
        if( list == null) Toast.makeText(getApplicationContext(),
                "Lista vazia",Toast.LENGTH_SHORT).show();
        assert list !=null;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Bacon clickedBacon = Lista_bacons.get(position);
                if(clickedBacon.getOnoff() == R.drawable.on){
                    clickedBacon.setOnoff(R.drawable.off);
                    populateListView();
                    //sendoff();    //funcao pra mandar sinal de off pro ESP
                }
                else {
                    clickedBacon.setOnoff(R.drawable.on);
                    populateListView();
                    //sendon();     //funcao pra mandar sinal de on pro ESP
                }
                
            }
        });

    }

    private void populateListView() {
        ArrayAdapter <Bacon> adapter= new myListadapter();
        ListView list = (ListView) findViewById(R.id.Bacons);
        list.setAdapter(adapter);
    }

    private class myListadapter extends ArrayAdapter <Bacon>{
        public myListadapter(){
            super(Main.this,R.layout.activity_main,Lista_bacons);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.baconcell,parent,false);
            }
            Bacon currentBacon = Lista_bacons.get(position);

            ImageView imageview = (ImageView) itemView.findViewById(R.id.baconicon);
            imageview.setImageResource(currentBacon.getIcon());
            TextView textview = (TextView) itemView.findViewById(R.id.baconname);
            textview.setText(currentBacon.getName());
            ImageView imageview2 = (ImageView) itemView.findViewById(R.id.onoff);
            if(currentBacon.getOnoff()== R.drawable.off) {
                imageview2.setImageResource(R.drawable.off);
            }
            else{
                imageview2.setImageResource(R.drawable.on);
            }
            final TextView textview2 = (TextView) itemView.findViewById(R.id.bacondisp);
            if(currentBacon.getDisponibilidade()== "on") {
                textview2.setText("Online");
            }
            else{
                textview2.setText("Offline");
            }

            return itemView;
        }


    }
    private void populatebacons(){
        Lista_bacons.add(new Bacon("Bacon1","on",R.drawable.off,R.drawable.geladeira,"1"));
        Lista_bacons.add(new Bacon("Bacon2","on",R.drawable.on,R.drawable.laptop,"2"));
    }
}
