package id.sandri.joborder.ApiHelper;

public class UtilsApiPrinting {
    public static final String BASE_URL_API = "https://mgi-schedule.herokuapp.com/api/schedule/printing/";

    public static BaseApiService getAPIService(){
        return RetrofitMachinePrinting.getDataPrinting(BASE_URL_API).create(BaseApiService.class);
    }
}
