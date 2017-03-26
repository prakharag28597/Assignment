package neuromancers.assignament1;

/**
 * Created by prakharag on 24-03-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class NewOrder extends AppCompatActivity{
    //declaring the price and name array
    String name[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int qty[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int price[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neworder);
        ListView listview=(ListView)findViewById(R.id.listview);
        Button menu=(Button)findViewById(R.id.menubutton);
        //randomly generating price for 26 items(price between 1 to 100)
        for(int i=0;i<price.length;++i){
            price[i]=(int )(Math. random() * 100 + 1);
        }
        //setting up the custom adapter
        CustomAdapter customAdapter=new CustomAdapter();
        listview.setAdapter(customAdapter);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"Order stored",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewOrder.this, MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putStringArray("name",name);
                bundle.putIntArray("qty",qty);
                bundle.putIntArray("price",price);
                intent.putExtras(bundle);
                startActivity(intent);
            }


        });


    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //inflating the layout
            view=getLayoutInflater().inflate(R.layout.listview,null);
            //setting up the buttons and text views
            TextView priceText=(TextView)view.findViewById(R.id.pricetext);
            TextView nameText=(TextView)view.findViewById(R.id.nametext);
            Button add=(Button)view.findViewById(R.id.add);
            Button sub=(Button)view.findViewById(R.id.sub);
            //using the view and variable in another class so have to declare it final
            final TextView qtyText=(TextView)view.findViewById(R.id.qtytext);
            qtyText.setText(Integer.toString(qty[i]));

            final int pos=i;
            //listener for add button
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++qty[pos];
                    qtyText.setText(Integer.toString(qty[pos]));

                }
            });
            //listener for subtract button
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(qty[pos]>0)
                    --qty[pos];
                    qtyText.setText(Integer.toString(qty[pos]));
                }
            });
            //setting up the price and name of the item
            nameText.setText(name[i]);
            priceText.setText(Integer.toString(price[i]));
            return view;
        }


    }
}