package neuromancers.assignament1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Category extends AppCompatActivity implements View.OnClickListener {

    TextView cat1,cat2;
    LeafyDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        cat1=(TextView)findViewById(R.id.cat1);
        cat2=(TextView)findViewById(R.id.cat2);

        cat1.setOnClickListener(this);
        cat2.setOnClickListener(this);
        //this constructor creates the data base in our phone
        helper=new LeafyDBHelper(this);
        //delete all data previously present in the table so that repetion doesn't occur
        helper.deleteAll();
        //inserting the data in the database
        for(int i=1;i<=100;++i){
            int randprice=(int )(Math. random() * 100 + 1);

            if(i<=17)
                helper.insertData("A"+i,"1","A",Integer.toString(randprice),"0");
            else if(i<=34)
                helper.insertData("A"+i,"2","A",Integer.toString(randprice),"0");
            else if(i<=17*3)
                helper.insertData("A"+i,"1","B",Integer.toString(randprice),"0");
            else if(i<=17*4)
                helper.insertData("A"+i,"2","B",Integer.toString(randprice),"0");
            else if(i<=17*5)
                helper.insertData("A"+i,"1","C",Integer.toString(randprice),"0");
            else
                helper.insertData("A"+i,"2","C",Integer.toString(randprice),"0");

        }
    }

    @Override
    public void onClick(View view) {
        String message=null;
        Intent intent=new Intent(Category.this,TabActivity.class);
        switch(view.getId()){
            case R.id.cat1:
                message="1";
                intent.putExtra("message", message);
                startActivity(intent);
                break;
            case R.id.cat2:
                message="2";
                intent.putExtra("message", message);
                startActivity(intent);
                break;
        }
    }
}
