package com.example.bletrackerandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListNearbyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListNearbyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListNearbyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListNearbyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListNearbyFragment newInstance(String param1, String param2) {
        ListNearbyFragment fragment = new ListNearbyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_nearby, container, false);

        String[] items = {  "ID#1   Crane#2  8m",
                            "ID#3   LargeWrench#5  15m",
                            "ID#4   Hammer#6  12m",
                            "ID#6   Crane#19  6m",
                            "ID#7   Crane#21  7m",
                            "ID#9   Hammer#9  5m",
                            "ID#10   Nails#11  17m",
                            "ID#12   ScrewDriver#21  19m",
                            "ID#13   SmallWrench#5  11m",
                            "ID#15   Crane#27  21m"};

        ListView listView = (ListView) view.findViewById(R.id.listNearby);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );

        listView.setAdapter((listViewAdapter));

        // Inflate the layout for this fragment
        return view;
    }
}