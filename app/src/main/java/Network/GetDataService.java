package Network;

import java.util.List;

import Model.tasks;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("tasks")
    Call<List<tasks>> getalltasks();

    @POST("tasks")
    Call<tasks> createtask(@Body tasks tasks);

    @DELETE("tasks/{id}")
    Call<Void> deletetask(@Path("id") String id);

}
