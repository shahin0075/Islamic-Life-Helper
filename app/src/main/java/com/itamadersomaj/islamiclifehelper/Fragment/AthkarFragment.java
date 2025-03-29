package com.itamadersomaj.islamiclifehelper.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.itamadersomaj.islamiclifehelper.Adapter.AthkarAdapter;
import com.itamadersomaj.islamiclifehelper.Model.AthkarModel;
import com.itamadersomaj.islamiclifehelper.R;
import java.util.List;


public class AthkarFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<AthkarModel> athkarModelList;
    Context context;
    private String mParam1;
    private String mParam2;
    RecyclerView rvAthkar;

    public AthkarFragment(Context context, List<AthkarModel> list) {
        this.context = context;
        this.athkarModelList = list;
    }

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_athkar, viewGroup, false);
        this.rvAthkar = (RecyclerView) inflate.findViewById(R.id.rvAthkar);
        this.rvAthkar.setLayoutManager(new LinearLayoutManager(this.context));
        this.rvAthkar.setAdapter(new AthkarAdapter(this.context, this.athkarModelList));
        return inflate;
    }
}
