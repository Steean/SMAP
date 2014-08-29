package dk.stiandahl.exercise2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class part_b extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_b);

        final ImageButton button1 = (ImageButton) findViewById(R.id.btn1);
        final ImageButton button2 = (ImageButton) findViewById(R.id.btn2);

        button1.setImageResource(R.drawable.funny_baby1);
        button1.setTag(1);
        button2.setImageResource(R.drawable.funny_baby2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwapImages(button1, button2);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwapImages(button1, button2);
            }
        });

    }

    public void SwapImages(ImageButton btn1, ImageButton btn2) {
        if(btn1.getTag().equals(1)) {
            btn1.setImageResource(R.drawable.funny_baby2);
            btn2.setImageResource(R.drawable.funny_baby1);
            btn1.setTag(2);
        }
        else {
            btn1.setImageResource(R.drawable.funny_baby1);
            btn2.setImageResource(R.drawable.funny_baby2);
            btn1.setTag(1);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_part_a) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            return true;
        }
        else if (id == R.id.action_part_b) {
            Intent myIntent = new Intent(this, part_b.class);
            startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
