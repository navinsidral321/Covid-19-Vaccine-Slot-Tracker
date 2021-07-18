package com.example.anew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class details extends AppCompatActivity {
int center_id=0;
int district_id;
String a;
ListView listView;

    ArrayList<listformat> formatdaata=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


       // Intent intent=getIntent();
        //center_id=intent.getIntExtra("value",0);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            district_id=bundle.getInt("district_id");
            center_id=bundle.getInt("center_id");
            a=bundle.getString("d");
        }

        listView=findViewById(R.id.listview);


        final listformat data=new listformat();

        final RequestQueue queue = Volley.newRequestQueue(this);


        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id="+district_id+"&date=+"+a+"";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(),"list11"+formatdaata.size(),Toast.LENGTH_SHORT).show();
                        try {

                            JSONArray a1=response.getJSONArray("sessions");
                            // JSONArray session=response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions");
                            for(int i=0;i<a1.length();i++){

                                if((Integer) response.getJSONArray("sessions").getJSONObject(i).get("center_id")==center_id){
                                data.setCenter_id((Integer) response.getJSONArray("sessions").getJSONObject(i).get("center_id"));
                                data.setDose1((Integer) response.getJSONArray("sessions").getJSONObject(i).get("available_capacity_dose1"));
                                data.setDose2((Integer) response.getJSONArray("sessions").getJSONObject(i).get("available_capacity_dose2"));
                                data.setName((String) response.getJSONArray("sessions").getJSONObject(i).get("name"));
                                    data.setAddress((String) response.getJSONArray("sessions").getJSONObject(i).get("address"));
                                    data.setBlock_name((String) response.getJSONArray("sessions").getJSONObject(i).get("block_name"));
                                    data.setPincode((Integer) response.getJSONArray("sessions").getJSONObject(i).get("pincode"));
                                    data.setFee_type((String) response.getJSONArray("sessions").getJSONObject(i).get("fee_type"));
                                    data.setSession_id((String) response.getJSONArray("sessions").getJSONObject(i).get("session_id"));
                                    data.setAge_limit((Integer) response.getJSONArray("sessions").getJSONObject(i).get("min_age_limit"));
                                    data.setVaccine((String) response.getJSONArray("sessions").getJSONObject(i).get("vaccine"));
                                    data.setFrom((String) response.getJSONArray("sessions").getJSONObject(i).get("from"));

                                    data.setTo((String) response.getJSONArray("sessions").getJSONObject(i).get("to"));
                                formatdaata.add(data);
                                break;

                                 }
                            }
                            //Toast.makeText(getApplicationContext(),"list"+formatdaata.size(),Toast.LENGTH_SHORT).show();
                            function1();


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

        queue.add(jsonObjectRequest);




    }
    public void function1(){
        final hiii hi=new hiii(this,formatdaata);
        listView.setAdapter(hi);
    }

    class hiii extends ArrayAdapter<listformat> {



        ArrayList<listformat> list3;

        Context context;
        public hiii(@NonNull Context context, ArrayList<listformat> resource) {
            super(context,R.layout.activity_detailsformat,R.id.details1,resource);
            this.context=context;

            this.list3=resource;

        }

        @NonNull
    //    @Override
        public View getView(int position1, @Nullable View convertView, @NonNull ViewGroup p) {
            LayoutInflater layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row=layoutInflater.inflate(R.layout.activity_detailsformat,p,false);


            TextView center_id=(TextView) row.findViewById(R.id.details1);
            TextView name=row.findViewById(R.id.details2);
            TextView address=row.findViewById(R.id.details3);
            TextView block_name=row.findViewById(R.id.details4);
            TextView pincode=row.findViewById(R.id.details5);
            TextView time=row.findViewById(R.id.details6);
            TextView fee_type=row.findViewById(R.id.details7);
            TextView session_id=row.findViewById(R.id.details8);
            TextView dose=row.findViewById(R.id.details9);
            TextView min_age_limit=row.findViewById(R.id.details10);
            TextView vaccine=row.findViewById(R.id.details11);




            center_id.setText("Center_id: "+list3.get(position1).getCenter_id());
            name.setText("Name : "+list3.get(position1).getName());
            address.setText("Address : "+list3.get(position1).getAddress());
            block_name.setText("Block_Name : "+list3.get(position1).getBlock_name());
            pincode.setText("Pincode : "+list3.get(position1).getPincode());
            time.setText("Time : "+list3.get(position1).getFrom()+" to"+list3.get(position1).getTo());
            fee_type.setText("Fee-Type : "+list3.get(position1).getFee_type());
            session_id.setText("Session_id : "+list3.get(position1).getSession_id());
            min_age_limit.setText("min_age_limit : "+list3.get(position1).getAge_limit());
            vaccine.setText("Vaccine : "+list3.get(position1).getVaccine());
            dose.setText("Dose 1:" +list3.get(position1).getDose1()+"  Dose 2:"+list3.get(position1).getDose2());

            return row;
        }
    }

}
