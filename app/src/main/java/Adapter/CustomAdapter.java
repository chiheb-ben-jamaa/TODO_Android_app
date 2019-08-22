package Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.android.todolist.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.List;

import Model.tasks;
import Network.GetDataService;
import Network.RetrofitClientInstance;
import lib.mozidev.me.extextview.ExTextView;
import lib.mozidev.me.extextview.StrikeThroughPainting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private   String description;
    private String category;
    private String id ;
    private int pos;
    private Boolean ret;
    private boolean startAnimation;




    private List<tasks> dataList;
    private Context context;

    public CustomAdapter(Context context, List<tasks> dataList){
        this.context = context;
        this.dataList = dataList;

    }









    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_cardview, parent, false);
        return new CustomViewHolder(view);
    }







    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {

        //TODO: .get(getItemCount()-position-1) to reverse the oerder of the recycleur view :
        holder.title_wig.setText(dataList.get(getItemCount()-position-1).getTitle());
        holder.time_wig.setText(dataList.get(getItemCount()-position-1).getTime());

        //TODO: send it into Dialogpop to display it :
        description=dataList.get(getItemCount()-position-1).getDescription();
        category=dataList.get(getItemCount()-position-1).getCategory();
        ///id=dataList.get(getItemCount()-position-getItemCount()).getId();
        pos = getItemCount()-position-1;
        fill_color_cardview(category,holder.fill_card_view);

    }




    @Override
    public int getItemCount() {
        //TODO: update the getItemCount
        return dataList.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(getItemCount() - position - 1);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public final View mView;


        //declare the wigth of the CardView
        TextView title_wig;
        lib.mozidev.me.extextview.ExTextView time_wig;
        TextView fill_card_view;
        LottieAnimationView checked_task;
        //TODO: change the color of cardview :
        RelativeLayout cardview_bg;



        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            //Set the data from the API into Conpoment of the CardView
            title_wig = mView.findViewById(R.id.title_wig);
            time_wig = mView.findViewById(R.id.time_wig);

            //set the Relative layout and change the color :
            cardview_bg =mView.findViewById(R.id.cardview_bg);
            fill_card_view=mView.findViewById(R.id.fill_card_view);


            checked_task=mView.findViewById(R.id.checked_task);
            checked_task.setBackgroundResource(R.drawable.ic_not_checked);
            checked_task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checked_task.playAnimation();
                    checked_task.setBackground(null);
                  //if (ret==true){

                      Log.d("MaterialStyledDialogs", "Delete task !");
                      MDToast  mdToast = MDToast.makeText(context, "Task Deleted successfully", Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                      mdToast.show();
                      strikeThroughTextView(time_wig,true);
                      Delete_task();


                  //}else {
                      //Log.d("MaterialStyledDialogs", "Cancal Delete task !");
                        //checked_task.cancelAnimation();
                        //checked_task.setBackgroundResource(R.drawable.ic_not_checked);
                  //}


                }

            });





        }


        private void Cancal_delete() {

        }


    }

    //TODO add the logic of changing color background cardview:

    private void fill_color_cardview(String color_code,View t )
    {
        if (color_code.equals("personal")){
            t.setBackgroundResource(R.drawable.ic_color_category1);
        }else  if (color_code.equals("work")){
            t.setBackgroundResource(R.drawable.ic_color_category2);
        }else if (color_code.equals("meeting")){
            t.setBackgroundResource(R.drawable.ic_color_category3);
        }else if (color_code.equals("study")){
            t.setBackgroundResource(R.drawable.ic_color_category4);
        }else if (color_code.equals("shopping")){
            t.setBackgroundResource(R.drawable.ic_color_category5);
        }
    }


    private void strikeThroughTextView(ExTextView tv,Boolean t){

        StrikeThroughPainting strikeThroughPainting = new StrikeThroughPainting(tv);
        if (t==true){




// Basic Usage
        strikeThroughPainting.strikeThrough();

// All Options
        strikeThroughPainting
                // default to true
                .cutTextEdge(false)
                // default to Color.BLACK
                .color(R.color.linecolortext)
                // default to 2F px
                .strokeWidth(3F)
                // default to StrikeThroughPainting.MODE_DEFAULT
                .mode(StrikeThroughPainting.MODE_DEFAULT)
                // default to 0.65F
                .linePosition(0.7F)
                // default to 0.6F, since the first line is calculated
                // differently to the following lines
                .firstLinePosition(0.6F)
                // default to 1_000 milliseconds, aka 1s
                .totalTime(2_000L)
                .callback(new StrikeThroughPainting.StrikeThroughPaintingCallback() {
                    @Override
                    public void onStrikeThroughEnd() {
                        //MDToast  mdToast = MDToast.makeText(context, "Are you Sur You Went to Delete this Task", Toast.LENGTH_SHORT, MDToast.TYPE_INFO);
                        //mdToast.show();

                    }
                }) // do the draw!
                .strikeThrough();

        // Clear Strike Through
                strikeThroughPainting.clearStrikeThrough();

        }


        else
        {
            // default to true
            strikeThroughPainting.cutTextEdge(false);
        }

            }

        private void DialogAlertDelete(){

            MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(context)
                    .setTitle("Confirm to delete Task!")
                    .setDescription("If you are sure to Delete this task click OK !")
                    .setIcon(R.drawable.ic_delete)
                    .withIconAnimation(true)
                    .withDialogAnimation(true)
                    .setHeaderColor(R.color.alert_color)
                    .setPositiveText("Ok")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            ret=true;


                        }
                    })
                    .setNegativeText("Cancel")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            ret=false;




                        }
                    })
                    .setCancelable(true)
                    .build();

            dialog.show();



        }




    private void Delete_task(){
       id= dataList.get(pos).getId();
        //Log.d(TAG, "Delete_task: Task ID "+id);
        //MDToast  mdToast = MDToast.makeText(context, "Task ID :"+id, Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
        //mdToast.show();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Void> call=service.deletetask(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: The Task has been deleted !");
                //TODO: update the recycler view
                //TODO: delete the item from ther postion :
                 dataList.remove(pos);
                //TODO: notify the recycleur view for any chaning :
                 notifyItemRemoved(pos);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: You have some error check again plz !");

            }
        });


    }



}
