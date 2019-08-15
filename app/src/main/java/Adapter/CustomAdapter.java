package Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.todolist.R;

import java.util.List;

import Model.tasks;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private   String description;
    private String category ;




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

        holder.title_wig.setText(dataList.get(position).getTitle());
        holder.time_wig.setText(dataList.get(position).getTime());

        //TODO: send it into Dialogpop to display it :
        description=dataList.get(position).getDescription();
        category=dataList.get(position).getCategory();

    }







    @Override
    public int getItemCount() {
        //TODO: update the getItemCount
        return dataList.size();
    }








    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public final View mView;


        //declare the wigth of the CardView
        TextView title_wig;
        TextView time_wig;
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
            if (category=="work"){
                //TODO: send the color code into work color code :
                fill_color_cardview("#5DE61A");

            }

            checked_task=mView.findViewById(R.id.checked_task);

            checked_task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checked_task.playAnimation();
                }
            });


        }
    }

    //TODO add the logic of changing color background cardview:

    private void fill_color_cardview(String color_code )
    {


    }





}
