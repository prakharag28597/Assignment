package neuromancers.assignament1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by prakharag on 31-03-2017.
 */

public class Tab1 extends Fragment {
    int category;
    View rootView;
    ListView listA;
    LayoutInflater inf;
    LeafyDBHelper helper;
    String name[];
    String cat[];
    String subcat[];
    String price[];
    String qty[];
    Button add,sub;
    Cursor cursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inf=inflater;
        rootView = inflater.inflate(R.layout.tab1, container, false);
        listA=(ListView)rootView.findViewById(R.id.listA);
        helper=new LeafyDBHelper(getContext());
        cursor=helper.getAllData();
        int i;
        name=new String[100];
        price=new String[100];
        qty=new String[100];
        cat=new String[100];
        subcat=new String[100];
        //initiallizing the arrays to 0
        for(i=0;i<100;++i) {
            name[i] = null;
            price[i] = null;
            cat[i] = null;
            qty[i] = null;
            subcat[i] = null;
        }
        if(cursor.getCount()==0)
            Toast.makeText(getContext(),"ERROR",Toast.LENGTH_SHORT).show();
        //inserting data into the array
        else{
            i=0;

           //
            // Toast.makeText(getContext(),""+name[0],Toast.LENGTH_SHORT).show();
            while(cursor.moveToNext() && i<100){
                name[i]=cursor.getString(1);
                cat[i]=cursor.getString(2);
                subcat[i]=cursor.getString(3);
                price[i]=cursor.getString(4);
                qty[i]=cursor.getString(5);
                ++i;
            }

        }
        Customadapter cust=new Customadapter();
        listA.setAdapter(cust);
        return rootView;
    }
    public void setCat(int cat){

        category=cat;
    }
    public class Customadapter extends BaseAdapter{
        //denotes the entries which are of particular categories and subcategories
        String n[];
        String q[];
        String p[];
        String c[];
        String sc[];
        @Override
        public int getCount() {
            int count=0;
            n=new String[100];
            q=new String[100];
            p=new String[100];
            c=new String[100];
            sc=new String[100];
            for(int i=0;i<100;++i) {
                n[i] = null;
                q[i] = null;
                p[i] = null;
                c[i] = null;
                sc[i]= null;
            }
            for(int i=0;i<100;++i) {
                if (cat[i].equals(Integer.toString(category)) && subcat[i].equals("A")) {

                    n[count] = name[i];
                    q[count] = qty[i];
                    p[count] = price[i];
                    c[count] = cat[i];
                    sc[count] = subcat[i];
                    ++count;
                }
            }
            Log.e("getcount",""+count);

            return count;

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
            //setting up the views in listview
            Log.e("getview",""+i);
            view=((Activity)getContext()).getLayoutInflater().inflate(R.layout.listview,null);
            TextView priceText=(TextView)view.findViewById(R.id.pricetext);
            TextView nameText=(TextView)view.findViewById(R.id.nametext);
            final TextView qtyText=(TextView)view.findViewById(R.id.qtytext);

            add=(Button)view.findViewById(R.id.add);
            sub=(Button)view.findViewById(R.id.sub);

            nameText.setText(n[i]);
            priceText.setText(p[i]);
            qtyText.setText(q[i]);
            final int pos=i;


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("position pressed",""+pos);
                    int temp=Integer.parseInt(q[pos]);
                    ++temp;

                    q[pos]=Integer.toString(temp);
                    Log.e("temp"," "+q[pos]);
                    qtyText.setText(q[pos]);
                    int c=0;
                    cursor.moveToFirst();
                   while( c<100){
                       if(cursor.getString(1).equals(n[pos]))
                       {
                           Log.e("name",cursor.getString(1)+" "+c);
                           helper.updateData(Integer.toString(c+1),n[pos],q[pos],p[pos]);
                           break;
                       }
                       cursor.moveToNext();
                               ++c;
                   }
                }
            });

            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int temp=Integer.parseInt(q[pos]);
                    if(temp>0){
                        --temp;
                        q[pos]=Integer.toString(temp);
                        int c=0;
                        cursor.moveToFirst();
                        while(cursor.moveToNext() && c<100){
                            if(cursor.getString(1).equals(n[pos]))
                            {
                                helper.updateData(Integer.toString(c+1),n[pos],q[pos],p[pos]);
                                break;
                            }
                            ++c;
                        }
                    }
                    qtyText.setText(q[pos]);
                }
            });
            return view;
        }
    }

}

