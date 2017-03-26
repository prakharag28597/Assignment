package neuromancers.assignament1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by prakharag on 23-03-2017.
 */

public class OrderPlaced extends AppCompatActivity {

    TextView totaltext;
    String name[]=new String[26];
    int qty[]=new int[26];
    int price[]=new int[26];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_placed);
        totaltext=(TextView)findViewById(R.id.total);
        ScrollView sv=(ScrollView)findViewById(R.id.sv);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        Bundle b = getIntent().getExtras();

        if(b!=null) {
            name = b.getStringArray("name");
            qty=b.getIntArray("qty");
            price=b.getIntArray("price");
            //Toast.makeText(getApplicationContext(), name[2], Toast.LENGTH_SHORT).show();
        }
        //iterating through each item
        int total=0;
        for(int i=0;i<26;++i){
            total+=qty[i]*price[i];
            if(qty[i]>0){
                LinearLayout l2 = new LinearLayout(this);
                l2.setOrientation(LinearLayout.HORIZONTAL);
                ll.addView(l2);
                TextView nametext = new TextView(this);
                nametext.setTextSize(20);
                nametext.setText("          "+name[i]+"              ");
                l2.addView(nametext);
                TextView pricetext = new TextView(this);
                pricetext.setTextSize(20);
                pricetext.setText("         "+Integer.toString(price[i])+"             ");
                l2.addView(pricetext);
                TextView qtytext = new TextView(this);
                qtytext.setTextSize(20);
                qtytext.setText("           "+Integer.toString(qty[i]));
                l2.addView(qtytext);


            }
        }
        totaltext.setText(Integer.toString(total));

    }
}