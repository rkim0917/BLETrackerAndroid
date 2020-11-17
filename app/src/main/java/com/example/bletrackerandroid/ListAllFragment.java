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
 * Use the {@link ListAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListAllFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListAllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListAllFragment newInstance(String param1, String param2) {
        ListAllFragment fragment = new ListAllFragment();
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

        View view = inflater.inflate(R.layout.fragment_list_all, container, false);

        //String[] items = {};
        String[] items = {  "ID#1   Crane#2",
                            "ID#2   Bulldozer#3",
                            "ID#3   LargeWrench#5",
                            "ID#4   Hammer#6",
                            "ID#5   Crane#17",
                            "ID#6   Crane#19",
                            "ID#7   Crane#21",
                            "ID#8   SmallWrench#9",
                            "ID#9   Hammer#9",
                            "ID#10   Nails#11",
                            "ID#11   Crane#26",
                            "ID#12   ScrewDriver#21",
                            "ID#13   SmallWrench#5",
                            "ID#14   Hammer#16",
                            "ID#15   Crane#27"};

        ListView listView = (ListView) view.findViewById(R.id.listAll);

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