package Network;

import Model.tasks;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {



    private static Retrofit retrofit;

    private static RetrofitClientInstance retrofitClientInstance;
    //TODO this is the basic api route :
    private static final String BASE_URL = "https://peaceful-lake-53040.herokuapp.com/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static RetrofitClientInstance getInstance() {
        if (retrofitClientInstance == null) {
            retrofitClientInstance = new RetrofitClientInstance();
        }
        return retrofitClientInstance;
    }





}
