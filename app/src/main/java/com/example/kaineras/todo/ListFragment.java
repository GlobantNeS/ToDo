package com.example.kaineras.todo;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private List<Tasks> taskList;
    View v;
    LinkAdapter mAdapter;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {

        // It's time we check if our activity implements the right inteface
        if (! (activity instanceof TasksListener) )
            throw new ClassCastException();
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(taskList==null)
            taskList= new ArrayList<Tasks>();
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void init() {
        ArrayList<String> temp=getArguments().getStringArrayList("TASKS");
        if(temp!=null) {
            for(String dato:temp)
            taskList.add(new Tasks(dato, false));
            //mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            mAdapter = new LinkAdapter(taskList, getActivity());
            v = inflater.inflate(R.layout.todo_list, container, false);
            ListView lView = (ListView) v.findViewById(R.id.menuList);
            lView.setAdapter(mAdapter);
            lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    ((TasksListener) getActivity()).TasksListener(taskList.get(position).title);
                }
            });
            setHasOptionsMenu(true);
        return v;

    }

    public interface TasksListener {
        public void TasksListener(String task);
    }


}
