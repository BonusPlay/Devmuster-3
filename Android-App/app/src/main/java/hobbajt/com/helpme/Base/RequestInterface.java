package hobbajt.com.helpme.Base;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface
{
    @POST("receiveData.php")
    Call<String> sendReport(@Body Object report);
}
