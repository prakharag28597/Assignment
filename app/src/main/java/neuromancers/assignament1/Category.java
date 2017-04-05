package neuromancers.assignament1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Category extends AppCompatActivity implements View.OnClickListener {

    TextView cat1,cat2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        cat1=(TextView)findViewById(R.id.cat1);
        cat2=(TextView)findViewById(R.id.cat2);

        cat1.setOnClickListener(this);
        cat2.setOnClickListener(this);
        //this constructor creates the data base in our phone

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
