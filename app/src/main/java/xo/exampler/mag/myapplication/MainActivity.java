package xo.exampler.mag.myapplication;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import xo.exampler.mag.myapplication.R;

import static xo.exampler.mag.myapplication.R.id.Singlep;
import static xo.exampler.mag.myapplication.R.id.info;
import static xo.exampler.mag.myapplication.R.id.quanity;

public class MainActivity extends AppCompatActivity {
    TextView quantity;
    public int numberOfCoffes=1;
    public int price=15;
    int Total;
    Button confirm;
    TextView single,summaryText;
    CheckBox choco, cream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = (TextView) findViewById(R.id.quanity);
        confirm = (Button) findViewById(R.id.odern);
        single = (TextView) findViewById(Singlep);
        choco = (CheckBox) findViewById(R.id.chocolate);
        cream = (CheckBox)findViewById(R.id.cream);
        summaryText = (TextView)findViewById(R.id.summary);
    }

    /**Adds the Number Of coffees By 1
     *
     * @param view View PAssed From XML
     */
    public void AddOne(View view) {
        numberOfCoffes++;
        quantity.setText(" "+numberOfCoffes);

    }

    /**Subracts the Number Of cups By 1
     * here we check the wheather the Number Of Cups is 1 or Less Than If It is Lesser Than 1
     * then Decrementing Will lead to Conflict, So If....else Is Used To Check this.
     * @param v View variable
     */
    public void lessOne(View v){
        if(numberOfCoffes == 0){
            Toast info = Toast.makeText(getApplicationContext(),"dude, It cam't be -ve",Toast.LENGTH_SHORT);
            info.show();
        }else {
            numberOfCoffes--;
            quantity.setText(" " + numberOfCoffes);
        }
    }

    /**
     * When The order Button Is Clicked It makes The Order Now TO Be Visble
     * Price is Calculated Based On The Toppings ANd Quanitity
     * @param vps View That is passed By Onclick IN XML
     */
    public void Ordered(View vps){
        single.setText(""+price);
        EditText Name = (EditText)findViewById(R.id.userName);
        EditText mail = (EditText)findViewById(R.id.userMail);
        EditText num = (EditText)findViewById(R.id.userNumber);
        summaryText.setVisibility(View.VISIBLE);
        summaryText.setText(getString(R.string.ordersumma));
        if(choco.isChecked()){
            this.price += 5;
            Total = price*numberOfCoffes;
            String Summary = "Name :" + Name.getText() +"\n"+"Mail Id :"+ mail.getText() + "\n" + "Contact Number :"+num.getText()+"\n"+"Cost :₹ "+Total+ "\n" +"Toppings : Yes, Chocolate";
            summaryText.setText(Summary);
        }
        else if(cream.isChecked()){
            price += 4;
            Total = price*numberOfCoffes;
            String Summary = "Name :" + Name.getText()+ "\n"+"Mail Id :"+mail.getText()+"\n"+"Contact Number :"+num.getText()+"\n"+"Cost : ₹ "+Total+"\n"+"Toppings : Yes, Cream";
            summaryText.setText(Summary);
        }
        else if(choco.isChecked()&&cream.isChecked()){
            price += 9;
            Total = price*numberOfCoffes;
            String Summary = "Name :"+Name.getText()+"\n"+"Mail Id :"+mail.getText()+"\n"+"Contact Number :"+num.getText()+"\n"+"Cost : ₹ "+Total+"\n"+"Toppings : Yes Both Chocolate And Cream";
            summaryText.setText(Summary);
        }
        else {

            Total = price*numberOfCoffes;
            String Summary = "Name :"+Name.getText()+"\n"+"Mail Id :"+mail.getText()+"\n"+"Contact Number :"+num.getText()+"\n"+"Cost : ₹ " + Total + "\nToppings : None \n";
            summaryText.setText(Summary);

        }
        confirm.setVisibility(View.VISIBLE);
    }

    /**
     * THis method is Used To pass Intent
     * Here We Use E-mail Intent
     * @param view Passed Via XML
     */
    public void Jumpoff(View view) {
        EditText Name = (EditText)findViewById(R.id.userName);
        EditText mail = (EditText)findViewById(R.id.userMail);
        EditText num = (EditText)findViewById(R.id.userNumber);
        summaryText.setVisibility(View.VISIBLE);
        summaryText.setText(getString(R.string.ordersumma));
        final Intent jump = new Intent(Intent.ACTION_SENDTO);
        jump.setData(Uri.parse("mailto:"));
        jump.putExtra(Intent.EXTRA_EMAIL, "mageshmani620@gmail.com");
        jump.putExtra(Intent.EXTRA_SUBJECT, "Hello Just Testing");
        if(choco.isChecked()){
            price=+5;
            Total = price*numberOfCoffes;
            String Summary = "Name :" + Name.getText() +"\n"+"Mail Id :"+ mail.getText() + "\n" + "Contact Number :"+num.getText()+"\n"+"Cost : ₹ "+Total+ "\n" +"Toppings : Yes, Chocolate";
            jump.putExtra(Intent.EXTRA_TEXT, Summary);
        }
        else if(cream.isChecked()){
            price=+4;
            Total = price*numberOfCoffes;
            String Summary = "Name :" + Name.getText()+ "\n"+"Mail Id :"+mail.getText()+"\n"+"Contact Number :"+num.getText()+"\n"+"Cost : ₹ "+Total+"\n"+"Toppings : Yes, Cream";
            jump.putExtra(Intent.EXTRA_TEXT, Summary);
        }
        else if(choco.isChecked()&&cream.isChecked()){
            price=+4+5;
            Total = price*numberOfCoffes;
            String Summary = "Name :"+Name.getText()+"\n"+"Mail Id :"+mail.getText()+"\n"+"Contact Number :"+num.getText()+"\n"+"Cost : ₹ "+Total+"\n"+"Toppings : Yes Both Chocolate And Cream";
            jump.putExtra(Intent.EXTRA_TEXT, Summary);
        }
        else {
            Total = price*numberOfCoffes;
            String Summary = "Name :"+Name.getText()+"\n"+"Mail Id :"+mail.getText()+"\n"+"Contact Number :"+num.getText()+"\n"+"Cost : ₹ "+Total+"Toppings : None";
            jump.putExtra(Intent.EXTRA_TEXT, Summary);

        }
        if(jump.resolveActivity(getPackageManager())!=null){
            startActivity(jump);
        }
    }
}

