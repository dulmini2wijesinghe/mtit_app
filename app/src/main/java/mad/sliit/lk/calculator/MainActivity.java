package mad.sliit.lk.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;


public class MainActivity extends AppCompatActivity {

    boolean operatorInUse;
    boolean eqlualUse;
    TextView txtQuestion;
    TextView txtAnswer;
    char lastOperator;
    List<Double> values=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtQuestion=(TextView) findViewById(R.id.textQuestion);
        txtAnswer=(TextView) findViewById(R.id.textAnswer);
        eqlualUse=false;
        operatorInUse=true;
    }


    public void calculate()
    {   Double answer=0.00;
        if(values.size()>1) {
            switch (lastOperator)
            {
                case '+':answer = values.get(values.size() - 2) + getLastValue();
                    break;
                case '-':answer = values.get(values.size() - 2) - getLastValue();
                    break;
                case '*':answer = values.get(values.size() - 2) * getLastValue();
                    break;
                case '/':answer = values.get(values.size() - 2) / getLastValue();
                    break;
            }
            values.add(answer);
            txtAnswer.setText(answer+"");
        }
    }

    public void numberTapped(View view) {

        if(eqlualUse) {
            txtQuestion.setText("");
            txtAnswer.setText("");
            values.clear();
            eqlualUse=false;
        }

        if (operatorInUse) {
            txtAnswer.setText("");
            operatorInUse=false;
        }
        Button number=(Button)view;
        String numberString=number.getText().toString();
        txtAnswer.append(numberString);
        txtQuestion.append(numberString);
    }

    public void operatorTapped(View view) {
        if(eqlualUse)
        {
            txtQuestion.setText(values.get(values.size()-1).toString());
            txtAnswer.setText("");
            eqlualUse=false;
        }
        if(!operatorInUse) {
            Button operator = (Button) view;
            String operatorString = operator.getText().toString();
            txtQuestion.append(operatorString);
            operatorInUse = true;
            values.add(getLastValue());
            calculate();
            lastOperator=operatorString.charAt(0);
        }
    }

    public void clearTapped(View view) {
        txtQuestion.setText("");
        txtAnswer.setText("");
        values.clear();
    }


    public double getLastValue()
    {
        double result=0.00;
        String[] input=txtQuestion.getText().toString().split("[+-/*]");
        if(input.length>0)
        {
            result=Double.parseDouble(input[input.length-1]);
        }
        return result;
    }


    public void equalTapped(View view) {
        if((!eqlualUse)&&(!operatorInUse)) {
            values.add(getLastValue());
            calculate();
            eqlualUse = true;
        }
    }
}
