package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.todolist.R;

import java.util.ArrayList;

public class his_recylerview_adapter extends RecyclerView.Adapter<his_recylerview_adapter.ViewHolder> {
    //private ArrayList<TextView> background_image=new ArrayList<>();
    private int flag=0;
    private Context mContext;




    //TODO: step 3 define the type of structeur to implement the recycleur view :
    public his_recylerview_adapter() {

    }



    //TODO: step1 create the ViewHolderClass:
    public  class ViewHolder extends RecyclerView.ViewHolder{
        //declare the wigt of the cardview :
        TextView background_category_flag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            background_category_flag=itemView.findViewById(R.id.background_category_flag);
        }
    }


    //TODO: step2 extends the recycleurview adapter to the class & implment the function :

    //TODO: step 5 : change the ViewHolder to inflate the custom  layout (cardview) and return new ViewHolder with the view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position==1){
            holder.background_category_flag.setBackgroundResource(R.drawable.ic_personal);
            flag=1;
        }else if (position==2){
            holder.background_category_flag.setBackgroundResource(R.drawable.ic_work);
            flag=2;
        }else if (position==3){
            holder.background_category_flag.setBackgroundResource(R.drawable.ic_meeting);
            flag=3;
        }else if (position==4){
            holder.background_category_flag.setBackgroundResource(R.drawable.ic_study);
            flag=4;
        }else if (position==5){
            holder.background_category_flag.setBackgroundResource(R.drawable.ic_shopping);
            flag=5;
        }

        holder.background_category_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (flag==1){
                   view.setBackgroundResource(R.drawable.ic_personal_select);
                   Toast.makeText(mContext,"Pos"+flag,Toast.LENGTH_SHORT).show();
               }
               else if (flag==2){
                   view.setBackgroundResource(R.drawable.ic_work_select);
                   Toast.makeText(mContext,"Pos"+flag,Toast.LENGTH_SHORT).show();

               }
            }
        });
    }

    //TODO: step 4 chang it to return the size
    @Override
    public int getItemCount() {
        return 6 ;
    }
}
