package id.sandri.joborder.ApiHelper;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitMachinePrinting {
    private static Retrofit retrofit = null;

    public static Retrofit getDataPrinting(String baseUrl){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
