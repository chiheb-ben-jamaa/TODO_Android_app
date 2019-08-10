package Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.todolist.R;

import java.util.List;

import Adapter.CustomAdapter;
import Dialog.DialogTimer_Frgament;
import Model.tasks;
import Network.GetDataService;
import Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Dashboard extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    TextView add_tasks,close_fragment,choose_time;
    TextView personal,work,meeting,study,shopping;
    EditText tasks_description;

    RelativeLayout conatiner_add_tasks;
    Animation animte_open,animte_exite,animte_rotate;
    Boolean pressed=false;
    Boolean selected_category=false;
    String input_time,input_description,input_category;



    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    LinearLayout icon_dasboard;
    TextView field_not_task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //dec animation :
        animte_open= AnimationUtils.loadAnimation(this,R.anim.open_animation);
        animte_exite= AnimationUtils.loadAnimation(this,R.anim.exite_animation);
        //animte_rotate= AnimationUtils.loadAnimation(this,R.anim.rotate_clock);



        //dec the tag list :
        personal=(TextView)findViewById(R.id.personal);
        work=(TextView)findViewById(R.id.work);
        meeting=(TextView)findViewById(R.id.meeting);
        study=(TextView)findViewById(R.id.study);
        shopping=(TextView)findViewById(R.id.shopping);

        //dec layer:
        conatiner_add_tasks=(RelativeLayout)findViewById(R.id.conatiner_add_tasks);
        choose_time=(TextView)findViewById(R.id.choose_time);

        //dec the inputs:
        tasks_description=(EditText)findViewById(R.id.tasks_description);



        //TODO: get visiblete gone :
        icon_dasboard=findViewById(R.id.icon_dasboard);
        field_not_task=findViewById(R.id.field_not_task);

        //dec:
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

        //TODO: get the data from the ViewGroup and send it into the API :
        init();



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


        //Close of OnCreate
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<tasks> tasksList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,tasksList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Dashboard.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


















    private void init() {
        getdata();

    }

    private void getdata() {
        input_description=tasks_description.getText().toString();
        input_time=choose_time.getText().toString();
        //input_category=getCategory();

    }




    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        choose_time.setText("Today : Hour "+ i +":" + i1);

    }





    public void onClickTextview(View view) {

        switch (view.getId()) {

            case R.id.personal: {


                    if (pressed) {

                        personal.setBackgroundResource(R.drawable.ic_personal_select);
                        pressed = true;
                        selected_category = true;


                    } else {
                        personal.setBackgroundResource(R.drawable.ic_personal);
                    }


                    pressed = !pressed;

                break;
            }

            case R.id.work:{



                    if (pressed) {
                        work.setBackgroundResource(R.drawable.ic_work_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        work.setBackgroundResource(R.drawable.ic_work);

                    }

                    pressed = !pressed;

                    break;
                }





            case R.id.meeting:{


                    if (pressed) {
                        meeting.setBackgroundResource(R.drawable.ic_meeting_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        meeting.setBackgroundResource(R.drawable.ic_meeting);

                    }


                    pressed = !pressed;

                break;
            }




            case R.id.study:{


                    if (pressed) {
                        study.setBackgroundResource(R.drawable.ic_study_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        study.setBackgroundResource(R.drawable.ic_study);

                    }


                    pressed = !pressed;

                break;
            }




            case R.id.shopping:{


                    if (pressed) {
                        shopping.setBackgroundResource(R.drawable.ic_shopping_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        shopping.setBackgroundResource(R.drawable.ic_shopping);
                        selected_category = false;

                    }


                    pressed = !pressed;

                break;
            }


    }


    }
}
