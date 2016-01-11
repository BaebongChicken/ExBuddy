package kr.co.kj_studio.exbuddy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import kr.co.kj_studio.exbuddy.R;
import kr.co.kj_studio.exbuddy.adapter.MissionAdapter;
import kr.co.kj_studio.exbuddy.dataClass.MissionData;
import kr.co.kj_studio.exbuddy.utils.ServerUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KJ_Studio on 2015-12-05.
 */
public class MissionsFragment extends BaseFragment {

    ListView missionListView;
    ArrayList<MissionData> mMissionList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutResId = R.layout.fragment_missions;
        View v = super.onCreateView(inflater, container, savedInstanceState);

        missionListView = (ListView) v.findViewById(R.id.missionListView);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ServerUtil.getMissions(getActivity().getApplicationContext(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("MissionsFragement", json.toString());

                try {
                    JSONArray data = json.getJSONArray("missions");

                    for (int i = 0; i < data.length(); i++) {
                        mMissionList.add(MissionData.getMissionDataFromJson(data.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        missionListView.setAdapter(new MissionAdapter(getActivity(), mMissionList));


    }
}
