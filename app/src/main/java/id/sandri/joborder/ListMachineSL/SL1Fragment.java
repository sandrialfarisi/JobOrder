package id.sandri.joborder.ListMachineSL;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.sandri.joborder.Adapter.MachineAdapter;
import id.sandri.joborder.ApiHelper.BaseApiService;
import id.sandri.joborder.ApiHelper.UtilsApiSliting;
import id.sandri.joborder.Model.Machine;
import id.sandri.joborder.R;
import id.sandri.joborder.utils.LoginSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SL1Fragment extends Fragment implements Callback<String> {
    private String token;
    private LoginSession loginSession;
    private MachineAdapter adapter;
    private RecyclerView rvMachine;
    private ArrayList<Machine> listMachine;
    private BaseApiService mApiService;
    private ShimmerFrameLayout shimmerFrameLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton backtotop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sl1, container, false);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        loginSession = new LoginSession(getContext());
        adapter = new MachineAdapter(getContext());
        rvMachine = view.findViewById(R.id.rv_data_sliting1);
        shimmerFrameLayout = view.findViewById(R.id.shimmer_sliting1);
        swipeRefreshLayout = view.findViewById(R.id.swipe_layout_sliting1);
        backtotop = view.findViewById(R.id.sl1backtotop);

        listMachine = new ArrayList<>();
        token = loginSession.getSPToken();
        mApiService = UtilsApiSliting.getAPIService();
        Call<String> userCall = mApiService.getDataSliting1(token);
        userCall.enqueue(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


//        final Handler autoUpdateHandler = new Handler();
//
//        autoUpdateHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                refreshData();
//                autoUpdateHandler.postDelayed(this, 600000);
//            }
//        }, 600000);

        return view;
    }

    private void refreshData(){
        token = loginSession.getSPToken();
        mApiService = UtilsApiSliting.getAPIService();
        Call<String> userCall = mApiService.getDataSliting1(token);
        listMachine.clear();
        listMachine = new ArrayList<>();
        userCall.enqueue(this);
        adapter.notifyDataSetChanged();

        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        rvMachine.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(@NotNull Call<String> call, Response<String> response) {
        Log.d("msg1", response.toString());
        assert response.body() != null;
        Log.d("Responsestring", response.body());

        if (response.isSuccessful()){
            if (response.body() != null){
                final String jsonResponse = response.body();
                writeRv(jsonResponse);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                rvMachine.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onFailure(@NotNull Call<String> call, Throwable t) {
        Log.d("error", "onFailure: " + t.getMessage());
        Toast.makeText(getContext(), t.getMessage()+ " : Please refresh manually or Re-Login", Toast.LENGTH_LONG).show();
    }

    private void writeRv(String response){
        try {
            JSONObject object = new JSONObject(response);
            if (object.optString("success").equals("true")){
                JSONArray dataArray = object.getJSONArray("data");
                for (int i=0; i <dataArray.length(); i++){
                    JSONObject object2 = dataArray.getJSONObject(i);
                    Machine machineModel = new Machine(object2);
                    listMachine.add(machineModel);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    layoutManager.setStackFromEnd(true);
                    layoutManager.setReverseLayout(true);
                    rvMachine.setLayoutManager(layoutManager);
                    adapter.setListMachine(listMachine);
                    rvMachine.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    backtotop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rvMachine.scrollToPosition(listMachine.size() -1);
                        }
                    });
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint("Write your No.OK in here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<Machine> dataFilter = new ArrayList<>();
                for (Machine data : listMachine){
                    String nama = data.getNo_ok().toLowerCase();
                    if (nama.contains(newText)){
                        dataFilter.add(data);
                    }
                }
                adapter.filterList(dataFilter);
                return true;
            }
        });
        searchItem.setActionView(searchView);
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }
}
