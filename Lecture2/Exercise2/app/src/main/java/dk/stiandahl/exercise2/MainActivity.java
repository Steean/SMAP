package dk.stiandahl.exercise2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {

    boolean checkImage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void doSwap(View view) {
        ImageView view1 = (ImageView) findViewById(R.id.img1);
        ImageView view2 = (ImageView) findViewById(R.id.img2);

        if(checkImage) {
            view1.setImageResource(R.drawable.funny_baby2);
            view2.setImageResource(R.drawable.funny_baby1);

            checkImage = false;
        }

        else if(!checkImage) {
            view1.setImageResource(R.drawable.funny_baby1);
            view2.setImageResource(R.drawable.funny_baby2);

            checkImage = true;
        }

    }
}
