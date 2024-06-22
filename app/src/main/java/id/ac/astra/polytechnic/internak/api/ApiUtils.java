package id.ac.astra.polytechnic.internak.api;

public class ApiUtils {

    //this is your server API server ip Address
    // (if you use localhost, look for your computer ip address)
//    private static final String API_URL = "http://10.1.17.214:8082/";
    // if You use emulator/AVD use this:
    public static final String API_URL = "http://192.168.0.6:8082/";
    private ApiUtils(){
    }

    public static UserService getUserService() {
        return RetrofitClient.getClient (API_URL).create(UserService.class);
    }
}
