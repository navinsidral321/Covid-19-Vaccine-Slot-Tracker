package com.example.anew;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Button button;
    Spinner district,date;
    String a;
    Integer b=0;
    EditText editText;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    ArrayList<Integer> list1=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addnotificatiochannel();
        button=findViewById(R.id.button);
        district=findViewById(R.id.spinner);
       // date=findViewById(R.id.spinner2);
        district.setOnItemSelectedListener(this);
        editText=findViewById(R.id.editText);
        Intent intent = new Intent(getApplicationContext(),notificationbroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);

        click();

       final ArrayList<String> list2=new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(this);

        final ArrayList<String> districtname=new ArrayList<>();
        final ArrayList<Integer> district_id=new ArrayList<>();
        list1.add(0);


      //  SharedPreferences sharedPreferences=getSharedPreferences("First Update",0);
   //     if(!sharedPreferences.getBoolean("Frist",false)){



            String url = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/21";
districtname.add("district name");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                JSONArray a=response.getJSONArray("districts");

                                for(int i=0;i<a.length();i++){
                                    districtname.add((String) response.getJSONArray("districts").getJSONObject(i).get("district_name"));
                                    district_id.add((Integer) response.getJSONArray("districts").getJSONObject(i).get("district_id"));

                                    //   Toast.makeText(getApplicationContext(),""+response.getJSONArray("districts").getJSONObject(i).get("district_name").toString(),Toast.LENGTH_SHORT).show();
                                }
                             //   Toast.makeText(getApplicationContext(),"hii"+districtname.getClass().getName(),Toast.LENGTH_SHORT).show();

                                for(int i=0;i<districtname.size()-1;i++){
                                    list1.add(district_id.get(i));


                                }
                            //    Toast.makeText(getApplicationContext(),"hiiiiiii"+list1.size(),Toast.LENGTH_SHORT).show();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {


                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Toast.makeText(getApplicationContext(),"No nerwork!",Toast.LENGTH_SHORT).show();

                        }
                    }

                    );

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,districtname);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        district.setAdapter(arrayAdapter);



            queue.add(jsonObjectRequest);

           /* SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("Frist",true);
            editor.commit();
        }*/


      //  Toast.makeText(getApplicationContext(),"hiii"+list2.get(0).getClass().getName(),Toast.LENGTH_SHORT).show();


        ArrayList<String> array=new ArrayList<>();
        array.add("hii");
        array.add("hiii");




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d=editText.getText().toString();
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("district_id",b);
                bundle.putString("d",d);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
    public void click(){


        Calendar alarmStartTime = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 18);
        alarmStartTime.set(Calendar.MINUTE, 30);
        alarmStartTime.set(Calendar.SECOND, 5);
        if (now.after(alarmStartTime)) {
            Log.d("Hey","Added a day");
            alarmStartTime.add(Calendar.DATE, 1);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmStartTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
       // Toast.makeText(this, "Alarm Set"+alarmStartTime.getInstance().getTime(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> p, View view, int position, long id) {

        a=p.getItemAtPosition(position).toString();

        b=list1.get(position);

      /*  if(a!=" ") {
            Toast.makeText(getApplicationContext(), "" + b, Toast.LENGTH_SHORT).show();
        }*/
        close();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void close() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void addnotificatiochannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notification";
            String description = "hii";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notify", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
