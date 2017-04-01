package neuromancers.assignament1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Declaring the textviews
    TextView newOrder,orderPlaced;
    String name[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int qty[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int price[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle b = getIntent().getExtras();

        if(b!=null) {
            name = b.getStringArray("name");
            qty=b.getIntArray("qty");
            price=b.getIntArray("price");
            //Toast.makeText(getApplicationContext(), "Order placed", Toast.LENGTH_SHORT).show();
        }
        newOrder = (TextView)findViewById(R.id.newOrder);
        orderPlaced=(TextView)findViewById(R.id.orderPlaced);


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



    }
}
