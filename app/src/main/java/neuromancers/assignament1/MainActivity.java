package neuromancers.assignament1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Declaring the textviews
    TextView newOrder,orderPlaced;
    Button clear;
    LeafyDBHelper helper;
    //String name[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    //int qty[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    //int price[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Bundle b = getIntent().getExtras();

        if(b!=null) {
            name = b.getStringArray("name");
            qty=b.getIntArray("qty");
            price=b.getIntArray("price");
            //Toast.makeText(getApplicationContext(), "Order placed", Toast.LENGTH_SHORT).show();
        }*/
        newOrder = (TextView)findViewById(R.id.newOrder);
        orderPlaced=(TextView)findViewById(R.id.orderPlaced);
        clear=(Button)findViewById(R.id.clearb);

        helper=new LeafyDBHelper(this);
        //delete all data previously present in the table so that repetion doesn't occur
        //helper.reset();
        Cursor cursor=helper.getAllData();
        if(cursor.getCount()==0) {
            //helper.deleteAll();
            //inserting the data in the database
            for (int i = 1; i <= 100; ++i) {
                int randprice = (int) (Math.random() * 100 + 1);

                if (i <= 17) {
                    helper.insertData("A" + i, "1", "A", Integer.toString(randprice), "0");
                    Log.e("yo", "" + i);
                } else if (i <= 34) {
                    helper.insertData("A" + i, "2", "A", Integer.toString(randprice), "0");
                    Log.e("yo", "" + i);
                } else if (i <= 17 * 3) {
                    helper.insertData("A" + i, "1", "B", Integer.toString(randprice), "0");
                    Log.e("yo", "" + i);
                } else if (i <= 17 * 4) {
                    helper.insertData("A" + i, "2", "B", Integer.toString(randprice), "0");
                    Log.e("yo", "" + i);
                } else if (i <= 17 * 5) {
                    helper.insertData("A" + i, "1", "C", Integer.toString(randprice), "0");
                    Log.e("yo", "" + i);
                } else {
                    helper.insertData("A" + i, "2", "C", Integer.toString(randprice), "0");
                    Log.e("yo", "" + i);
                }

            }
        }
        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderPlaced.setClickable(true);
                Intent intent=new Intent(MainActivity.this,Category.class);
                startActivity(intent);
            }
        });
        orderPlaced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,OrderPlaced.class);
                startActivity(intent);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=1;i<=100;++i){
                    helper.updateQty(""+i,"0");
                }
                Toast.makeText(getApplicationContext(),"Deletion Successful!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
