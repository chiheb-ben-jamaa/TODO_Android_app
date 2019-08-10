package Network;

import java.util.List;

import Model.tasks;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("tasks")
    Call<List<tasks>> getalltasks();
}
