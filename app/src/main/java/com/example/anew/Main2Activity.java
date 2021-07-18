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
import android.widget.AdapterView;
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

public class Main2Activity extends AppCompatActivity {
    int a;
    String b;
    ListView listView;

    ArrayList<listformat> formatdaata=new ArrayList<>();

    ArrayList<Integer> center_id1=new ArrayList<>();
    ArrayList<Integer> dose1=new ArrayList<>();
    ArrayList<Integer> dose2=new ArrayList<>();
    ArrayList<String> center_name=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            a=bundle.getInt("district_id");
            b=bundle.getString("d");
        }
       /* Intent intent=getIntent();
        a=intent.getIntExtra("value",10);*/
        //Toast.makeText(getApplicationContext(),b+""+a,Toast.LENGTH_SHORT).show();





        function();


    }

    public void function(){
        final listformat data=new listformat();

        final RequestQueue queue = Volley.newRequestQueue(this);


        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id="+a+"&date="+b+"";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    //    Toast.makeText(getApplicationContext(),"list11"+formatdaata.size(),Toast.LENGTH_SHORT).show();
                        try {

                            JSONArray a1=response.getJSONArray("sessions");
                           // JSONArray session=response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions");
                            for(int i=0;i<a1.length();i++){
                               // if((Integer)response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions").getJSONObject(0).get("available_capacity")!=0){

                                //JSONArray session=response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions");
                              /*  int countdose1=0;
                                int countdose2=0;*/
                               // for(int j=0;j<session.length();j++){
                                    /*data.setCenter_id((Integer) response.getJSONArray("centers").getJSONObject(i).get("center_id"));
                                    data.setDose1((Integer) response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions").getJSONObject(j).get("available_capacity_dose1"));
                                    data.setDose2((Integer) response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions").getJSONObject(j).get("available_capacity_dose2"));
                                  // Toast.makeText(getApplicationContext(),"a"+ (Integer) response.getJSONArray("centers").getJSONObject(i).get("center_id"),Toast.LENGTH_SHORT).show();
                                    formatdaata.add(data);
                                    countdose1+=(Integer) response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions").getJSONObject(j).get("available_capacity_dose1");
                                    countdose2+=(Integer) response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions").getJSONObject(j).get("available_capacity_dose2");
                                   /* center_id1.add((Integer) response.getJSONArray("centers").getJSONObject(i).get("center_id"));
                                    center_name.add((String) response.getJSONArray("centers").getJSONObject(i).get("name"));
                                    dose1.add((Integer) response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions").getJSONObject(j).get("available_capacity_dose1"));
                                    dose2.add((Integer) response.getJSONArray("centers").getJSONObject(i).getJSONArray("sessions").getJSONObject(j).get("available_capacity_dose2"));*/

                             //   }*/
                                center_id1.add((Integer) response.getJSONArray("sessions").getJSONObject(i).get("center_id"));
                                center_name.add((String) response.getJSONArray("sessions").getJSONObject(i).get("name"));
                                dose1.add((Integer) response.getJSONArray("sessions").getJSONObject(i).get("available_capacity_dose1"));
                                dose2.add((Integer) response.getJSONArray("sessions").getJSONObject(i).get("available_capacity_dose2"));


                               // }
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
        listView=findViewById(R.id.listview);
        final hii hi=new hii(this,center_name,dose1,dose2);
       // Toast.makeText(getApplicationContext(),"list1"+formatdaata.size(),Toast.LENGTH_SHORT).show();
        listView.setAdapter(hi);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),details.class);
               // intent.putExtra("value",center_id1.get(position));

                Bundle bundle=new Bundle();
                bundle.putInt("district_id",a);
                bundle.putInt("center_id",center_id1.get(position));
                bundle.putString("d",b);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    class hii extends ArrayAdapter<String>{
        //ArrayList<Integer> list1;
        ArrayList<String> list2;
        ArrayList<Integer> list3;
        ArrayList<Integer> list4;
        Context context;
        public hii(@NonNull Context context, ArrayList<String> resource,ArrayList<Integer> resource1,ArrayList<Integer> resource2) {
            super(context,R.layout.activity_list,R.id.textView3,resource);
            this.context=context;
            this.list2=resource;
            this.list3=resource1;
            this.list4=resource2;
        }

        @NonNull
        @Override
        public View getView(int position1, @Nullable View convertView, @NonNull ViewGroup p) {
            LayoutInflater layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row=layoutInflater.inflate(R.layout.activity_list,p,false);


            TextView center_id=(TextView) row.findViewById(R.id.textView3);
            TextView dose=row.findViewById(R.id.textView4);

          center_id.setText(list2.get(position1).toString());
           dose.setText("Dose 1:" +list3.get(position1).toString()+"  Dose 2:"+list4.get(position1).toString());

            return row;
        }
    }
}
