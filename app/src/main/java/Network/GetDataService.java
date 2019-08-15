package Network;

import java.util.List;

import Model.tasks;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {

    @GET("tasks")
    Call<List<tasks>> getalltasks();

    @POST("tasks")
    Call<List<tasks>> posttask(@Body tasks tasks_model_ins);

}
