package Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.todolist.R;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.List;

import Adapter.CustomAdapter;
import Dialog.DialogTimer_Frgament;
import Model.tasks;
import Network.GetDataService;
import Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    final static String TAG="Dashboard";

    TextView add_tasks,close_fragment,choose_time;
    TextView category_flag_val,add_tasks_fragment;
    EditText tasks_description,tasks_title;

    RelativeLayout conatiner_add_tasks;
    Animation animte_open,animte_exite,animte_rotate;
    Boolean pressed=false;
    Boolean selected_category=false;
    String input_time,input_description,input_category,input_title;



    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    LinearLayout icon_dasboard;
    TextView field_not_task;

    MDToast mdToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //dec animation :
        animte_open= AnimationUtils.loadAnimation(this,R.anim.open_animation);
        animte_exite= AnimationUtils.loadAnimation(this,R.anim.exite_animation);
        //animte_rotate= AnimationUtils.loadAnimation(this,R.anim.rotate_clock);





        //dec layer:
        conatiner_add_tasks=(RelativeLayout)findViewById(R.id.conatiner_add_tasks);
        choose_time=(TextView)findViewById(R.id.choose_time);
        add_tasks_fragment=(TextView)findViewById(R.id.add_tasks_fragment);


        //dec the inputs:
        tasks_description=(EditText)findViewById(R.id.title_tasks);
        tasks_title=(EditText)findViewById(R.id.tasks_description);



        //TODO: get visiblete gone :
        icon_dasboard=findViewById(R.id.icon_dasboard);
        field_not_task=findViewById(R.id.field_not_task);

        //dec:
        category_flag_val=(TextView)findViewById(R.id.category_flag_val);
        add_tasks=(TextView)findViewById(R.id.add_tasks);
        add_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add_tasks.startAnimation(animte_rotate);
                conatiner_add_tasks.startAnimation(animte_open);
                conatiner_add_tasks.setVisibility(View.VISIBLE);

            }
        });


        //dec action_compmonets:
        close_fragment=(TextView)findViewById(R.id.close_fragment);
        close_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conatiner_add_tasks.startAnimation(animte_exite);
                conatiner_add_tasks.setVisibility(View.INVISIBLE);
            }
        });


        //dec the selct Time:
        choose_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //show the Time clock :
                DialogFragment timePicker = new DialogTimer_Frgament();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });

        add_tasks_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostIntoAPI();
            }


        });

        //TODO: get the data from the ViewGroup and send it into the API :
        init();







    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<tasks> tasksList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,tasksList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Dashboard.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }











    private void PostIntoAPI() {
        if ((tasks_description.getText().toString().equals("")) ||(tasks_title.getText().toString().equals("")) || (category_flag_val.getText().toString().equals("")) || (choose_time.getText().toString().equals(""))){
            mdToast = MDToast.makeText(getApplicationContext(), "You Have Empty Input", Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
            mdToast.show();
            Log.d(TAG, "PostIntoAPI: ");
            Log.d(TAG, ":tasks_description "+tasks_description.getText().toString());
            Log.d(TAG, ":tasks_title "+tasks_title.getText().toString());
            Log.d(TAG, ":tasks_category "+category_flag_val.getText().toString());
            Log.d(TAG, ":choose_time "+choose_time.getText().toString());
        }
        else if (!(tasks_description.getText().toString().equals("")) &&(!tasks_title.getText().toString().equals("")) && (!category_flag_val.getText().toString().equals("")) && (!choose_time.getText().toString().equals(""))){

            //To send the data and post it into the server you need to send as parmas into model class instance :
            tasks tasks_model_class=new tasks(tasks_description.getText().toString(),tasks_title.getText().toString(),category_flag_val.getText().toString(),choose_time.getText().toString());
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<tasks> call=service.createpost(tasks_model_class);
            call.enqueue(new Callback<tasks>() {
                @Override
                public void onResponse(Call<tasks> call, Response<tasks> response) {

                    if (!response.isSuccessful() || response.body()!=null) {
                        //do some logic :
                        mdToast = MDToast.makeText(getApplicationContext(), "New task has been created successfully.", Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                        mdToast.show();
                        //TODO: add custom animation :
                        conatiner_add_tasks.startAnimation(animte_exite);
                        conatiner_add_tasks.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<tasks> call, Throwable t) {

                    //desplay error message :
                    mdToast = MDToast.makeText(getApplicationContext(), "You Have some error "+t.getMessage(), Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                    Log.d(TAG, "onFailure: "+t.getMessage());

                }
            });



            /*
            Call<List<tasks>> call = service.createpost(tasks_model_class);
            call.enqueue(new Callback<List<tasks>>() {
                @Override
                public void onResponse(Call<List<tasks>> call, Response<List<tasks>> response) {
                    //TODO:check the reponse if sucesses:
                    if (!response.isSuccessful() && response.body()!=null){
                        //do some logic :
                        mdToast = MDToast.makeText(getApplicationContext(), "New task has been created successfully.", Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                        mdToast.show();
                        //TODO: make th task constaine invisible :
                        conatiner_add_tasks.startAnimation(animte_exite);
                        conatiner_add_tasks.setVisibility(View.INVISIBLE);


                    }

                }

                @Override
                public void onFailure(Call<List<tasks>> call, Throwable t) {
                    //desplay error message :
                    mdToast = MDToast.makeText(getApplicationContext(), "You Have some error "+t.getMessage(), Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                    Log.d(TAG, "onFailure: "+t.getMessage());


                }
            });

            */



        }



    }









    private void init() {
        getdata();
        load_data();

    }

    private void load_data() {

        progressDoalog = new ProgressDialog(Dashboard.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<tasks>> call = service.getalltasks();
        call.enqueue(new Callback<List<tasks>>() {
            @Override
            public void onResponse(Call<List<tasks>> call, Response<List<tasks>> response) {
                progressDoalog.dismiss();
                icon_dasboard.setVisibility(View.GONE);
                field_not_task.setVisibility(View.GONE);
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<tasks>> call, Throwable t) {
                progressDoalog.dismiss();
                icon_dasboard.setVisibility(View.VISIBLE);
                field_not_task.setVisibility(View.VISIBLE);
                Toast.makeText(Dashboard.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getdata() {
        input_description=tasks_description.getText().toString();
        input_title=tasks_title.getText().toString();
        input_time=choose_time.getText().toString();
        Category_List();


    }



    private void Category_List() {
        RadioGroup category_radio_group = (RadioGroup) findViewById(R.id.category_radio_group);

        category_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String s="";
                switch (checkedId) {
                    case R.id.personnel:
                        //catergoy personnel selected:
                        showToast("personnel");
                        getCategory("personnel");
                        break;
                    case R.id.work:
                        //catergoy work selected:
                        showToast("work");
                        getCategory("work");
                        break;
                    case R.id.study:
                        //catergoy study selected:
                        showToast("study");
                        getCategory("study");
                        break;
                    case R.id.meeting:
                        //catergoy meeting selected:
                        showToast("meeting");
                        getCategory("meeting");
                        break;
                    case R.id.shopping:
                        //catergoy shopping selected:
                        showToast("shopping");
                        getCategory("shopping");
                        break;

                }

            }

        });

    }

    public void getCategory(String ms){
    category_flag_val.setText(ms);
}


    private void showToast(String msg) {
        mdToast = MDToast.makeText(getApplicationContext(), "You Select Category "+msg, Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
        mdToast.show();
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        choose_time.setText(""+ i +":" + i1);

    }







}
